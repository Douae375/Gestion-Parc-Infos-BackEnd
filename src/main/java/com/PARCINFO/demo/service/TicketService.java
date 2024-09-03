package com.PARCINFO.demo.service;

import com.PARCINFO.demo.Mapper.TicketMapper;
import com.PARCINFO.demo.dto.TicketDTO;
import com.PARCINFO.demo.entity.Equipement;
import com.PARCINFO.demo.entity.Ticket;
import com.PARCINFO.demo.entity.Users;
import com.PARCINFO.demo.repository.EquipementRepository;
import com.PARCINFO.demo.repository.TicketRepository;
import com.PARCINFO.demo.repository.UsersRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @PersistenceContext
    private EntityManager entityManager;

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UsersRepository userRepository;
    private final EquipementRepository equipementRepository;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper, UsersRepository userRepository, EquipementRepository equipementRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.userRepository = userRepository;
        this.equipementRepository = equipementRepository;
    }

    // Method to get all tickets
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.toDtos(tickets);
    }

    // Method to get tickets by status
    public List<TicketDTO> getTicketsByStatut() {
        return ticketRepository.countTicketsByStatut();
    }

    // Method to get tickets by technician
    public List<TicketDTO> getTicketsByTechnician() {
        return ticketRepository.countTicketsByTechnician();
    }

    // Method to get tickets by equipment type
    public List<TicketDTO> getTicketsByEquipementType() {
        return ticketRepository.countTicketsByEquipementType();
    }

    @Transactional // Ensures that the method is executed within a transaction
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Users user = userRepository.findById(ticketDTO.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + ticketDTO.getUser().getId()));

        Equipement equipement = equipementRepository.findById(ticketDTO.getEquipement().getSerie())
                .orElseThrow(() -> new RuntimeException("Equipement not found with id: " + ticketDTO.getEquipement().getSerie()));

        equipement = entityManager.merge(equipement);  // Ensure the entity is managed

        Ticket ticket = new Ticket();
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setType(ticketDTO.getType());
        ticket.setDate(ticketDTO.getDate() != null ? ticketDTO.getDate() : new Date());
        ticket.setStatut(ticketDTO.getStatut());
        ticket.setUser(user);
        ticket.setEquipement(equipement);

        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(savedTicket);
    }

    public TicketDTO updateTicket(Integer id, TicketDTO ticketDTO) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Only update the fields that are necessary
        if (ticketDTO.getDate() != null) {
            existingTicket.setDate(ticketDTO.getDate());
        }
        if (ticketDTO.getDescription() != null) {
            existingTicket.setDescription(ticketDTO.getDescription());
        }
        if (ticketDTO.getType() != null) {
            existingTicket.setType(ticketDTO.getType());
        }
        if (ticketDTO.getEquipement() != null) {
            existingTicket.setEquipement(ticketDTO.getEquipement());
        }
        // Note: Do not update the user field, as you want to keep the existing user

        if (ticketDTO.getStatut() != null) {
            existingTicket.setStatut(ticketDTO.getStatut());
        }

        Ticket updatedTicket = ticketRepository.save(existingTicket);
        return ticketMapper.toDto(updatedTicket);
    }

    // Method to delete a ticket
    public void deleteTicket(Integer id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepository.deleteById(id);
    }

    // Method to get a ticket by ID
    public TicketDTO getTicketById(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return ticketMapper.toDto(ticket);
    }
}

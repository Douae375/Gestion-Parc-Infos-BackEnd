package com.PARCINFO.demo.service;

import com.PARCINFO.demo.Mapper.TicketMapper;
import com.PARCINFO.demo.dto.TicketDTO;
import com.PARCINFO.demo.entity.Ticket;
import com.PARCINFO.demo.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    // Méthode pour obtenir tous les tickets
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.toDtos(tickets);
    }

    // Méthode pour obtenir les tickets par statut
    public List<TicketDTO> getTicketsByStatut() {
        return ticketRepository.countTicketsByStatut();
    }

    // Méthode pour obtenir les tickets par technicien
    public List<TicketDTO> getTicketsByTechnician() {
        return ticketRepository.countTicketsByTechnician();
    }

    // Méthode pour obtenir les tickets par type d'équipement
    public List<TicketDTO> getTicketsByEquipementType() {
        return ticketRepository.countTicketsByEquipementType();
    }

    // CRUD: Méthode pour créer un ticket
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(savedTicket);
    }

    // CRUD: Méthode pour mettre à jour un ticket
    public TicketDTO updateTicket(Integer id, TicketDTO ticketDTO) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        existingTicket.setDate(ticketDTO.getDate());
        existingTicket.setDescription(ticketDTO.getDescription());
        existingTicket.setType(ticketDTO.getType());
        existingTicket.setEquipement(ticketDTO.getEquipement());
        existingTicket.setUser(ticketDTO.getUser());
        existingTicket.setStatut(ticketDTO.getStatut());

        Ticket updatedTicket = ticketRepository.save(existingTicket);
        return ticketMapper.toDto(updatedTicket);
    }

    // CRUD: Méthode pour supprimer un ticket
    public void deleteTicket(Integer id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepository.deleteById(id);
    }

    // CRUD: Méthode pour obtenir un ticket par ID
    public TicketDTO getTicketById(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return ticketMapper.toDto(ticket);
    }
}

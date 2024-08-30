package com.PARCINFO.demo.controller;

import com.PARCINFO.demo.dto.TicketDTO;
import com.PARCINFO.demo.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Endpoint to get all tickets
    @GetMapping
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // Endpoint to create a new ticket
    @PostMapping
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.createTicket(ticketDTO);
    }

    // Endpoint to update an existing ticket by ID
    @PutMapping("/{id}")
    public TicketDTO updateTicket(@PathVariable Integer id, @RequestBody TicketDTO ticketDTO) {
        return ticketService.updateTicket(id, ticketDTO);
    }

    // Endpoint to delete a ticket by ID
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }

    // Endpoint to get a ticket by ID
    @GetMapping("/{id}")
    public TicketDTO getTicketById(@PathVariable Integer id) {
        return ticketService.getTicketById(id);
    }

    // Endpoint to get the count of tickets by status
    @GetMapping("/by-status")
    public List<TicketDTO> getTicketsByStatut() {
        return ticketService.getTicketsByStatut();
    }

    // Endpoint to get the count of tickets by technician
    @GetMapping("/by-technician")
    public List<TicketDTO> getTicketsByTechnician() {
        return ticketService.getTicketsByTechnician();
    }

    // Endpoint to get the count of tickets by equipment type
    @GetMapping("/by-equipement-type")
    public List<TicketDTO> getTicketsByEquipementType() {
        return ticketService.getTicketsByEquipementType();
    }
}

package com.PARCINFO.demo.repository;

import com.PARCINFO.demo.dto.TicketDTO;
import com.PARCINFO.demo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query("SELECT new com.PARCINFO.demo.dto.TicketDTO(t.statut, COUNT(t)) FROM Ticket t GROUP BY t.statut")
    List<TicketDTO> countTicketsByStatut();

    @Query("SELECT new com.PARCINFO.demo.dto.TicketDTO(t.user.name, COUNT(t)) FROM Ticket t WHERE t.user.role = 'TECHNICIAN' GROUP BY t.user.name")
    List<TicketDTO> countTicketsByTechnician();
    @Query("SELECT new com.PARCINFO.demo.dto.TicketDTO(e.type, COUNT(t)) FROM Ticket t JOIN t.equipement e GROUP BY e.type")
    List<TicketDTO> countTicketsByEquipementType();
}

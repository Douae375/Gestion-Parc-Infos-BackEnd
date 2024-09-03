package com.PARCINFO.demo.Mapper;

import com.PARCINFO.demo.dto.TicketDTO;
import com.PARCINFO.demo.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TicketMapper implements EntityMapper <Ticket, TicketDTO> {
    @Override
    public TicketDTO toDto(Ticket entity) {
        TicketDTO ticket=new TicketDTO();
        ticket.setId(entity.getId());
        ticket.setDate(entity.getDate());
        ticket.setDescription(entity.getDescription());
        ticket.setType(entity.getType());
        ticket.setEquipement(entity.getEquipement());
        ticket.setUser(entity.getUser());
        ticket.setStatut(entity.getStatut());
        return ticket;
    }

    @Override
    public List<TicketDTO> toDtos(List<Ticket> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Ticket toEntity(TicketDTO dto) {
        Ticket entity = new Ticket();
        // Do not set the ID here unless you're updating an existing entity
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setDate(dto.getDate());
        entity.setType(dto.getType());
        entity.setEquipement(dto.getEquipement());
        entity.setUser(dto.getUser());
        entity.setDescription(dto.getDescription());
        entity.setStatut(dto.getStatut());
        return entity;
    }


    @Override
    public List<Ticket> toEntities(List<TicketDTO> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}

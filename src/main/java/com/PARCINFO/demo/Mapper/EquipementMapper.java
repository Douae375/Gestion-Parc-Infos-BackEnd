package com.PARCINFO.demo.Mapper;

import com.PARCINFO.demo.dto.EquipementDTO;
import com.PARCINFO.demo.entity.Equipement;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EquipementMapper implements EntityMapper<Equipement, EquipementDTO> {

    @Override
    public EquipementDTO toDto(Equipement entity) {
        EquipementDTO equipementDTO=new EquipementDTO();
        equipementDTO.setSerie(entity.getSerie());
        equipementDTO.setModele(entity.getModele());
        equipementDTO.setNom(entity.getNom());
        equipementDTO.setDateAchat(entity.getDateAchat());
        equipementDTO.setType(entity.getType());
        return equipementDTO;
    }

    @Override
    public List<EquipementDTO> toDtos(List<Equipement> entities) {
        return  entities.stream().map(this::toDto).toList();
    }

    @Override
    public Equipement toEntity(EquipementDTO dto) {
        Equipement equipement=new Equipement();
        equipement.setSerie(dto.getSerie());
        equipement.setType(dto.getType());
        equipement.setModele(dto.getModele());
        equipement.setDateAchat(dto.getDateAchat());
        equipement.setNom(dto.getNom());
        return equipement;
    }

    @Override
    public List<Equipement> toEntities(List<EquipementDTO> dtos) {
        return  dtos.stream().map(this::toEntity).toList();
    }
}

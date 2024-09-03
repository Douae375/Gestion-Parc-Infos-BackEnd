package com.PARCINFO.demo.Mapper;

import com.PARCINFO.demo.dto.DepartementDTO;
import com.PARCINFO.demo.entity.Departement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartementMapper implements EntityMapper<Departement, DepartementDTO> {

    private final UserMapper userMapper;

    // Constructor injection for UserMapper
    public DepartementMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public DepartementDTO toDto(Departement entity) {
        DepartementDTO departementDTO = new DepartementDTO();
        departementDTO.setId(entity.getId());
        departementDTO.setNom(entity.getNom());
        departementDTO.setUtilisateurs(userMapper.toDtos(entity.getUtilisateurs()));
        return departementDTO;
    }

    @Override
    public List<DepartementDTO> toDtos(List<Departement> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Departement toEntity(DepartementDTO dto) {
        Departement departement = new Departement();
        departement.setId(dto.getId());
        departement.setNom(dto.getNom());
        departement.setUtilisateurs(userMapper.toEntities(dto.getUtilisateurs()));
        return departement;
    }

    @Override
    public List<Departement> toEntities(List<DepartementDTO> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}

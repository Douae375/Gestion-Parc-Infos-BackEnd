package com.PARCINFO.demo.Mapper;

import com.PARCINFO.demo.dto.UserDTO;
import com.PARCINFO.demo.entity.Departement;
import com.PARCINFO.demo.entity.Users;
import com.PARCINFO.demo.repository.DepartementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements EntityMapper<Users, UserDTO> {

    private final DepartementRepository departementRepository;

    public UserMapper(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    public UserDTO toDto(Users entity) {
        if (entity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setUsername(entity.getName());
        userDTO.setRole(entity.getRole());
        userDTO.setEmail(entity.getEmail());
        userDTO.setPassword(entity.getPassword());
        userDTO.setDepartementId(entity.getDepartement() != null ? entity.getDepartement().getId() : null);

        return userDTO;
    }

    @Override
    public List<UserDTO> toDtos(List<Users> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Users toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        Users user = new Users();
        user.setId(dto.getId());
        user.setName(dto.getUsername());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        if (dto.getDepartementId() != null) {
            Departement departement = departementRepository.findById(dto.getDepartementId())
                    .orElseThrow(() -> new RuntimeException("Departements not found"));
            user.setDepartement(departement);
        }

        return user;
    }

    @Override
    public List<Users> toEntities(List<UserDTO> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}

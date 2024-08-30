package com.PARCINFO.demo.Mapper;

import com.PARCINFO.demo.dto.UserDTO;
import com.PARCINFO.demo.entity.Users;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
@Component
public class UserMapper implements EntityMapper<Users, UserDTO>{

    @Override
    public UserDTO toDto(Users entity) {
        if ( entity == null ) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId() );
        userDTO.setUsername( entity.getName() );
        userDTO.setRole( entity.getRole() );
        userDTO.setEmail( entity.getEmail() );

        return userDTO;
    }

    @Override
    public List<UserDTO> toDtos(List<Users> entities) {
        return entities.stream().map(this::toDto).toList();
    }
    @Override
    public Users toEntity(UserDTO dto) {
        return null;
    }

    @Override
    public List<Users> toEntities(List<UserDTO> dtos) {

        return Collections.emptyList()   ;
    }

}

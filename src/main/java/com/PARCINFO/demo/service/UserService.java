package com.PARCINFO.demo.service;

import com.PARCINFO.demo.Mapper.UserMapper;
import com.PARCINFO.demo.dto.UserDTO;
import com.PARCINFO.demo.entity.Users;
import com.PARCINFO.demo.repository.UsersRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UsersRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserDTO findByEmail(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        // Convertir l'entité `Users` en `UserDTO` ici
        return userMapper.toDto(user);
    }
}

package com.PARCINFO.demo.service;

import com.PARCINFO.demo.Mapper.UserMapper;
import com.PARCINFO.demo.dto.UserDTO;
import com.PARCINFO.demo.entity.Users;
import com.PARCINFO.demo.repository.UsersRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserService(UsersRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Find a user by email
    public UserDTO findByEmail(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return userMapper.toDto(user);
    }

    // Create a new user
    public UserDTO createUser(UserDTO userDTO) {
        Users user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Users savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    // Get all users
    public List<UserDTO> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return userMapper.toDtos(users);
    }

    // Get a user by ID
    public UserDTO getUserById(Integer id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    // Update a user
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        existingUser.setName(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRole(userDTO.getRole());

        Users updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    // Delete a user
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}

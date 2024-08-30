package com.PARCINFO.demo.service;

import com.PARCINFO.demo.Mapper.DepartementMapper;
import com.PARCINFO.demo.Mapper.UserMapper;
import com.PARCINFO.demo.dto.DepartementDTO;
import com.PARCINFO.demo.entity.Departement;
import com.PARCINFO.demo.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {
    private final DepartementMapper departementMapper;
    private final DepartementRepository departementRepository;
    @Autowired
    private  UserMapper userMapper ;
    public DepartementService(DepartementMapper departementMapper, DepartementRepository departementRepository) {
        this.departementMapper = departementMapper;
        this.departementRepository = departementRepository;
    }
    public List<DepartementDTO> getAllDepartements() {
        List<Departement> departements = departementRepository.findAll();
        return departementMapper.toDtos(departements);
    }
    public DepartementDTO getDepartementById(Integer id) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departement not found"));
        return departementMapper.toDto(departement);
    }

    // Create a new departement
    public DepartementDTO createDepartement(DepartementDTO departementDTO) {
        Departement departement = departementMapper.toEntity(departementDTO);
        Departement savedDepartement = departementRepository.save(departement);
        return departementMapper.toDto(savedDepartement);
    }

    // Update an existing departement
    public DepartementDTO updateDepartement(Integer id, DepartementDTO departementDTO) {
        Departement existingDepartement = departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departement not found"));

        existingDepartement.setNom(departementDTO.getNom());

        // Convert UserDTOs to Users entities and set to existing department
        existingDepartement.setUtilisateurs(userMapper.toEntities(departementDTO.getUtilisateurs()));

        Departement updatedDepartement = departementRepository.save(existingDepartement);
        return departementMapper.toDto(updatedDepartement);
    }


    // Delete a departement by ID
    public void deleteDepartement(Integer id) {
        if (!departementRepository.existsById(id)) {
            throw new RuntimeException("Departement not found");
        }
        departementRepository.deleteById(id);
    }


}

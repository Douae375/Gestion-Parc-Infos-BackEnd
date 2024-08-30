package com.PARCINFO.demo.service;

import com.PARCINFO.demo.Mapper.DepartementMapper;
import com.PARCINFO.demo.Mapper.EquipementMapper;
import com.PARCINFO.demo.dto.EquipementDTO;
import com.PARCINFO.demo.entity.Equipement;
import com.PARCINFO.demo.repository.DepartementRepository;
import com.PARCINFO.demo.repository.EquipementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EquipementService {
    private final EquipementMapper equipementMapper;
    private final EquipementRepository equipementRepository;

    public EquipementService(EquipementMapper equipementMapper, EquipementRepository equipementRepository) {
        this.equipementMapper = equipementMapper;
        this.equipementRepository = equipementRepository;
    }
    public EquipementDTO createEquipement(EquipementDTO equipementDTO) {
        Equipement equipement = equipementMapper.toEntity(equipementDTO);
        Equipement savedEquipement = equipementRepository.save(equipement);
        return equipementMapper.toDto(savedEquipement);
    }

    // Méthode pour obtenir tous les équipements
    public List<EquipementDTO> getAllEquipements() {
        List<Equipement> equipements = equipementRepository.findAll();
        return equipementMapper.toDtos(equipements);
    }

    // Méthode pour obtenir un équipement par ID
    public EquipementDTO getEquipementById(Integer id) {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipement not found"));
        return equipementMapper.toDto(equipement);
    }

    // Méthode pour mettre à jour un équipement
    public EquipementDTO updateEquipement(Integer id, EquipementDTO equipementDTO) {
        Equipement existingEquipement = equipementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipement not found"));

        existingEquipement.setNom(equipementDTO.getNom());
        existingEquipement.setModele(equipementDTO.getModele());
        existingEquipement.setDateAchat(equipementDTO.getDateAchat());
        existingEquipement.setType(equipementDTO.getType());

        Equipement updatedEquipement = equipementRepository.save(existingEquipement);
        return equipementMapper.toDto(updatedEquipement);
    }

    // Méthode pour supprimer un équipement
    public void deleteEquipement(Integer id) {
        if (!equipementRepository.existsById(id)) {
            throw new RuntimeException("Equipement not found");
        }
        equipementRepository.deleteById(id);
    }

    // Méthode pour obtenir le nombre d'équipements par type
    public List<Object[]> countEquipementsByType() {
        return equipementRepository.countEquipementsByType();
    }

}

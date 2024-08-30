package com.PARCINFO.demo.controller;

import com.PARCINFO.demo.dto.EquipementDTO;
import com.PARCINFO.demo.service.EquipementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {

    private final EquipementService equipementService;

    public EquipementController(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

    // Endpoint pour créer un nouvel équipement
    @PostMapping
    public ResponseEntity<EquipementDTO> createEquipement(@RequestBody EquipementDTO equipementDTO) {
        EquipementDTO createdEquipement = equipementService.createEquipement(equipementDTO);
        return ResponseEntity.ok(createdEquipement);
    }

    // Endpoint pour récupérer tous les équipements
    @GetMapping
    public ResponseEntity<List<EquipementDTO>> getAllEquipements() {
        List<EquipementDTO> equipements = equipementService.getAllEquipements();
        return ResponseEntity.ok(equipements);
    }

    // Endpoint pour récupérer un équipement par ID
    @GetMapping("/{id}")
    public ResponseEntity<EquipementDTO> getEquipementById(@PathVariable Integer id) {
        EquipementDTO equipement = equipementService.getEquipementById(id);
        return ResponseEntity.ok(equipement);
    }

    // Endpoint pour mettre à jour un équipement existant
    @PutMapping("/{id}")
    public ResponseEntity<EquipementDTO> updateEquipement(@PathVariable Integer id, @RequestBody EquipementDTO equipementDTO) {
        EquipementDTO updatedEquipement = equipementService.updateEquipement(id, equipementDTO);
        return ResponseEntity.ok(updatedEquipement);
    }

    // Endpoint pour supprimer un équipement par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Integer id) {
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint pour obtenir le nombre d'équipements par type
    @GetMapping("/count-by-type")
    public ResponseEntity<List<Object[]>> countEquipementsByType() {
        List<Object[]> counts = equipementService.countEquipementsByType();
        return ResponseEntity.ok(counts);
    }
}

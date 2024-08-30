package com.PARCINFO.demo.controller;

import com.PARCINFO.demo.dto.DepartementDTO;
import com.PARCINFO.demo.service.DepartementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departements")
public class DepartementController {

    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    // Créer un nouveau département
    @PostMapping
    public ResponseEntity<DepartementDTO> createDepartement(@RequestBody DepartementDTO departementDTO) {
        DepartementDTO createdDepartement = departementService.createDepartement(departementDTO);
        return ResponseEntity.ok(createdDepartement);
    }

    // Récupérer tous les départements
    @GetMapping
    public ResponseEntity<List<DepartementDTO>> getAllDepartements() {
        List<DepartementDTO> departements = departementService.getAllDepartements();
        return ResponseEntity.ok(departements);
    }

    // Récupérer un département par ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartementDTO> getDepartementById(@PathVariable Integer id) {
        DepartementDTO departement = departementService.getDepartementById(id);
        return ResponseEntity.ok(departement);
    }

    // Mettre à jour un département
    @PutMapping("/{id}")
    public ResponseEntity<DepartementDTO> updateDepartement(@PathVariable Integer id, @RequestBody DepartementDTO departementDTO) {
        DepartementDTO updatedDepartement = departementService.updateDepartement(id, departementDTO);
        return ResponseEntity.ok(updatedDepartement);
    }

    // Supprimer un département
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Integer id) {
        departementService.deleteDepartement(id);
        return ResponseEntity.noContent().build();
    }
}

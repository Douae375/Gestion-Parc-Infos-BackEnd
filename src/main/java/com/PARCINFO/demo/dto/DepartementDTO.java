package com.PARCINFO.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartementDTO {
    private Integer id;
    private String nom;
    private List<UserDTO> utilisateurs;
    // Getters and Setters

    public DepartementDTO() {
    }

    public DepartementDTO(Integer id, String nom, List<UserDTO> utilisateurs) {
        this.id = id;
        this.nom = nom;
        this.utilisateurs = utilisateurs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<UserDTO> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<UserDTO> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}

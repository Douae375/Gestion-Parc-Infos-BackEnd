package com.PARCINFO.demo.dto;

import java.util.Date;

public class EquipementDTO {
    private Integer serie;

    private String nom;
    private String type;
    private String modele;
    private Date dateAchat;

    public EquipementDTO() {
    }

    public EquipementDTO(Integer serie, String nom, String type, String modele, Date dateAchat) {
        this.serie = serie;
        this.nom = nom;
        this.type = type;
        this.modele = modele;
        this.dateAchat = dateAchat;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
}

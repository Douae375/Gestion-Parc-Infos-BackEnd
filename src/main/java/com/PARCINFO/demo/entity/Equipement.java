package com.PARCINFO.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "equipements")
@Data
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serie;

    private String nom;
    private String type;
    private String modele;
    private Date dateAchat;


}

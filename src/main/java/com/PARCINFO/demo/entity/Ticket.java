package com.PARCINFO.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private Date date;
    private String description;
    private String statut;
    @ManyToOne
    @JoinColumn(name = "equipement_serie")
    private Equipement equipement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}

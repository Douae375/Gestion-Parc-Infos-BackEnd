package com.PARCINFO.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Table(name = "departement")
@Data
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @OneToMany(mappedBy = "departement", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Users> utilisateurs;

}

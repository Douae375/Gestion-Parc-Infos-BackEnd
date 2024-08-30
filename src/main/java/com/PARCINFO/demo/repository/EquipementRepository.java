package com.PARCINFO.demo.repository;

import com.PARCINFO.demo.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipementRepository extends JpaRepository<Equipement, Integer> {
    @Query("SELECT e.type, COUNT(e) FROM Equipement e GROUP BY e.type")
    List<Object[]> countEquipementsByType();
}

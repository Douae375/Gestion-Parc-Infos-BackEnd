package com.PARCINFO.demo.repository;

import com.PARCINFO.demo.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

}

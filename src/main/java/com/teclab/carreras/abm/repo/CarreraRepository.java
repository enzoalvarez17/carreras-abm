package com.teclab.carreras.abm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teclab.carreras.abm.model.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
}


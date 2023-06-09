package com.example.demo.repositories;

import com.example.demo.dto.PacienteDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface PacienteRepository extends JpaRepository<PacienteDto, Integer> {

}
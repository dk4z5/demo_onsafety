package com.onsafety.demoproj.repository;

import org.springframework.stereotype.Repository;

import com.onsafety.demoproj.models.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}

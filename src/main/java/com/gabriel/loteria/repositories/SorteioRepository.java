package com.gabriel.loteria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.loteria.entities.Sorteio;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Long>{

}

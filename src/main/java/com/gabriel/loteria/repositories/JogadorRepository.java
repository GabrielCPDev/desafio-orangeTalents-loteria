package com.gabriel.loteria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.loteria.entities.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long>{

	Jogador findByEmail(String email);
}

package com.gabriel.loteria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.loteria.entities.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long>{

	Optional<Jogador> findByEmail(String email);
}

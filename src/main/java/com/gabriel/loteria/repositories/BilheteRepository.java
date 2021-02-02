package com.gabriel.loteria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.loteria.entities.Bilhete;

@Repository
public interface BilheteRepository extends JpaRepository<Bilhete, Long>{

}

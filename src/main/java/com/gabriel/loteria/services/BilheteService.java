package com.gabriel.loteria.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gabriel.loteria.dto.BilheteDTO;
import com.gabriel.loteria.entities.Bilhete;
import com.gabriel.loteria.repositories.BilheteRepository;
import com.gabriel.loteria.repositories.JogadorRepository;

@Service
public class BilheteService {
	
	private BilheteRepository bilheteRepository;
	
	private JogadorRepository jogadorRepository;

	@Transactional
	public BilheteDTO findById(Long id) {
		Optional<Bilhete> obj = bilheteRepository.findById(id);
		Bilhete entity = obj.orElseThrow(() -> new IllegalArgumentException("Entity not found"));
		return new BilheteDTO(entity);
	}

	@Transactional
	public BilheteDTO insert(BilheteDTO dto) {
		Bilhete entity = new Bilhete();
		entity.setId(dto.getId());
		entity.setDataAposta(dto.getDataAposta());
		entity.setJogador(dto.getJogador());
		entity.setNumeroSorteio(dto.getNumeroSorteio());
		entity.setNumeros(dto.getNumeros());
		
		return new BilheteDTO(entity);
	}
}

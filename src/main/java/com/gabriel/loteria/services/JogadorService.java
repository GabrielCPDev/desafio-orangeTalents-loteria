package com.gabriel.loteria.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gabriel.loteria.dto.BilheteDTO;
import com.gabriel.loteria.dto.JogadorDTO;
import com.gabriel.loteria.entities.Bilhete;
import com.gabriel.loteria.entities.Jogador;
import com.gabriel.loteria.repositories.BilheteRepository;
import com.gabriel.loteria.repositories.JogadorRepository;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository jogadorRepository;

	@Autowired
	private BilheteRepository bilheteRepository;

	@Transactional
	public JogadorDTO findById(Long id) {
		Optional<Jogador> obj = jogadorRepository.findById(id);
		Jogador entity = obj.orElseThrow(() -> new IllegalArgumentException("Entity not found"));
		return new JogadorDTO(entity, entity.getApostas());
	}

	@Transactional
	public JogadorDTO insert(JogadorDTO dto) {
		Jogador entity = new Jogador();
		copyDtoToEntity(dto, entity);
		entity = jogadorRepository.save(entity);
		return new JogadorDTO(entity);
	}

	public Page<JogadorDTO> findAllPaged(PageRequest pageRequest) {
		Page<Jogador> list = jogadorRepository.findAll(pageRequest);

		return list.map(x -> new JogadorDTO(x));
	}

	public JogadorDTO findByEmail(String email) {
		Optional<Jogador> obj = jogadorRepository.findByEmail(email);
		Jogador entity = obj.orElseThrow(() -> new IllegalArgumentException("Entity not found"));
		return new JogadorDTO(entity, entity.getApostas());
	}

	private void copyDtoToEntity(JogadorDTO dto, Jogador entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setName(dto.getName());

		entity.getApostas().clear();
		for (BilheteDTO bilheteDto : dto.getBilhetes()) {
			Bilhete bilhete = bilheteRepository.getOne(bilheteDto.getId());
			entity.getApostas().add(bilhete);
		}
	}

}
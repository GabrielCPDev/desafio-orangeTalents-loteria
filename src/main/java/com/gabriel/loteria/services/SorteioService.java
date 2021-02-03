package com.gabriel.loteria.services;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gabriel.loteria.dto.SorteioDTO;
import com.gabriel.loteria.entities.Sorteio;
import com.gabriel.loteria.repositories.SorteioRepository;

@Service
public class SorteioService {

	@Autowired
	private SorteioRepository repository;

	@Transactional
	public Page<SorteioDTO> findAllPaged(PageRequest pageRequest) {
		Page<Sorteio> list = repository.findAll(pageRequest);

		return list.map(x -> new SorteioDTO(x));
	}

	@Transactional
	public SorteioDTO findById(Long id) {
		Optional<Sorteio> obj = repository.findById(id);
		Sorteio entity = obj.orElseThrow(() -> new IllegalArgumentException("Entity not found"));
		return new SorteioDTO(entity);
	}

	@Transactional
	public SorteioDTO insert(SorteioDTO dto) {
		Sorteio entity = new Sorteio();
		entity.setId(dto.getId());
		entity.setDataSorteio(dto.getDataSorteio());
		entity = repository.save(entity);
		return new SorteioDTO(entity);
	}

	@Transactional
	public SorteioDTO update(Long id, Integer quantidadeDeNumeros, SorteioDTO dto) {
		LocalDate agora = LocalDate.now();

		try {

			Sorteio entity = repository.getOne(id);

			if (dto.getDataSorteio().equals(agora)) {
				entity.setNumerosSorteados(sortearNumeros(quantidadeDeNumeros));
				
				entity.setDataSorteio(dto.getDataSorteio());
				entity = repository.save(entity);
			}

			
			return new SorteioDTO(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Id not found: " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultDataAccessException("Id not found: " + id, 0);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Integrity violation");
		}
	}

	public Set<Integer> sortearNumeros(Integer quantidadeDeNumeros) {
		Set<Integer> numerosSorteados = new HashSet<>();
		for (int i = 0; i < quantidadeDeNumeros; i++) {
			Random gerador = new Random();
			Integer numeroGerado = gerador.nextInt(61);
			numerosSorteados.add(numeroGerado);
		}
		return numerosSorteados;
	}
}

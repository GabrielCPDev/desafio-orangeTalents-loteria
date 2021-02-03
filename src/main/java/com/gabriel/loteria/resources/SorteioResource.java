package com.gabriel.loteria.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.loteria.dto.SorteioDTO;
import com.gabriel.loteria.services.BilheteService;
import com.gabriel.loteria.services.SorteioService;

@RestController
@RequestMapping(value = "/sorteios")
public class SorteioResource {

	@Autowired
	private SorteioService sorteioService;
	@Autowired
	private BilheteService bilheteService;

	@GetMapping
	public ResponseEntity<Page<SorteioDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction));

		Page<SorteioDTO> list = sorteioService.findAllPaged(pageRequest);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SorteioDTO> findById(@PathVariable Long id) {
		SorteioDTO dto = sorteioService.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<SorteioDTO> insert(@RequestBody SorteioDTO dto) {
		dto = sorteioService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<SorteioDTO> update(@PathVariable Long id,@RequestParam(value = "numerosParaSorteio", defaultValue = "6") Integer quantidadeDeNumeros, @Valid @RequestBody SorteioDTO dto) {
		dto = sorteioService.update(id, quantidadeDeNumeros, dto);
		return ResponseEntity.ok().body(dto);
	}

}

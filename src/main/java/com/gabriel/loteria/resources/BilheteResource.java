package com.gabriel.loteria.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.loteria.dto.BilheteDTO;
import com.gabriel.loteria.services.BilheteService;

@RestController
@RequestMapping(value = "/bilhetes")
public class BilheteResource {

	@Autowired
	private BilheteService bilheteService;

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BilheteDTO> findById(@PathVariable Long id) {
		BilheteDTO dto = bilheteService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<BilheteDTO> insert(@RequestBody BilheteDTO dto) {
		dto = bilheteService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}	
	
}

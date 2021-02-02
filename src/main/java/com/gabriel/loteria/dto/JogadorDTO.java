package com.gabriel.loteria.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.gabriel.loteria.entities.Bilhete;
import com.gabriel.loteria.entities.Jogador;

public class JogadorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	@NotBlank(message = "Campo obrigatório")
	@Column(unique = true)
	private String email;

	private List<BilheteDTO> bilhetes = new ArrayList<>();

	public JogadorDTO() {

	}

	public JogadorDTO(Jogador entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
	}

	public JogadorDTO(Long id, String name, @NotBlank(message = "Campo obrigatório") String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public JogadorDTO(Jogador entity, List<Bilhete> list) {
		this(entity);
		list.forEach(bilhete -> this.bilhetes.add(new BilheteDTO(bilhete)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<BilheteDTO> getBilhetes() {
		return bilhetes;
	}

	public void setBilhetes(List<BilheteDTO> bilhetes) {
		this.bilhetes = bilhetes;
	}

}

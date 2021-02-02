package com.gabriel.loteria.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.gabriel.loteria.entities.Bilhete;
import com.gabriel.loteria.entities.Jogador;

public class BilheteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Instant dataAposta = Instant.now();
	private Jogador jogador;
	private Integer numeroSorteio;
	
	private Set<Integer> numeros = new HashSet<>();
	
	public BilheteDTO () {
		
	}

	public BilheteDTO(Long id, Instant dataAposta, Jogador jogador, Integer numeroSorteio) {
		super();
		this.id = id;
		this.dataAposta = dataAposta;
		this.jogador = jogador;
		this.numeroSorteio = numeroSorteio;
	}
	
	public BilheteDTO(Bilhete entity) {
		this.id = entity.getId();
		this.dataAposta = entity.getDataAposta();
		this.jogador = entity.getJogador();
		this.numeroSorteio = entity.getNumeroSorteio();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataAposta() {
		return dataAposta;
	}

	public void setDataAposta(Instant dataAposta) {
		this.dataAposta = dataAposta;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Integer getNumeroSorteio() {
		return numeroSorteio;
	}

	public void setNumeroSorteio(Integer numeroSorteio) {
		this.numeroSorteio = numeroSorteio;
	}

	public Set<Integer> getNumeros() {
		return numeros;
	}

	public void setNumeros(Set<Integer> numeros) {
		this.numeros = numeros;
	}

}

package com.gabriel.loteria.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_bilhetes")
public class Bilhete implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant dataAposta = Instant.now();
	
	@NotBlank(message = "Campo obrigatório")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jogador_id")
	private Jogador jogador;
	private Integer numeroSorteio;
	
	@ElementCollection
	private Set<Integer> numeros = new HashSet<>();
	
	public Bilhete () {
		
	}

	public Bilhete(Long id, Instant dataAposta, Jogador jogador, Integer numeroSorteio) {
		super();
		this.id = id;
		this.dataAposta = dataAposta;
		this.jogador = jogador;
		this.numeroSorteio = numeroSorteio;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bilhete other = (Bilhete) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

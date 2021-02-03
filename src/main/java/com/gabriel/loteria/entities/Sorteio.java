package com.gabriel.loteria.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

@Entity
@Table(name = "tb_sorteios")
public class Sorteio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@FutureOrPresent(message = "A data do sorteio não pode ser inferior ao dia atual")
	private LocalDate dataSorteio;
	@OneToMany
	private List<Bilhete> apostas;
	
	@ElementCollection
	private Set<Integer> numerosSorteados = new HashSet<>();
	
	public Sorteio () {
		
	}

	public Sorteio(Long id, LocalDate dataSorteio) {
		super();
		this.id = id;
		this.dataSorteio = dataSorteio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio(LocalDate dataSorteio) {
		this.dataSorteio = dataSorteio;
	}

	public List<Bilhete> getApostas() {
		return apostas;
	}

	public void setApostas(List<Bilhete> apostas) {
		this.apostas = apostas;
	}

	public Set<Integer> getNumerosSorteados() {
		return numerosSorteados;
	}

	public void setNumerosSorteados(Set<Integer> numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
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
		Sorteio other = (Sorteio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}

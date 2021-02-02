package com.gabriel.loteria.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.FutureOrPresent;

import com.gabriel.loteria.entities.Bilhete;
import com.gabriel.loteria.entities.Sorteio;

public class SorteioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@FutureOrPresent(message = "A data do sorteio não pode ser inferior ao dia atual")
	private LocalDate dataSorteio;
	
	
	private Set<Integer> numerosSorteados = new HashSet<>();
	
	private List<BilheteDTO> apostas;
	
	public SorteioDTO() {
		
	}

	public SorteioDTO(Long id,
			@FutureOrPresent(message = "A data do sorteio não pode ser inferior ao dia atual") LocalDate dataSorteio) {
		super();
		this.id = id;
		this.dataSorteio = dataSorteio;
	}
	
	public SorteioDTO (Sorteio entity) {
		this.id = entity.getId();
		this.dataSorteio = entity.getDataSorteio();
	}
	public SorteioDTO(Sorteio entity, List<Integer> list) {
		this(entity);
		list.forEach(numero -> this.numerosSorteados.add(numero));
	}

	
	public SorteioDTO(Sorteio entity, Set<Bilhete> bilhetes){
		this(entity);
		bilhetes.forEach(bilhete -> this.apostas.add(new BilheteDTO(bilhete)));
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

	public List<BilheteDTO> getApostas() {
		return apostas;
	}

	public void setApostas(List<BilheteDTO> apostas) {
		this.apostas = apostas;
	}
}

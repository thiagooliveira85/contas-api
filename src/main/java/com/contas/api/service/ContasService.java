package com.contas.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contas.api.model.Conta;
import com.contas.api.repository.ContasRepository;

@Service
public class ContasService {
	
	@Autowired
	private ContasRepository contas;
	
	public Conta salvar(Conta conta) {
		return contas.save(conta);
	}

	public List<Conta> listar() {
		return contas.findAll();
	}

	public Optional<Conta> buscaPorId(Long id) {
		return contas.findById(id);
	}
	
	public void remover(Long id) {
		contas.deleteById(id);		
	}

	public void remover(Conta conta) {
		contas.delete(conta);		
	}

	public BigDecimal getValorTotalLista(List<Conta> lista) {
		BigDecimal valorTotal = new BigDecimal(0); 
		for (Conta conta : lista) {
			valorTotal = valorTotal.add(conta.getValor());
		}
		return valorTotal;
	}
	
}

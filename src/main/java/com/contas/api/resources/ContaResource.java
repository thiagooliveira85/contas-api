package com.contas.api.resources;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.contas.api.event.RecursoCriadoEvent;
import com.contas.api.model.Conta;
import com.contas.api.service.ContasService;

@RestController
@RequestMapping("/contas-api")
public class ContaResource {
	
	@Autowired
	private ContasService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<List<Conta>> lista(){
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Conta> buscaPor(@PathVariable("id") long id){
		Optional<Conta> contaOpcional = service.buscaPorId(id);
		if (contaOpcional.isPresent())
			return ResponseEntity.ok(contaOpcional.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Conta> salvar(@Valid @RequestBody Conta conta, HttpServletResponse response){
		Conta contaSalva = service.salvar(conta);
		
		// Publicando o evento
		publisher.publishEvent(new RecursoCriadoEvent(this, response, contaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(contaSalva);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable long id) {
		service.remover(id);
	}
}

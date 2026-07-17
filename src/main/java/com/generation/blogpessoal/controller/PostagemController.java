package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*") //aceita reposição de fora do backend
public class PostagemController {

	@Autowired  //metraz todos os metodos da minha tabela postagem
	private PostagemRepository postagemRepository;
	
	// equivalente SELECT * FROM tb_postegem;
	@GetMapping //metodo get do http
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
		
	}
	
	@GetMapping("/{id}") //mostrar a postagem pelo id
	//aqui é para mostrar o id. Se tem um id, aparece 200. se está vazio, aparece 404 (usando o map, que parece o if e else)
											//@pathvariable ele pega o id da linha 31 e juntar com o da linha 34
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	
		// o metodo acima tem essa logica equivalente SELECT * FROM tb_postagens WHERE id = ?;
	}
	

	//consulta personalizada
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>>getAllByTitulo(@PathVariable String titulo){
	    return ResponseEntity.ok (postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
	return ResponseEntity.status(HttpStatus.CREATED)
			.body(postagemRepository.save(postagem));	
		// equivalente a INSERT INTO tb_postagens(titulo, texto) VALUES (?, ?);
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
	
		if(postagemRepository.existsById(postagem.getId()))
			return ResponseEntity.ok(postagemRepository.save(postagem));
			
		// equivalente a UPDATE tb_postagens SET titulo = ?, texto = ? WHERE id = ?;
		
		return ResponseEntity.notFound().build();
	
	}


	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}") 
	public void delete(@PathVariable Long id) {
	
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		// Delete FROM tb_postagens WHERE id = ?;
	postagemRepository.deleteById(id);
	}

}

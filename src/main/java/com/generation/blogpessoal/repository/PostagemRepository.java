package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Postagem;

//herdando o JPArepository e a tabela postagem, (Long tipo da chave primaria)
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	//equivalente SELECT * FROM tb_postagem WHERE titulo LIKE "%?%"
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
}

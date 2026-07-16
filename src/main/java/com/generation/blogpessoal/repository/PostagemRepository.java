package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Postagem;

//herdando o JPArepository e a tabela postagem, (Long tipo da chave primaria)
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

}

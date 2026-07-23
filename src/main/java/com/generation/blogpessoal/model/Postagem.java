package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // Gera uma tabela
@Table(name = "tb_postagens") // equivalente = CREATE TABLE tb_postagens(),
public class Postagem {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // equivalente = AUTO_INCREMENT
	private Long id;

	@NotBlank(message = "O atributo titulo é obrigatório!") // NOT NULL e tratamento de erro
	@Size(min = 5, max = 100, message = "o atributo titulo deve ter no mínimo 5 e no máximo 100 caracteres") // validação
																												// e
																												// tratamento
																												// de
																												// erro
	@Column(length = 100) // VARCHAR(100)
	private String titulo;

	@NotBlank(message = "O atributo texto é obrigatório!") // NOT NULL e tratamento de erro
	@Size(min = 10, max = 1000, message = "o atributo texto deve ter no mínimo 10 e no máximo 1000 caracteres") // validação
																												// e
																												// tratamento
																												// de
																												// erro
	@Column(length = 1000) // VARCHAR(100)
	private String texto;

	@UpdateTimestamp // atualiza a data e a hora a vez q vc criar a postagem e atualizar ela
	private LocalDateTime data;

	// criação de chave estrangera
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

}

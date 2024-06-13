package com.alura.literalura.model;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "Livros")
public class Livro {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String titulo;
	
	private String download;
	
	@Enumerated(EnumType.STRING)
	private Idioma idioma;
	
    @ManyToOne
    @JoinColumn(name = "autor_id")
	private Autor autor;
	
	public Livro() {
		
	}
	
	public Livro(DadosLivro dadosLivro) {
		
		this.titulo = dadosLivro.titulo();
		this.download = dadosLivro.download();
		this.idioma = Idioma.fromString(dadosLivro.idioma().stream()
                .limit(1).collect(Collectors.joining()));;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
    @Override
    public String toString() {
        return  "\n-------------- LIVRO ------------- \n"
                + " Titulo: " + titulo + "\n"
                + " Idioma: " + idioma + "\n"
                + " Autor: " + autor.getNome() + "\n"
                + " Numero de downloads: " + download + "\n"
                + "--------------------------------- ";
    }
}


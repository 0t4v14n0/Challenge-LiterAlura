package com.alura.literalura.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "autores")
public class Autor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nome;
	
	private int dataNascimento;
	
	private Integer dataMorte;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Livro> livros;
	
	public Autor(){
		
	}
	
	public Autor(DadosAutor dadosAutor){
		
		this.nome = dadosAutor.nome();
		this.dataNascimento = dadosAutor.dataNascimento();
		this.dataMorte = dadosAutor.dataMorte();
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(int dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getDataMorte() {
		return dataMorte;
	}

	public void setDataMorte(int dataMorte) {
		this.dataMorte = dataMorte;
	}

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;
    }
    
    @Override
	public String toString() {
		return "\n-------------- Autor ------------- \n"
                + " Autor: " + getNome() + "\n"
                + " Ano de nascimento do autor: " + getDataNascimento() + "\n"
                + " Ano de morte do autor: " + (getDataMorte() != 0 ? getDataMorte() : "Vivo") + "\n"
                + "--------------------------------- ";
	}
	
}

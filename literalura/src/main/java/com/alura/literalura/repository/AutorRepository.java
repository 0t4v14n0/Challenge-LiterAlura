package com.alura.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
	
	@Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.nome LIKE %:nome%")
    Optional<Autor> buscarAutorPorNome(@Param("nome") String nome);
	
	@Query("SELECT l FROM Livro l JOIN l.autor a WHERE l.titulo LIKE %:nome%")
    Optional<Livro> buscarLivroPorNome(@Param("nome") String titulo);
	
	@Query("SELECT l FROM Livro l")
	List<Livro> buscarTodosLivros();
	
}


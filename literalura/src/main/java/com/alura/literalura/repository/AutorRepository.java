package com.alura.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Idioma;
import com.alura.literalura.model.Livro;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
	
	@Query("SELECT a FROM Livro l JOIN l.autor a WHERE a.nome LIKE %:nome%")
    Optional<Autor> buscarAutorPorNome(@Param("nome") String nome);
	
	@Query("SELECT l FROM Livro l JOIN l.autor a WHERE l.titulo LIKE %:nome%")
    Optional<Livro> buscarLivroPorNome(@Param("nome") String titulo);
	
	@Query("SELECT l FROM Livro l")
	List<Livro> buscarTodosLivros();
	
    @Query("SELECT a FROM Autor a WHERE a.dataNascimento <= :ano AND (a.dataMorte IS NULL OR a.dataMorte >= :ano)")
    List<Autor> buscarAutoresVivosNoAno(@Param("ano") int ano);
    
    @Query("SELECT l FROM Autor a JOIN a.livros l ORDER BY l.download DESC LIMIT 10")
    List<Livro> top10Livros();
    
    @Query("SELECT l FROM Autor a JOIN a.livros l WHERE l.idioma = :idioma")
    List<Livro> buscarLivrosIdioma(@Param("idioma") Idioma idioma) ;
	
}


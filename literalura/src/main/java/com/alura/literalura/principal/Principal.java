package com.alura.literalura.principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Dados;
import com.alura.literalura.model.DadosLivro;
import com.alura.literalura.model.Livro;
import com.alura.literalura.service.ConsumindoAPI;
import com.alura.literalura.service.ConverteDados;

@Component
@Service
public class Principal {
	
	private Scanner leitura = new Scanner(System.in);
	
	@Autowired
	private ConsumindoAPI consumindo = new ConsumindoAPI();
	
	@Autowired
	private ConverteDados converte = new ConverteDados();
	
	@Autowired
	private AutorRepository repositorio;
	
	public Principal(AutorRepository repositorio){
	    this.repositorio = repositorio;
	}

	public void menu() {
		
        int opcao;
        
		do {
        	
    		System.out.println("""	
    				
                    1 - Buscar Livro pelo titulo
                    2 - Lista Livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar Livros em um determinado idioma
                    
                    0 - Sair 
                                                    
                    """);
    		
            opcao = leitura.nextInt();
            leitura.nextLine();
            
            switch (opcao) {
            case 0:
            	System.out.println("Xau");
            	break;
            case 1:
            	buscaLivro();
            	break;
            case 2:
            	listarLivros();
            	break;
            case 3:
            	listarAutores();
            	break;
            case 4:
            	break;
            case 5:
            	break;
            default:
            	System.out.println("Opção inválida !");
            	break;
            }
        	
        }while(opcao != 0);
		
	}

	private void listarAutores() {
		
		List<Autor> autores = repositorio.findAll();
		
		autores.stream().forEach(System.out::println);
		
	}

	private void listarLivros() {
		
		List<Livro> livros = repositorio.buscarTodosLivros();
		
		livros.stream().forEach(System.out::println);
		
	}

	private void buscaLivro() {
		
		System.out.println("Nome do livro: ");
		var nomeLivro = leitura.nextLine();
		
		String json = consumindo.busca(nomeLivro);
		
		System.out.println(json);
		
		var dados = converte.obterDados(json, Dados.class);
		
		Optional<DadosLivro> buscaLivro = dados.livros().stream().findFirst();
		
		System.out.println(buscaLivro);
		
		if(buscaLivro.isPresent()) {
			
			try {
				//dadosLivro em lista de objeto livro
				List<Livro> encontraLivro = buscaLivro.stream()
						.map(a -> new Livro(a))
						.collect(Collectors.toList());
				
				System.out.println(encontraLivro);
				
				//Autor a partir dos dados da API
				Autor novoAutor = buscaLivro.stream()
						.flatMap(l -> l.autor().stream())
						.map(a -> new Autor(a))
						.collect(Collectors.toList()).stream().findFirst().get();
				
				System.out.println(novoAutor);
				
				//busca db nome do autor
				Optional<Autor> dbAutor = repositorio.buscarAutorPorNome(buscaLivro.get().autor().stream()
						.map(a -> a.nome())
						.collect(Collectors.joining()));
				
				System.out.println(dbAutor);
				
				// busca do db o livro
				Optional<Livro> dbLivro = repositorio.buscarLivroPorNome(nomeLivro);
				
				System.out.println(dbLivro);
				
				if(dbLivro.isPresent()) {
					System.out.println("Esse livro ja foi registrado ! ");
				}else {
					System.out.println("chegou aqui.... ");
					Autor autor;
					if(dbAutor.isPresent()) {
						System.out.println("Esse Autor ja esta cadastrado ! ");
						autor = dbAutor.get();
					}else {
						autor = novoAutor;
						System.out.println(autor);
						repositorio.save(autor);
					}
					
					//Associar livros ao autor
                    autor.setLivros(encontraLivro);
                    System.out.println("-----------------------------");
                    System.out.println("colacondo livro: "+encontraLivro);
                    
                    // Persistir o autor com os livros associados
                    repositorio.save(autor);
				}
				
			}catch(Exception e) {
				System.out.println("deu erro dog"+e);
			}
			
		}else {
			
		}

	}

}


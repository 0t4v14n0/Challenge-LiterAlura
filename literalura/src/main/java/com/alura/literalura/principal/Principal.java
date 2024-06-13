package com.alura.literalura.principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.console.ConsolePrinters;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Dados;
import com.alura.literalura.model.DadosLivro;
import com.alura.literalura.model.Idioma;
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
        	
    		ConsolePrinters.menu();
    		
            opcao = leitura.nextInt();
            leitura.nextLine();
            
            switch (opcao) {
            case 0:
            	ConsolePrinters.fim();
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
            	listarAutoresVivos();
            	break;
            case 5:
            	ListarLivrosIdiomas();
            	break;
            case 6:
            	break;
            case 7:
            	top10();
            	break;
            case 8:
            	break;
            case 9:
            	break;
            default:
            	ConsolePrinters.opcaoInvalida();
            	break;
            }
        	
        }while(opcao != 0);
		
	}

	private void top10() {
		
		List<Livro> top10 = repositorio.top10Livros();
		
		top10.stream().forEach(System.out::println);
		
	}

	private void ListarLivrosIdiomas() {
		
		ConsolePrinters.idiomaEscolha();
		var opcaoIdioma = leitura.nextInt();
		
		Idioma idioma ;
        
	    switch (opcaoIdioma) {
        case 1:
            idioma = Idioma.PT;
            break;
        case 2:
            idioma = Idioma.EN;
            break;
        case 3:
            idioma = Idioma.ES;
            break;
        case 4:
            idioma = Idioma.FR;
            break;
        default:
            ConsolePrinters.opcaoInvalida();
            return;
    }
    
    List<Livro> livroPorIdioma = repositorio.buscarLivrosIdioma(idioma);
    livroPorIdioma.stream().forEach(System.out::println);	
    
	}

	private void listarAutoresVivos() {
		
		ConsolePrinters.vivoMorto();
		var vivoOuMorto = leitura.nextInt();
		
		
		List<Autor> vivos = repositorio.buscarAutoresVivosNoAno(vivoOuMorto);
		
		if(vivos.isEmpty()) {
			ConsolePrinters.autorVivo();
		}else {
			vivos.stream().forEach(System.out::println);
		}
		
	}

	private void listarAutores() {
		
		List<Autor> autores = repositorio.findAll();
		
		if(autores.isEmpty()) {
			ConsolePrinters.livroVazio();
		}else {
			autores.stream().forEach(System.out::println);
		}
		
	}

	private void listarLivros() {
		
		List<Livro> livros = repositorio.buscarTodosLivros();
		
		livros.stream().forEach(System.out::println);
		
	}

	private void buscaLivro() {
		
		ConsolePrinters.nomeLivro();
		var nomeLivro = leitura.nextLine();
		
		String json = consumindo.busca(nomeLivro);
		
		var dados = converte.obterDados(json, Dados.class);
		
		Optional<DadosLivro> buscaLivro = dados.livros().stream().findFirst();
		
		if(buscaLivro.isPresent()) {
			
			try {
				
				//dadosLivro em lista de objeto livro
				List<Livro> encontraLivro = buscaLivro.stream()
						.map(a -> new Livro(a))
						.collect(Collectors.toList());
				
				//Autor a partir dos dados da API
				Autor novoAutor = buscaLivro.stream()
						.flatMap(l -> l.autor().stream())
						.map(a -> new Autor(a))
						.collect(Collectors.toList()).stream().findFirst().get();
				
				//busca db nome do autor
				Optional<Autor> dbAutor = repositorio.buscarAutorPorNome(buscaLivro.get().autor().stream()
						.map(a -> a.nome())
						.collect(Collectors.joining()));
				
				// busca do db o livro
				Optional<Livro> dbLivro = repositorio.buscarLivroPorNome(nomeLivro);
				
				if(dbLivro.isPresent()) {
					ConsolePrinters.livroExistente();
				}else {
					Autor autor;
					if(dbAutor.isPresent()) {
						
						autor = dbAutor.get();
						
					}else {
						autor = novoAutor;
						System.out.println(autor);
						repositorio.save(autor);
					}
					
					//Associar livros ao autor
                    autor.setLivros(encontraLivro);
                    
                    // Persistir o autor com os livros associados
                    repositorio.save(autor);
				}
				
			}catch(Exception e) {
				System.out.println("deu erro dog "+e);
			}
			
		}else {
			
		}

	}

}


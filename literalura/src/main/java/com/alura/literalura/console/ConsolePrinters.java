package com.alura.literalura.console;

public class ConsolePrinters {
	
	public static void menu () {
		
		System.out.println("""	
				  
				-------------------LiteAlura------------------
				
				1 - Buscar Livro Pelo Titulo
				2 - Lista Livros Registrados
				3 - Listar Autores Registrados
				4 - Listar Autores Vivos Em Determinado Ano
				5 - Listar Livros Em Determinado Idioma
				6 - Estatisticas
				7 - TOP 10 livros Mais baixados
				8 - Buscar Autor Por Nome
				9 - 
				
				0 - Sair 
				----------------------------------------------
				                                     
                """);
	}
	
	public static void nomeLivro() {

		System.out.println("""	  
				------------------------------------
				Nome do livro:   
				------------------------------------                   
                """);
	}
	
	public static void vivoMorto() {
		System.out.println("""	  
				------------------------------------
				Qual o ano para a busca ?   
				------------------------------------                   
                """);
	}
	
	public static void livroExistente() {
		System.out.println("""	  
				------------------------------------
				Esse livro ja foi registrado !   
				------------------------------------                   
                """);
	}
	
	public static void opcaoInvalida() {
		System.out.println("""	  
				------------------------------------
				Opcao Invalida !   
				------------------------------------                   
                """);
	}
	
	public static void fim() {
		System.out.println("""	  
				------------------------------------
				Obrigado por usar LiterAlura !   
				------------------------------------                   
                """);
	}
	
	public static void autorVivo() {
		System.out.println("""	  
				------------------------------------
				Nenhum autor encontrado nesse ano !   
				------------------------------------                   
                """);
	}
	
	public static void livroVazio() {
		System.out.println("""	  
				------------------------------------
				Nenhum livro registrado no banco !   
				------------------------------------                   
                """);
	}
	
	public static void idiomaEscolha () {
		
		System.out.println("""	
				  
				-------------------LiteAlura------------------
				
				Escolha um idioma:
				
				1 - pt
				2 - en
				3 - fr
				4 - es
 
				----------------------------------------------
				                                     
                """);
	}
	

			

}

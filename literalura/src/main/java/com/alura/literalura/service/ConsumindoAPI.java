package com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

@Component
public class ConsumindoAPI {
		
	private static final String BASE_URL = "https://gutendex.com/books?search=";
	
    public String busca(String livro) { 

        String termoBuscaEncoded = URLEncoder.encode(livro, StandardCharsets.UTF_8);

        String endereco = BASE_URL + termoBuscaEncoded;

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null;
		try {
			response = client
			        .send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
                        
        String retorno = response.body();

        return retorno;
        
    }

}

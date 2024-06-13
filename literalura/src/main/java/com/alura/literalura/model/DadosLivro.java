package com.alura.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro (@JsonAlias("title") String titulo,
							@JsonAlias("download_count") String download,
							@JsonAlias("languages") List<String> idioma,
							@JsonProperty("authors") List<DadosAutor> autor) {}

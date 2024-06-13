package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonProperty("name") String nome,
        @JsonProperty("birth_year") int dataNascimento,
        @JsonProperty("death_year") int dataMorte){}


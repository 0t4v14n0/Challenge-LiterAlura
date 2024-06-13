package com.alura.literalura.model;

public enum Idioma {
	PT("pt"),
	EN("en"),
	FR("fr"),
	ES("es");

	private String idioma;

	Idioma(String idioma) {
		this.idioma = idioma;
	}
	
	public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idioma.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado " + text);
	}

}

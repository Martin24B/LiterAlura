package com.literAlura.Data;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorList implements JsonConverter {
    private List<Author> authors;

    public AuthorList(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BookListData data = objectMapper.readValue(json, BookListData.class);
     
            this.authors = data.getResults().stream()
                .flatMap(result -> result.authors().stream())
                .distinct() 
                .collect(Collectors.toList());

        } catch (Exception e) {
            throw new IllegalArgumentException("Error al procesar el JSON de autores: " + e.getMessage());
        }
    }

    @Override
    public String printResponse() {
        StringBuilder responseText = new StringBuilder();
        responseText.append("Lista de autores:\n");

        if (this.authors != null && !this.authors.isEmpty()) {
            for (Author author : authors) {
                responseText.append(formatAuthor(author)).append("\n");
            }
        } else {
            responseText.append("No se encontraron autores.\n");
        }

        return responseText.toString();
    }

    private String formatAuthor(Author author) {
        StringBuilder authorText = new StringBuilder();
        authorText.append("Nombre: ").append(author.name()).append("\n");
        authorText.append("Año de nacimiento: ").append(author.birthYear()).append("\n");
        authorText.append("Año de fallecimiento: ").append(author.deathYear() != null ? author.deathYear() : "Desconocido");

        return authorText.toString();
    }

    public List<Author> getLivingAuthorsInYear(int year) {
        return authors.stream()
                .filter(author -> (author.deathYear() == null || author.deathYear() >= year) && author.birthYear() <= year)
                .collect(Collectors.toList());
    }
}

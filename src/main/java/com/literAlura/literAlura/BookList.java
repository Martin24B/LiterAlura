package com.literAlura.literAlura;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literAlura.model.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


public class BookList {
    private List<Book> books;

    public BookList(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            BookListData data = objectMapper.readValue(json, BookListData.class);

            this.books = data.getResults().stream()
                    .map(result -> new Book(
                            result.id(),
                            result.title(),
                            result.authors().stream()
                                    .map(a -> new Author(a.name(), a.birthYear(), a.deathYear())) 
                                    .collect(Collectors.toList()), 
                            result.languages().isEmpty() ? "Desconocido" : result.languages().get(0),
                            result.downloadCount()
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new IllegalArgumentException("Error al procesar el JSON: " + e.getMessage());
        }
    }

   
    public Book getBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.title().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null); 
    }

   
    public Author getAuthorByBookTitle(String title) {
        Book book = getBookByTitle(title);
        if (book != null && !book.authors().isEmpty()) {
            return book.authors().get(0);  
        }
        return null;  
    }

    public String listBooks() {
        StringBuilder responseText = new StringBuilder();
        responseText.append("Lista de libros:\n");

        if (this.books != null && !this.books.isEmpty()) {
            for (Book book : books) {
                responseText.append(formatBook(book)).append("\n");
            }
        } else {
            responseText.append("No se encontraron libros.\n");
        }

        return responseText.toString();
    }

    public String searchBooksByTitle(String title) {
        StringBuilder responseText = new StringBuilder();
        responseText.append("Resultados de búsqueda para el título: ").append(title).append("\n");

        List<Book> filteredBooks = books.stream()
                .filter(book -> book.title().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

        if (!filteredBooks.isEmpty()) {
            for (Book book : filteredBooks) {
                responseText.append(formatBook(book)).append("\n");
            }
        } else {
            responseText.append("No se encontraron libros con ese título.\n");
        }

        return responseText.toString();
    }

    public String listAuthors() {
        StringBuilder responseText = new StringBuilder();
        responseText.append("Lista de autores:\n\n");

        Set<Author> authorSet = books.stream()
                .flatMap(book -> book.authors().stream())
                .collect(Collectors.toSet());

        if (!authorSet.isEmpty()) {
            for (Author author : authorSet) {
                responseText.append(author.name()).append("\n\n");
            }
        } else {
            responseText.append("No se encontraron autores.\n");
        }

        return responseText.toString();
    }

    public String listLivingAuthors(int year) {
        StringBuilder responseText = new StringBuilder();
        responseText.append("Lista de autores vivos en el año ").append(year).append(":\n");

        List<String> livingAuthors = books.stream()
                .flatMap(book -> book.authors().stream())
                .filter(author ->
                        author.birthYear() <= year &&
                        (author.deathYear() == null || author.deathYear() >= year)
                )
                .map(Author::name)
                .distinct()
                .collect(Collectors.toList());

        if (!livingAuthors.isEmpty()) {
            for (String author : livingAuthors) {
                responseText.append(author).append("\n");
            }
        } else {
            responseText.append("No se encontraron autores vivos en ese año.\n");
        }

        return responseText.toString();
    }

    private String formatBook(Book book) {
        StringBuilder bookText = new StringBuilder();
        bookText.append("\n\nTítulo: ").append(book.title()).append("\n");
        bookText.append("Autores: ").append(book.authors().stream().map(Author::name).collect(Collectors.joining(", "))).append("\n");
        bookText.append("Idioma: ").append(book.language()).append("\n");
        bookText.append("Descargas: ").append(book.downloadCount());

        return bookText.toString();
    }
}

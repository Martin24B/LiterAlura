package com.literAlura.controller;

import com.literAlura.literAlura.BookService;
import com.literAlura.model.BookRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public void addBook(@RequestBody BookRequest bookRequest) {
        bookService.saveBookWithAuthor(bookRequest.getBook(), bookRequest.getAuthor());
    }

    @GetMapping("/count/{language}")
    public String getBooksCountByLanguage(@PathVariable String language) {
        long count = bookService.countBooksByLanguage(language);
        return "Cantidad de libros en " + language + ": " + count;
    }

    @GetMapping("/authors/living/{year}")
    public String getLivingAuthorsByYear(@PathVariable int year) {
        return bookService.getLivingAuthorsByYear(year);
    }
}

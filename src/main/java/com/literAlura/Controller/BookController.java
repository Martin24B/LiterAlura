package com.literAlura.Controller;

import com.literAlura.Data.Book;
import com.literAlura.Data.Author;
import com.literAlura.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public void addBook(@RequestBody Book book, @RequestBody Author author) {
        bookService.saveBookWithAuthor(book, author);
    }
}

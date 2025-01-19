
package com.literAlura.Service;

import com.literAlura.Data.Author;

import com.literAlura.Data.Book;
import com.literAlura.Model.*; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void saveBookWithAuthor(Book book, Author author) {
       
        Author savedAuthor = authorRepository.save(author);

        book.setAuthor(savedAuthor);
        bookRepository.save(book);
    } 
}

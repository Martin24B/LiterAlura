package com.literAlura.literAlura;

import com.literAlura.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void saveBookWithAuthor(Book book, Author author) {
        AuthorData savedAuthor = authorRepository.save(new AuthorData(author.name(), author.birthYear(), author.deathYear()));

        BookData bookData = new BookData();
        bookData.setTitle(book.title());
        bookData.setAuthor(savedAuthor);
        bookData.setLanguage(book.language());
        bookData.setDownloadCount(book.downloadCount());
        bookRepository.save(bookData);
    }

    public long countBooksByLanguage(String language) {
        return bookRepository.countByLanguage(language);
    }

    public String getLivingAuthorsByYear(int year) {
        if (year <= 0) {
            return "Año inválido.";
        }

        List<AuthorData> authors = authorRepository.findByBirthYearBeforeAndDeathYearAfterOrDeathYearNull(year, year);
        if (authors.isEmpty()) {
            return "No se encontraron autores vivos en el año " + year + ".";
        }

        StringBuilder response = new StringBuilder();
        response.append("Autores vivos en el año ").append(year).append(":\n");
        for (AuthorData author : authors) {
            response.append("Nombre: ").append(author.getName()).append("\n");
            response.append("Año de nacimiento: ").append(author.getBirthYear()).append("\n");
            response.append("Año de fallecimiento: ").append(author.getDeathYear() != null ? author.getDeathYear() : "Vivo").append("\n\n");
        }

        return response.toString();
    }
}

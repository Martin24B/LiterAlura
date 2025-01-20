package com.literAlura.literAlura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.literAlura.external.Client;
import com.literAlura.external.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private BookService bookService;  

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Client client = new Client();
        client.setUri("books");
        client.sendRequest();

        BookList bookList = new BookList(client.getResponse());

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println(Resource.showMenu());

            String option = "";
            while (!Resource.optionValid(option)) {
                option = scanner.nextLine();
                if (!Resource.optionValid(option)) {
                    System.out.println("Opción inválida. Por favor ingrese un número válido.");
                }
            }

            int selectedOption = Integer.parseInt(option);
            System.out.println("\nOpción seleccionada: " + selectedOption);

            switch (selectedOption) {
                case 1:
                    System.out.println("Búsqueda de libro por título:");
                    System.out.println("Ingrese el título del libro a buscar:");
                    String title = scanner.nextLine();
                    String bookInfo = bookList.searchBooksByTitle(title);
                    System.out.println(bookInfo);

                    if (bookList.getBookByTitle(title) != null) {
                        System.out.println("¿Desea guardar este libro en la base de datos? Ingrese 1 para sí, 0 para no.");
                        String saveOption = scanner.nextLine().trim();
                        if ("1".equals(saveOption)) {
                             bookService.saveBookWithAuthor(bookList.getBookByTitle(title),
                             bookList.getAuthorByBookTitle(title));
                            System.out.println("Libro guardado correctamente.");
                        } else {
                            System.out.println("El libro no ha sido guardado.");
                        }
                    } else {
                        System.out.println("El libro no existe en los resultados.");
                    }
                    break;

                case 2:
                    System.out.println(bookList.listBooks());
                    break;

                case 3:
                    System.out.println(bookList.listAuthors());
                    break;

                case 4:
                    System.out.println("Ingrese el año para listar autores vivos:");
                    String yearInput = scanner.nextLine();
                    try {
                        int year = Integer.parseInt(yearInput);
                        System.out.println(bookService.getLivingAuthorsByYear(year));
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un año válido.");
                    }
                    break;

                case 5:
                    System.out.println("Ingrese el idioma para contar los libros en ese idioma:");
                    String language = scanner.nextLine();
                    System.out.println("Cantidad de libros en el idioma " + language + ": " +
                            bookService.countBooksByLanguage(language));
                    break;

                default:
                    System.out.println("Opción no implementada.");
                    break;
            }

            System.out.println("\n¿Desea realizar otra operación? Ingrese 1 para sí, 0 para no.");
            String continueOption = scanner.nextLine().trim();
            if (!"1".equals(continueOption)) {
                running = false;
            }
        }
        scanner.close();
        System.out.println("¡Gracias por usar el buscador de libros del Proyecto literAlura!");
    }
}

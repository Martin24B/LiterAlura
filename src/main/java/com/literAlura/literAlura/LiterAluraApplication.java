package com.literAlura.literAlura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.literAlura.ApiConfig.Client;
import com.literAlura.ApiConfig.Resource;
import com.literAlura.Data.BookList;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }
 
    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Resource.showMenu());

        String option = "";
        while (!Resource.optionValid(option)) {
            option = scanner.nextLine();
            if (!Resource.optionValid(option)) {
                System.out.println("Opción inválida. Por favor ingrese un número válido.");
            }
        }

        int selectedOption = Integer.parseInt(option) - 1;
        System.out.println("\nOpción seleccionada: " + (selectedOption + 1));

        switch (selectedOption) {
            case 0:
                System.out.println(Resource.listOfRequest(Resource.BOOK_LIST.name().toLowerCase()));
              
                String internalOption = "";
                while (!Resource.optionValid(internalOption)) {
                    internalOption = scanner.nextLine();
                    if (!Resource.optionValid(internalOption)) {
                        System.out.println("Opción inválida. Por favor ingrese un número válido.");
                    }
                }
          
                Client client = new Client();
                client.setUri("books");
                client.sendRequest();
            
                BookList bookList = new BookList(client.getResponse());
         
                switch (internalOption) {
                    case "1":
                        System.out.println("Búsqueda de libro por título:");
                        System.out.println("Ingrese el título del libro a buscar:");
                        String title = scanner.nextLine();
                        System.out.println(bookList.searchBooksByTitle(title));
                        break;
                    case "2":                 
                        System.out.println(bookList.listBooks());
                        break;
                    case "3":
                        System.out.println(bookList.listAuthors());
                        break;
                    case "4":
                        System.out.println("Ingrese el año para listar autores vivos:");
                        String yearInput = scanner.nextLine();
                        try {
                            int year = Integer.parseInt(yearInput);
                            System.out.println(bookList.listLivingAuthors(year));
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, ingrese un año válido.");
                        }
                        break;

                    default:
                        System.out.println("Opción no implementada.");
                        break;
                }
                break;

            default:
                System.out.println("Opción no implementada.");
        }

        scanner.close();
    }
}

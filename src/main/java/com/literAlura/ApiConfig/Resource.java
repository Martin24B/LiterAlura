package com.literAlura.ApiConfig;

public enum Resource {
    BOOK_LIST("listado de todos los libros, búsqueda por título, listado de autores, autores vivos en un determinado año."),
    AUTHOR_YEAR("Obtener libros dependiendo del año de publicación."),
    COPYRIGHT("Filtrar libros por estado de copyright: verdadero, falso o sin información."),
    IDS("Listar libros usando los números de identificación del Proyecto Gutenberg."),
    LANGUAGES("Filtrar libros según el idioma o idiomas proporcionados."),
    MIME_TYPE("Filtrar libros según su tipo MIME."),
    SEARCH("Buscar libros por nombre de autor o título."),
    SORT("Ordenar libros según diferentes criterios: ascendente, descendente o por popularidad."),
    TOPIC("Buscar libros por tema o estantería."),
    BOOK_DETAILS("Obtener detalles de un libro específico por su ID de Proyecto Gutenberg.");

    private final String description;

    private Resource(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\nBIENVENIDOS AL BUSCADOR DE LIBROS DEL PROYECTO GUTENBERG\n");
        menu.append("\nSeleccione una opción para realizar una operación:\n\n");

        int index = 1;
        for (Resource resource : Resource.values()) {
            menu.append(index++)
                .append(") ")
                .append(resource.getDescription())
                .append("\n");
        }

        menu.append("\nSeleccione una opción: ");
        return menu.toString();
    }

    public static int posResource(String value) {
        for (Resource resource : Resource.values()) {
            if (resource.name().toLowerCase().equals(value)) {
                return resource.ordinal() + 1;
            }
        }
        return -1;  
    }

    public static boolean resourceValid(String value) {
        for (Resource resource : Resource.values()) {
            if (resource.name().toLowerCase().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean optionValid(String option) {
        try {
            int opt = Integer.parseInt(option);
            return opt >= 1 && opt <= Resource.values().length;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String listOfRequest(String resource) {
        String listOfRequest = "";

        if (resource.equals(BOOK_LIST.name().toLowerCase())) {
            listOfRequest =
                    """
                        \nOperaciones disponibles para la opción '1':
                        1) Búsqueda de libro por título.
                        2) Lista de todos los libros.
                        3) Lista de autores.
                        4) Listar autores vivos en determinado año.
                    """;
            return listOfRequest;
        }

        return listOfRequest;
    }
}

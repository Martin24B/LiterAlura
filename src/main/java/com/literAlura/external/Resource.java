package com.literAlura.external;

public enum Resource {
    BOOK_SEARCH("Búsqueda de libro por título."),
    BOOK_LIST("Lista de todos los libros."),
    AUTHOR_LIST("Lista de autores."),
    LIVE_AUTHORS("Autores vivos en un determinado año.");

    private final String description;

    private Resource(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\nBIENVENIDOS AL BUSCADOR DE LIBROS literAlura\n");
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
}

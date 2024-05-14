package com.ecommerce.bookinventory.model;

public enum Genre {
    VALUE1("Friction"),
    VALUE2("Thriller"),
    VALUE3("Mystery"),
    VALUE4("Poetry"),
    VALUE5("Horror"),
    VALUE6("Satire");

    private final String stringValue;

    Genre(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public static String validateString(String text) {
        for (Genre genre : Genre.values()) {
            if (genre.stringValue.equalsIgnoreCase(text)) {
                return genre.stringValue;
            }
        }
        throw new IllegalArgumentException(" No constant with text " + text + " found");
    }
}

package org.loose.fis.sre.exceptions;

public class ProductAlreadyExistsException extends Exception {

    public ProductAlreadyExistsException(String description) {
        super("A product with the same description already exists!");
    }
}

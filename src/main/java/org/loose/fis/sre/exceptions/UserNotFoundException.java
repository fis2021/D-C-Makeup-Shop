package org.loose.fis.sre.exceptions;

public class UserNotFoundException  extends Exception {
    public UserNotFoundException() {
        super("The user with the given credentials was not found!");
    }
}

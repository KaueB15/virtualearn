package br.com.fafic.virtualearn.exceptions;

public class LoginNotFound extends RuntimeException{

    public LoginNotFound() {
        super("ERROR => LOGIN NOT FOUND");
    }

}

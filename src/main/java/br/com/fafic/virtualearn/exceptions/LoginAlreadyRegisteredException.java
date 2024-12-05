package br.com.fafic.virtualearn.exceptions;

public class LoginAlreadyRegisteredException extends RuntimeException{

    public LoginAlreadyRegisteredException(){

        super("ERROR => LOGIN ALREADY REGISTERED");

    }

}

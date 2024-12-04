package br.com.fafic.virtualearn.exceptions;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException(){
        super("ERROR => INVALID DATE");
    }
}

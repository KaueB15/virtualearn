package br.com.fafic.virtualearn.exceptions;

public class InvalidPhoneNumberException extends RuntimeException{
    public InvalidPhoneNumberException(){
        super("ERROR => INVALID PHONENUMBER");
    }
}

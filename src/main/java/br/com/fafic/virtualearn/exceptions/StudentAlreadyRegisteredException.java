package br.com.fafic.virtualearn.exceptions;

public class StudentAlreadyRegisteredException extends RuntimeException {

    public StudentAlreadyRegisteredException(){

        super("ERROR => STUDENT ALREADY REGISTERED");

    }

}

package br.com.fafic.virtualearn.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("ERROR => USER NOT FOUND");
    }
}

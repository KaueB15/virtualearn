package br.com.fafic.virtualearn.exceptions;

public class FieldIsNullException extends RuntimeException {

    public FieldIsNullException() {
        super("ERROR => FIELD IS NULL");
    }

}

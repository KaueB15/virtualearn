package br.com.fafic.virtualearn.exceptions;

public class ContractNotFoundException extends RuntimeException{
    public ContractNotFoundException() {
        super("ERROR => CONTRACT NOT FOUND");
    }
}

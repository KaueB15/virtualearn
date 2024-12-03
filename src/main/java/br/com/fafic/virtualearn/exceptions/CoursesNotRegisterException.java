package br.com.fafic.virtualearn.exceptions;

public class CoursesNotRegisterException extends RuntimeException{

    public CoursesNotRegisterException(){
        super("ERROR => COURSE NOT REGISTER");
    }

}

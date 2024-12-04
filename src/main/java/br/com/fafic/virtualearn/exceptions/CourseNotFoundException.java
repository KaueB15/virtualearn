package br.com.fafic.virtualearn.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException() {
        super("ERROR => COURSE NOT FOUND");
    }
}

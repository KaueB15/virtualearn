package br.com.fafic.virtualearn.exceptions;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException(){
        super("ERROR => TEACHER NOT FOUND");
    }
}

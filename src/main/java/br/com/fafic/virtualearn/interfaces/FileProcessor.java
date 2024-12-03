package br.com.fafic.virtualearn.interfaces;

import br.com.fafic.virtualearn.model.Rating;
import br.com.fafic.virtualearn.model.Student;
import br.com.fafic.virtualearn.model.Teacher;

public interface FileProcessor {
    void generateFile(Rating rating, String filePath, Teacher teacher, Student student);
}

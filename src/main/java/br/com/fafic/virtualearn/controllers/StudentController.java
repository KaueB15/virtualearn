package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.StudentDAO;
import br.com.fafic.virtualearn.exceptions.StudentNotFoundException;
import br.com.fafic.virtualearn.model.Student;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class StudentController {

    private StudentDAO studentDAO = new StudentDAO();

    public void createNewStudent(String name, String email, String phoneNumber, LocalDate date){
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setDateOfBirth(date);
        student.setPhoneNumber(phoneNumber);

        studentDAO.registerStudent(student);
    }

    public Student getStudentById(UUID id){
        Student student = studentDAO.getByID(id);

        if(student == null){
            throw new StudentNotFoundException();
        }

        return student;
    }

}

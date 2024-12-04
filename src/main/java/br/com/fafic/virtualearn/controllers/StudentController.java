package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.LoginDAO;
import br.com.fafic.virtualearn.dao.StudentDAO;
import br.com.fafic.virtualearn.exceptions.FieldIsNullException;
import br.com.fafic.virtualearn.exceptions.InvalidCpfException;
import br.com.fafic.virtualearn.exceptions.InvalidPhoneNumberException;
import br.com.fafic.virtualearn.exceptions.StudentNotFoundException;
import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.model.Student;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class StudentController {

    private StudentDAO studentDAO = new StudentDAO();

    private LoginDAO loginDAO = new LoginDAO();

    public boolean createNewStudent(String name, String email, String phoneNumber, LocalDate date, Login login, String cpf){
        try{

            if (fieldValidation(name, email, phoneNumber, date, cpf)){
                throw new FieldIsNullException();
            }

            if (cpfValidation(cpf)){
                throw new InvalidCpfException();
            }

            if (phoneNumberValidation(phoneNumber)){
                throw new InvalidPhoneNumberException();
            }

            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setDateOfBirth(date);
            student.setPhoneNumber(phoneNumber);
            student.setLogin(login);
            student.setCpf(cpf);

            studentDAO.registerStudent(student);

            return true;

        }catch (FieldIsNullException e) {
            System.err.println(e.getMessage());
            loginDAO.deleteLogin(login);
            return false;
        }catch (InvalidCpfException | InvalidPhoneNumberException e){
            loginDAO.deleteLogin(login);
            System.err.println(e.getMessage());
            return false;
        } catch (RuntimeException e){
            loginDAO.deleteLogin(login);
            return false;
        }
    }

    public Student getStudentById(UUID id){
        try {
            Student student = studentDAO.getStudentByID(id);

            if(student == null){
                throw new StudentNotFoundException();
            }
            return student;
        }catch (StudentNotFoundException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Student findByStudent(String student){
        try{
            Student students = studentDAO.findByStudent(student);

            if (students == null){
                throw new StudentNotFoundException();
            }
            return students;
        }catch (StudentNotFoundException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    private boolean fieldValidation(String name, String email, String phoneNumber, LocalDate date, String cpf){
        return name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || cpf.isEmpty() || date == null;
    }

    private boolean cpfValidation(String cpf){
        return cpf.length() < 11;
    }

    private boolean phoneNumberValidation(String phoneNumber){
        return  phoneNumber.length() < 11;
    }

    public boolean dateValidation(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        if (date.isAfter(now)) {
            return false;
        }
        int age = Period.between(date.toLocalDate(), now.toLocalDate()).getYears();
        return age >= 16;
    }

}

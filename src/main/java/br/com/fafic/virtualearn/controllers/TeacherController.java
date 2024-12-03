package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.LoginDAO;
import br.com.fafic.virtualearn.dao.TeacherDAO;
import br.com.fafic.virtualearn.exceptions.FieldIsNullException;
import br.com.fafic.virtualearn.exceptions.InvalidCpfException;
import br.com.fafic.virtualearn.exceptions.InvalidPhoneNumberException;
import br.com.fafic.virtualearn.exceptions.TeacherNotRegisterException;
import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.model.Teacher;

import java.time.LocalDate;
import java.util.List;

public class TeacherController {

    private TeacherDAO teacherDAO = new TeacherDAO();

    private LoginDAO loginDAO = new LoginDAO();

    public boolean createNewTeacher(String name, String email, String phoneNumber, LocalDate date, Login login, String cpf, String matter){
        try{

            if (fieldValidation(name, email, phoneNumber, date, cpf, matter)){
                throw new FieldIsNullException();
            }

            if (cpfValidation(cpf)){
                throw new InvalidCpfException();
            }

            if (phoneNumberValidation(phoneNumber)){
                throw new InvalidPhoneNumberException();
            }
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setEmail(email);
            teacher.setDateOfBirth(date);
            teacher.setPhoneNumber(phoneNumber);
            teacher.setLogin(login);
            teacher.setCpf(cpf);
            teacher.setMatter(matter);

            teacherDAO.registerTeacher(teacher);

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

    private boolean fieldValidation(String name, String email, String phoneNumber, LocalDate date, String cpf, String matter){
        return name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || cpf.isEmpty() || date == null || matter.isEmpty();
    }

    private boolean cpfValidation(String cpf){
        return cpf.length() < 11;
    }

    private boolean phoneNumberValidation(String phoneNumber){
        return  phoneNumber.length() < 11;
    }

    public List<Teacher> getAllTeachers(){

        List<Teacher> teachers = null;

        try{

            teachers = teacherDAO.getAllTeacher();

            if(teachers.isEmpty()){
                throw new TeacherNotRegisterException();
            }

            return teachers;

        }catch (TeacherNotRegisterException e){
            System.err.println(e.getMessage());
            return teachers;
        }

    }

}

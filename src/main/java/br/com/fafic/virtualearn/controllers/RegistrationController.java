package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.RegistrationDAO;
import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.model.Registration;
import br.com.fafic.virtualearn.model.Student;
import br.com.fafic.virtualearn.model.Teacher;

import java.time.LocalDate;
import java.util.UUID;

public class RegistrationController {

    private RegistrationDAO registrationDAO = new RegistrationDAO();

    public void createRegistration(Course course, Student student, int duration){
        Registration registration = new Registration();

        LocalDate dateNow = LocalDate.now();

        registration.setCourse(course);
        registration.setStudent(student);
        registration.setRegisterDay(dateNow);
        registration.setEndRegisterDay(dateNow.plusYears(duration));
        registration.setRegistrationNumber(UUID.randomUUID());

        registrationDAO.registerRegistration(registration);

    }

}

package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.RegistrationDAO;
import br.com.fafic.virtualearn.exceptions.ContractNotRegisterException;
import br.com.fafic.virtualearn.exceptions.StudentAlreadyRegisteredException;
import br.com.fafic.virtualearn.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RegistrationController {

    private RegistrationDAO registrationDAO = new RegistrationDAO();

    public boolean createRegistration(Course course, Student student, int duration){
        try {
            if(registrationValidate(student)){
                throw new StudentAlreadyRegisteredException();
            }

            Registration registration = new Registration();

            LocalDate dateNow = LocalDate.now();

            registration.setCourse(course);
            registration.setStudent(student);
            registration.setRegisterDay(dateNow);
            registration.setEndRegisterDay(dateNow.plusYears(duration));
            registration.setRegistrationNumber(UUID.randomUUID());

            registrationDAO.registerRegistration(registration);
            return true;
        }catch (StudentAlreadyRegisteredException e){
            System.err.println(e.getMessage());
            return false;
        }



    }

    public void deleteRegistrationByCourse(Course course){
        List<Registration> registrations = registrationDAO.getAllRegistrations();

        if(!registrations.isEmpty()){
            for (Registration registration : registrations){
                if(course.getId().equals(registration.getCourse().getId())){
                    registrationDAO.deleteRegistrationById(registration.getId());
                    System.out.println("DELETE CONTRACT");
                }
            }
        }
    }

    private boolean registrationValidate(Student student){
        List<Registration> registrations = registrationDAO.getAllRegistrations();

        if (registrations.isEmpty()){
            return false;
        }
        for (Registration registration : registrations){
            if(registration.getStudent().equals(student)){
                return true;
            }
        }
        return false;
    }

    public List<Registration> getAllRegistration(){
        List<Registration> registrations = null;

        try {

            registrations = registrationDAO.getAllRegistrations();

            if (registrations.isEmpty()){
                throw new ContractNotRegisterException();
            }

            return registrations;

        }catch (ContractNotRegisterException e){
            System.err.println(e.getMessage());
            return registrations;
        }
    }

}

package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.RatingDAO;
import br.com.fafic.virtualearn.exceptions.TeacherNotRegisterException;
import br.com.fafic.virtualearn.model.Contract;
import br.com.fafic.virtualearn.model.Rating;
import br.com.fafic.virtualearn.model.Student;
import br.com.fafic.virtualearn.model.Teacher;

import java.util.List;

public class RatingController {

    private RatingDAO ratingDAO = new RatingDAO();

    public void createRating(Student student, Contract contract){
        Rating rating = new Rating();

        rating.setR1(0);
        rating.setR2(0);
        rating.setR3(0);
        rating.setFinalRating(0);
        rating.setTeacher(contract.getTeacher());
        rating.setStudent(student);
        rating.setStudentName(student.getName());
        rating.setMatter(contract.getMatter());
        rating.setTeacherName(contract.getTeacherName());

        ratingDAO.registerRating(rating);
    }

    public List<Rating> getAllTeachers() {

        List<Rating> ratings = null;

        try {

            ratings = ratingDAO.getAllRating();

            if (ratings.isEmpty()) {
                throw new TeacherNotRegisterException();
            }

            return ratings;

        } catch (TeacherNotRegisterException e) {
            System.err.println(e.getMessage());
            return ratings;
        }
    }

    public void updateRating(Rating rating){

        ratingDAO.updateRating(rating);

    }

}

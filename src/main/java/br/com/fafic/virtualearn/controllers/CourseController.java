package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.CourseDAO;
import br.com.fafic.virtualearn.model.Course;

public class CourseController {

    private CourseDAO courseDAO = new CourseDAO();

    public void registerNewCourse(String name, int duration){

        Course course = new Course();

        course.setName(name);
        course.setDuration(duration);

        courseDAO.registerCourse(course);

    }

}

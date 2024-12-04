package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.CourseDAO;
import br.com.fafic.virtualearn.exceptions.CourseNotFoundException;
import br.com.fafic.virtualearn.exceptions.CoursesNotRegisterException;
import br.com.fafic.virtualearn.exceptions.StudentNotFoundException;
import br.com.fafic.virtualearn.model.Course;

import java.util.List;

public class CourseController {

    private CourseDAO courseDAO = new CourseDAO();

    public void registerNewCourse(String name, int duration){

        Course course = new Course();

        course.setName(name);
        course.setDuration(duration);

        courseDAO.registerCourse(course);

    }

    public List<Course> getAllCourses(){

        List<Course> courses = null;

        try{

            courses = courseDAO.getAllCourses();

            if(courses.isEmpty()){
                throw new CoursesNotRegisterException();
            }

            return courses;

        }catch (CoursesNotRegisterException e){
            System.err.println(e.getMessage());
            return courses;
        }

    }

    public Course findByCourse(String name){
        try{
            Course course = courseDAO.findByCourse(name);

            if (course == null){
                throw new CourseNotFoundException();
            }
            return course;
        }catch (CourseNotFoundException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

}

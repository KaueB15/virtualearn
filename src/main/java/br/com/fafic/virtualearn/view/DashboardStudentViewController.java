package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.CourseController;
import br.com.fafic.virtualearn.controllers.RegistrationController;
import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.model.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;

public class DashboardStudentViewController {

    @FXML
    ComboBox<String> comboboxCourses;

    private CourseController courseController = new CourseController();

    private RegistrationController registrationController = new RegistrationController();

    @FXML
    public void onShowingComboBoxCourses(){
        comboboxCourses.getItems().clear();

        List<Course> courses = courseController.getAllCourses();

        for (Course course : courses){
            comboboxCourses.getItems().add(course.getName());
        }
    }

    @FXML
    public void onRegisterButtonClick(){
        String courseName = comboboxCourses.getValue();



        //registrationController.createRegistration();

    }

}

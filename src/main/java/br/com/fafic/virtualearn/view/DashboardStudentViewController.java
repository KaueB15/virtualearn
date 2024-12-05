package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.*;
import br.com.fafic.virtualearn.dao.ContractDAO;
import br.com.fafic.virtualearn.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class DashboardStudentViewController {

    @FXML
    ComboBox<String> comboboxCourses;

    @FXML
    Label labelStudentName;

    @FXML
    Button logoutButton;

    @FXML
    Button buttonCourse;

    protected Login studentLogged;

    protected Student studentAuthenticated;

    private CourseController courseController = new CourseController();

    private RegistrationController registrationController = new RegistrationController();

    private StudentController studentController = new StudentController();

    private RatingController ratingController = new RatingController();

    private ContractController contractController = new ContractController();

    public void setStudentLogged(Login studentLogged) {
        this.studentLogged = studentLogged;
        studentAuthenticated = studentController.getStudentByLogin(studentLogged);
        labelStudentName.setText(studentAuthenticated.getName() + ",");
    }

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

        Course course = courseController.getByCourse(courseName);

        List<Contract> contracts = contractController.getAllContracts();

        registrationController.createRegistration(course, studentAuthenticated, course.getDuration());

        for(Contract contract : contracts){
            if(contract.getCourse().equals(course)){
                ratingController.createRating(studentAuthenticated, contract);
            }
        }

    }

    @FXML
    public void onClickLogoutButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/login-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }

    @FXML
    public void onCourseButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/dashboard-student-course-view.fxml"));
        Parent loginRoot = fxmlLoader.load();

        DashboardStudentCourseViewController dashboardStudentCourseViewController = fxmlLoader.getController();
        dashboardStudentCourseViewController.setStudentAuthenticated(studentAuthenticated);

        Stage stage = (Stage) buttonCourse.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }
}

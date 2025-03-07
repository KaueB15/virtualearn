package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.*;
import br.com.fafic.virtualearn.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DashboardStudentCourseViewController {

    @FXML
    TableView<Rating> tableRatings;

    @FXML
    TableColumn<Rating, String> columnTeacher;

    @FXML
    TableColumn<Rating, String> columnMatter;

    @FXML
    TableColumn<Rating, Double> columnR1;

    @FXML
    TableColumn<Rating, Double> columnR2;

    @FXML
    TableColumn<Rating, Double> columnR3;

    @FXML
    TableColumn<Rating, Double> columnRFinal;

    @FXML
    Button logoutButton;

    @FXML
    Label labelStudentName;

    @FXML
    Label labelCourseName;

    protected Student studentAuthenticated;

    private TeacherController teacherController = new TeacherController();

    private RatingController ratingController = new RatingController();

    private StudentController studentController = new StudentController();

    private RegistrationController registrationController = new RegistrationController();

    public void setStudentAuthenticated(Student studentAuthenticated) {
        this.studentAuthenticated = studentAuthenticated;
        labelStudentName.setText(studentAuthenticated.getName());

        loadDataTable();
    }

    @FXML
    public void initialize(){
        columnMatter.setCellValueFactory(new PropertyValueFactory<>("matter"));
        columnTeacher.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        columnR1.setCellValueFactory(new PropertyValueFactory<>("r1"));
        columnR2.setCellValueFactory(new PropertyValueFactory<>("r2"));
        columnR3.setCellValueFactory(new PropertyValueFactory<>("r3"));
        columnRFinal.setCellValueFactory(new PropertyValueFactory<>("finalRating"));
    }

    private void loadDataTable(){
        tableRatings.getItems().clear();

        List<Rating> ratings = ratingController.getAllTeachers();
        List<Registration> registrations = registrationController.getAllRegistration();

        for (Rating rating : ratings) {
            if(rating.getStudent().equals(studentAuthenticated)){
                tableRatings.getItems().add(rating);
            }
        }

        for(Registration registration : registrations){
            if(registration.getStudent().equals(studentAuthenticated)){
                labelCourseName.setText(registration.getCourse().getName());
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
    public void onClickPdfGenerateButton(){
        List<Rating> ratings = ratingController.getAllTeachers();
        for (Rating rating : ratings){
            if (rating.getStudent().equals(studentAuthenticated)){
                studentController.generateStudentPdf(rating, rating.getTeacher(), rating.getStudent());
            }
        }
    }
}

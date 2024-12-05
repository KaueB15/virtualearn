package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.RatingController;
import br.com.fafic.virtualearn.controllers.TeacherController;
import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.model.Rating;
import br.com.fafic.virtualearn.model.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DashboardTeacherViewController {

    @FXML
    Label labelTeacherName;

    @FXML
    Button logoutButton;

    @FXML
    TableView<Rating> tableRatings;

    @FXML
    TableColumn<Rating, String> columnStudent;

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
    TextField fieldRating1;

    @FXML
    TextField fieldRating2;

    @FXML
    TextField fieldRating3;

    protected Login teacherLogged;

    protected Teacher teacherAuthenticated;

    private TeacherController teacherController = new TeacherController();

    private RatingController ratingController = new RatingController();

    public void setTeacherLogged(Login teacherLogged) {
        this.teacherLogged = teacherLogged;
        teacherAuthenticated = teacherController.getTeacherByLogin(teacherLogged);
        labelTeacherName.setText(teacherAuthenticated.getName() + ",");
        loadDataTable();
    }

    @FXML
    public void initialize(){
        columnMatter.setCellValueFactory(new PropertyValueFactory<>("matter"));
        columnStudent.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        columnR1.setCellValueFactory(new PropertyValueFactory<>("r1"));
        columnR2.setCellValueFactory(new PropertyValueFactory<>("r2"));
        columnR3.setCellValueFactory(new PropertyValueFactory<>("r3"));
        columnRFinal.setCellValueFactory(new PropertyValueFactory<>("finalRating"));
    }

    private void loadDataTable(){
        tableRatings.getItems().clear();

        List<Rating> ratings = ratingController.getAllTeachers();

        for (Rating rating : ratings) {
            if(rating.getTeacher().equals(teacherAuthenticated)){
                tableRatings.getItems().add(rating);
            }
        }
    }

    @FXML
    public void onEditRatingsButtonClick(){
        Rating rating = tableRatings.getSelectionModel().getSelectedItem();

        rating.setR1(!fieldRating1.getText().isEmpty() ? Double.parseDouble(fieldRating1.getText()) : 0);

        rating.setR2(!fieldRating2.getText().isEmpty() ? Double.parseDouble(fieldRating2.getText()) : 0);

        rating.setR3(!fieldRating3.getText().isEmpty() ? Double.parseDouble(fieldRating3.getText()) : 0);

        rating.calculateFinalRating();

        ratingController.updateRating(rating);


        loadDataTable();

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

}

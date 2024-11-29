package br.com.fafic.virtualearn.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardAdminViewController {

    @FXML
    Label usersText;

    @FXML
    Button registerCourseButton;

    @FXML
    Button registerTeacherButton;

    @FXML
    Button logoutButton;

    public void onClickButtonRegisterTeacher() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/register-teacher-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) registerTeacherButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }

    public void onClickButtonRegisterCourse() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/register-course-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) registerCourseButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }

    public void onClickButtonLogout(){

    }

}

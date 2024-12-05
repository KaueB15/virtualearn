package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.TeacherController;
import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.model.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardTeacherViewController {

    @FXML
    Label labelTeacherName;

    @FXML
    Button logoutButton;

    protected Login teacherLogged;

    protected Teacher teacherAuthenticated;

    private TeacherController teacherController = new TeacherController();

    public void setTeacherLogged(Login teacherLogged) {
        this.teacherLogged = teacherLogged;
        teacherAuthenticated = teacherController.getTeacherByLogin(teacherLogged);
        labelTeacherName.setText(teacherAuthenticated.getName() + ",");
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

package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.CourseController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.Buffer;

public class RegisterCourseViewController {

    @FXML
    TextField fieldName;

    @FXML
    TextField fieldDuration;

    @FXML
    Button buttonBack;

    private CourseController courseController = new CourseController();

    @FXML
    public void onRegisterCourseButtonClick() throws IOException{

        String name = fieldName.getText();
        int duration = Integer.parseInt(fieldDuration.getText());

        courseController.registerNewCourse(name, duration);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/dashboard-admin-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);

    }

    @FXML
    public void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/dashboard-admin-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }



}

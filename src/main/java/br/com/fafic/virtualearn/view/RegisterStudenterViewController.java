package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.LoginController;
import br.com.fafic.virtualearn.controllers.StudentController;
import br.com.fafic.virtualearn.dao.LoginDAO;
import br.com.fafic.virtualearn.dao.StudentDAO;
import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.model.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterStudenterViewController {

    @FXML
    TextField loginField;

    @FXML
    TextField passwordField;

    @FXML
    TextField nameField;

    @FXML
    TextField phoneField;

    @FXML
    TextField emailField;

    @FXML
    TextField cpfField;

    @FXML
    DatePicker dateField;

    @FXML
    Button registerButton;

    @FXML
    Button signinButton;

    StudentController studentController = new StudentController();

    LoginController loginController = new LoginController();

    @FXML
    public void registerStudent() throws IOException{

        String login = loginField.getText();
        String password = passwordField.getText();

        Login userLogin = loginController.createLogin(login, password, "student");

        String name = nameField.getText();
        String cpf = cpfField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneField.getText();
        LocalDate dateOfBirthday = dateField.getValue();

        if(studentController.createNewStudent(name, email,phoneNumber, dateOfBirthday, userLogin, cpf)){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/login-view.fxml"));
            Parent loginRoot = fxmlLoader.load();
            Stage stage = (Stage) signinButton.getScene().getWindow();
            Pane mainPane = (Pane) stage.getScene().getRoot();
            mainPane.getChildren().clear();
            mainPane.getChildren().add(loginRoot);
        }

    }

    @FXML
    public void onSigninButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/login-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) signinButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }


}

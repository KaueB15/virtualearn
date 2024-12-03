package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.LoginController;
import br.com.fafic.virtualearn.controllers.TeacherController;
import br.com.fafic.virtualearn.model.Login;
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

public class RegisterTeacherViewController {

    @FXML
    DatePicker fieldDate;

    @FXML
    TextField fieldName;

    @FXML
    TextField fieldEmail;

    @FXML
    TextField fieldCpf;

    @FXML
    TextField fieldPhoneNumber;

    @FXML
    TextField fieldMatter;

    @FXML
    TextField fieldLogin;

    @FXML
    TextField fieldPassword;

    @FXML
    Button registerTeacherButton;

    TeacherController teacherController = new TeacherController();

    LoginController loginController = new LoginController();

    public void onRegisterTeacherButtonClick() throws IOException {

        String login = fieldLogin.getText();
        String password = fieldPassword.getText();

        Login userLogin = loginController.createLogin(login, password, "teacher");

        String name = fieldName.getText();
        String cpf = fieldCpf.getText();
        String email = fieldEmail.getText();
        String phoneNumber = fieldPhoneNumber.getText();
        LocalDate dateOfBirthday = fieldDate.getValue();
        String matter = fieldMatter.getText();

        if(teacherController.createNewTeacher(name, email,phoneNumber, dateOfBirthday, userLogin, cpf, matter)){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/dashboard-admin-view.fxml"));
            Parent loginRoot = fxmlLoader.load();
            Stage stage = (Stage) registerTeacherButton.getScene().getWindow();
            Pane mainPane = (Pane) stage.getScene().getRoot();
            mainPane.getChildren().clear();
            mainPane.getChildren().add(loginRoot);
        }

    }

}

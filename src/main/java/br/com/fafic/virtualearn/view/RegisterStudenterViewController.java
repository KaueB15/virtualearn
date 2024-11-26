package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.LoginController;
import br.com.fafic.virtualearn.controllers.StudentController;
import br.com.fafic.virtualearn.dao.LoginDAO;
import br.com.fafic.virtualearn.dao.StudentDAO;
import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    StudentController studentController = new StudentController();

    LoginController loginController = new LoginController();

    @FXML
    public void registerStudent(){

        String login = loginField.getText();
        String password = passwordField.getText();

        Login userLogin = loginController.createLogin(login, password, "student");

        String name = nameField.getText();
        String cpf = cpfField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneField.getText();
        LocalDate dateOfBirthday = dateField.getValue();

        studentController.createNewStudent(name, email,phoneNumber, dateOfBirthday, userLogin, cpf);

    }


}

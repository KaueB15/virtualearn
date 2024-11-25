package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.dao.LoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController {

    @FXML
    Label welcomeText;

    @FXML
    TextField textField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button button;

    LoginDAO loginDAO = new LoginDAO();

}

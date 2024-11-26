package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.LoginController;
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
    TextField fieldUsername;

    @FXML
    PasswordField fieldPassword;

    @FXML
    Button button;

    LoginController loginController = new LoginController();

    public void loginValidate(){

        String userName = fieldUsername.getText();
        String password = fieldPassword.getText();

        if (loginController.validateLogin(userName, password)){
            System.out.println("LOGADO");
        }else {
            System.out.println("NÃO DEU CERTO");
        }

    }

}

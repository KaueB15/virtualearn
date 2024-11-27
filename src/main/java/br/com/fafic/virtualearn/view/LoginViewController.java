package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.LoginController;
import br.com.fafic.virtualearn.dao.LoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

    @FXML
    Label welcomeText;

    @FXML
    TextField fieldUsername;

    @FXML
    PasswordField fieldPassword;

    @FXML
    Button signupButton;

    @FXML
    TextField fieldPasswordShow;

    LoginController loginController = new LoginController();

    public void onLoginButtonClick(){

        String userName = fieldUsername.getText();
        String password = fieldPassword.getText();

        if (loginController.validateLogin(userName, password)){
            System.out.println("LOGADO");
        }else {
            System.out.println("NÃO DEU CERTO");
        }

    }

    @FXML
    public void onShowPassword(){
        String password = fieldPassword.getText();
        if (fieldPassword.isVisible()){
            fieldPassword.setVisible(false);
            fieldPasswordShow.setText(password);
            fieldPasswordShow.setVisible(true);
        }else{
            fieldPasswordShow.setVisible(false);
            fieldPassword.setText(password);
            fieldPassword.setVisible(true);
        }
    }

    @FXML
    public void onSignupButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/register-student-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) signupButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }

}

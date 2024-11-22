package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.LoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class HelloController {

    @FXML
    Label welcomeText;

    @FXML
    TextField textField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button button;

    LoginDAO loginDAO = new LoginDAO();

    public void buttonClick(){
        String text = textField.getText();
        String password = passwordField.getText();

        if (loginDAO.checkLoginn(text, password)){
            System.out.println("Logou!!!");
        }else {
            System.out.println("Deu ruim");
        }

    }

}

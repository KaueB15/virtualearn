package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.LoginController;
import br.com.fafic.virtualearn.dao.LoginDAO;
import br.com.fafic.virtualearn.model.Login;
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

    @FXML
    Label loginInvalidError;

    LoginController loginController = new LoginController();

    public void onLoginButtonClick() throws IOException{

        String userName = fieldUsername.getText();
        String password = fieldPassword.getText();

        if (loginController.validateLogin(userName, password)){
            Login userAuthenticated = loginController.findByLogin(userName);
            switch (userAuthenticated.getType()) {
                case "admin" -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/dashboard-admin-view.fxml"));
                    Parent loginRoot = fxmlLoader.load();

                    DashboardAdminViewController dashboardAdminViewController = fxmlLoader.getController();
                    dashboardAdminViewController.adminLogged = userAuthenticated;

                    Stage stage = (Stage) signupButton.getScene().getWindow();
                    Pane mainPane = (Pane) stage.getScene().getRoot();
                    mainPane.getChildren().clear();
                    mainPane.getChildren().add(loginRoot);
                }
                case "student" -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/dashboard-student-view.fxml"));
                    Parent loginRoot = fxmlLoader.load();

                    DashboardStudentViewController dashboardStudentViewController = fxmlLoader.getController();
                    dashboardStudentViewController.setStudentLogged(userAuthenticated);

                    Stage stage = (Stage) signupButton.getScene().getWindow();
                    Pane mainPane = (Pane) stage.getScene().getRoot();
                    mainPane.getChildren().clear();
                    mainPane.getChildren().add(loginRoot);
                }
                case "teacher" -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/dashboard-teacher-view.fxml"));
                    Parent loginRoot = fxmlLoader.load();

                    DashboardTeacherViewController dashboardTeacherViewController = fxmlLoader.getController();
                    dashboardTeacherViewController.setTeacherLogged(userAuthenticated);

                    Stage stage = (Stage) signupButton.getScene().getWindow();
                    Pane mainPane = (Pane) stage.getScene().getRoot();
                    mainPane.getChildren().clear();
                    mainPane.getChildren().add(loginRoot);
                }
                default -> throw new IllegalStateException("Unexpected value: " + userAuthenticated.getType());
            }

        }

        loginInvalidError.setText("Login ou Senha Invalidos");


    }

    @FXML
    public void onShowPassword(){
        String password = fieldPassword.getText();
        String passwordShow = fieldPasswordShow.getText();
        if (fieldPassword.isVisible()){
            fieldPassword.setVisible(false);
            fieldPasswordShow.setText(password);
            fieldPasswordShow.setVisible(true);
        }else{
            fieldPasswordShow.setVisible(false);
            fieldPassword.setText(passwordShow);
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

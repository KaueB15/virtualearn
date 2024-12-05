package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.LoginDAO;
import br.com.fafic.virtualearn.exceptions.FieldIsNullException;
import br.com.fafic.virtualearn.exceptions.LoginAlreadyRegisteredException;
import br.com.fafic.virtualearn.exceptions.LoginNotFound;
import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.view.RegisterStudenterViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class LoginController {

    private LoginDAO loginDAO = new LoginDAO();

    public Login createLogin(String userName, String password, String type) {

        try{
            if (fieldValidation(userName, password)){
                throw new FieldIsNullException();
            }

            System.out.println(loginUsernameValidation(userName));

            if (loginUsernameValidation(userName)){
                throw new LoginAlreadyRegisteredException();
            }

            Login login = new Login();

            login.setLogin(userName);
            login.setPassword(password);
            login.setType(type);

            loginDAO.enterLogin(login);

            return login;
        }catch (LoginAlreadyRegisteredException e){
            System.err.println(e.getMessage());
            return null;
        }catch (FieldIsNullException e){
            return null;
        }

    }

    public boolean validateLogin(String userName, String password){

        try{
            if(!(loginDAO.checkLoginn(userName, password))){
                throw new LoginNotFound();
            }
            return true;
        }catch (LoginNotFound e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public Login findByLogin(String login){
        return loginDAO.findByLogin(login);
    }

    public boolean fieldValidation(String userName, String passowrd){
        return userName.isEmpty() || passowrd.isEmpty();
    }

    private boolean loginUsernameValidation(String userName){
        Login login = loginDAO.findByLogin(userName);

        if (login == null){
            return false;
        }

        return true;
    }

}

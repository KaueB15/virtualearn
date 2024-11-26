package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.LoginDAO;
import br.com.fafic.virtualearn.model.Login;

public class LoginController {

    private LoginDAO loginDAO = new LoginDAO();

    public Login createLogin(String userName, String password, String type){
        Login login = new Login();

        login.setLogin(userName);
        login.setPassword(password);
        login.setType(type);

        loginDAO.enterLogin(login);

        return login;
    }

}

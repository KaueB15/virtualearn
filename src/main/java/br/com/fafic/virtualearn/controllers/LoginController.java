package br.com.fafic.virtualearn.controllers;

import br.com.fafic.virtualearn.dao.LoginDAO;
import br.com.fafic.virtualearn.exceptions.LoginNotFound;
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

}

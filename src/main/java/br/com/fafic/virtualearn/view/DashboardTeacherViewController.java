package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.TeacherController;
import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.model.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardTeacherViewController {

    @FXML
    Label labelTeacherName;

    protected Login teacherLogged;

    protected Teacher teacherAuthenticated;

    private TeacherController teacherController = new TeacherController();

    public void setTeacherLogged(Login teacherLogged) {
        this.teacherLogged = teacherLogged;
        teacherAuthenticated = teacherController.getTeacherByLogin(teacherLogged);
        labelTeacherName.setText(teacherAuthenticated.getName() + ",");
    }
}

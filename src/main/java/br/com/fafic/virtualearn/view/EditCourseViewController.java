package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.ContractController;
import br.com.fafic.virtualearn.controllers.TeacherController;
import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.model.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EditCourseViewController {

    @FXML
    ComboBox<String> comboboxTeacher;

    @FXML
    TextField fieldMatter;

    protected Course courseSelected;

    private TeacherController teacherController = new TeacherController();

    private ContractController contractController = new ContractController();

    @FXML
    public void initialize(){

    }

    public void onShowingComboBoxTeacher(){
        comboboxTeacher.getItems().clear();

        List<Teacher> teachers = teacherController.getAllTeachers();

        for (Teacher teacher : teachers){
            comboboxTeacher.getItems().add(teacher.getName());
        }
    }

    @FXML
    public void onClickRegisterTeacherInCourseButton(){

        String teacherName = comboboxTeacher.getValue();
        String matter = fieldMatter.getText();
        Course course = courseSelected;

//        Teacher teacher = * FALTA FAZER FIND BY NAME *

        contractController.createContract(course, , matter);

    }

}

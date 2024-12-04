package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.ContractController;
import br.com.fafic.virtualearn.controllers.TeacherController;
import br.com.fafic.virtualearn.model.Contract;
import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.model.Teacher;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EditCourseViewController {

    @FXML
    ComboBox<String> comboboxTeacher;

    @FXML
    TextField fieldMatter;

    @FXML
    TableView<Contract>tableTeachers;

    @FXML
    TableColumn<Contract, String> columnTeacher;

    @FXML
    TableColumn<Contract, String> columnFormed;

    @FXML
    TableColumn<Contract, String> columnMatter;

    protected Course courseSelected;

    private TeacherController teacherController = new TeacherController();

    private ContractController contractController = new ContractController();

    @FXML
    public void initialize(){
        columnTeacher.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        columnFormed.setCellValueFactory(new PropertyValueFactory<>("teacherFormation"));
        columnMatter.setCellValueFactory(new PropertyValueFactory<>("matter"));

        loadDataTable();
    }

    @FXML
    private void loadDataTable(){

        Course course = courseSelected;

        System.out.println(course);

        tableTeachers.getItems().clear();

        List<Contract> contracts = contractController.getAllContracts();

        for (Contract contract : contracts) {

            tableTeachers.getItems().add(contract);

        }

    }

    @FXML
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

        Teacher teacher = teacherController.getTeacherByName(teacherName);

        contractController.createContract(course, teacher, matter, teacher.getMatter(), teacher.getName());

    }

}

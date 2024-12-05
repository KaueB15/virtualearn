package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.ContractController;
import br.com.fafic.virtualearn.controllers.TeacherController;
import br.com.fafic.virtualearn.model.Contract;
import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.model.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class EditCourseViewController {

    @FXML
    ComboBox<String> comboboxTeacher;

    @FXML
    TextField fieldMatter;

    @FXML
    Label labelCourseName;

    @FXML
    TableView<Contract>tableTeachers;

    @FXML
    TableColumn<Contract, String> columnTeacher;

    @FXML
    TableColumn<Contract, String> columnFormed;

    @FXML
    TableColumn<Contract, String> columnMatter;

    @FXML
    Button backButton;

    protected Course courseSelected;

    private TeacherController teacherController = new TeacherController();

    private ContractController contractController = new ContractController();

    public void setCourseSelected(Course courseSelected) {
        this.courseSelected = courseSelected;
        loadDataTable();
        labelCourseName.setText(courseSelected.getName());

    }

    @FXML
    public void initialize(){
        columnTeacher.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        columnFormed.setCellValueFactory(new PropertyValueFactory<>("teacherFormation"));
        columnMatter.setCellValueFactory(new PropertyValueFactory<>("matter"));
    }

    protected void loadDataTable(){


        tableTeachers.getItems().clear();

        Course course = courseSelected;

        List<Contract> contracts = contractController.getAllContracts();

        for (Contract contract : contracts) {

            if (contract.getCourse().equals(course)){
                tableTeachers.getItems().add(contract);
            }
        }

    }

    @FXML
    public void onShowingComboBoxTeacher(){
        comboboxTeacher.getItems().clear();

        List<Teacher> teachers = teacherController.getAllTeachers();

        if(!teachers.isEmpty()){
            for (Teacher teacher : teachers){
                comboboxTeacher.getItems().add(teacher.getName());
            }
        }
    }

    @FXML
    public void onClickRegisterTeacherInCourseButton(){

        String teacherName = comboboxTeacher.getValue();
        String matter = fieldMatter.getText();
        Course course = courseSelected;

        Teacher teacher = teacherController.getTeacherByName(teacherName);

        contractController.createContract(course, teacher, matter, teacher.getName(), teacher.getMatter());

        loadDataTable();

    }

    @FXML
    public void onBackButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/dashboard-admin-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }

}

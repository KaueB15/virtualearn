package br.com.fafic.virtualearn.view;

import br.com.fafic.virtualearn.controllers.ContractController;
import br.com.fafic.virtualearn.controllers.CourseController;
import br.com.fafic.virtualearn.controllers.RegistrationController;
import br.com.fafic.virtualearn.controllers.TeacherController;
import br.com.fafic.virtualearn.model.Course;
import br.com.fafic.virtualearn.model.Login;
import br.com.fafic.virtualearn.model.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DashboardAdminViewController {

    @FXML
    Label usersText;

    @FXML
    Button registerCourseButton;

    @FXML
    Button registerTeacherButton;

    @FXML
    Button logoutButton;

    @FXML
    TableView<Course> tableCourse;

    @FXML
    TableColumn<Course, String> courseColumn;

    @FXML
    TableColumn<Course, Integer> durationColumn;

    @FXML
    Button editCourseButton;

    @FXML
    TableView<Teacher> tableTeacher;

    @FXML
    TableColumn<Teacher, String> teacherColumn;

    protected Login adminLogged;

    private CourseController courseController = new CourseController();

    private TeacherController teacherController = new TeacherController();

    private ContractController contractController = new ContractController();

    private RegistrationController registrationController = new RegistrationController();

    @FXML
    public void initialize(){
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        loadDataTable();
    }

    private void loadDataTable(){
        tableCourse.getItems().clear();
        tableTeacher.getItems().clear();

        List<Course> courses = courseController.getAllCourses();
        List<Teacher> teachers = teacherController.getAllTeachers();

        for (Course course : courses) {
            tableCourse.getItems().add(course);
        }

        for (Teacher teacher : teachers) {
            tableTeacher.getItems().add(teacher);
        }

    }

    @FXML
    public void onClickButtonRegisterTeacher() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/register-teacher-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) registerTeacherButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }

    @FXML
    public void onClickButtonRegisterCourse() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/register-course-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) registerCourseButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }

    @FXML
    public void onClickDeleteCourse(){
        Course course = tableCourse.getSelectionModel().getSelectedItem();

        contractController.deleteContractByCourse(course);
        registrationController.deleteRegistrationByCourse(course);
        courseController.deleteCourse(course.getId());

        loadDataTable();
    }

    @FXML
    public void onClickLogoutButton() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/login-view.fxml"));
        Parent loginRoot = fxmlLoader.load();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }

    @FXML
    public void onClickEditCourseButton() throws IOException{
        Course course = tableCourse.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pages/edit-course-view.fxml"));
        Parent loginRoot = fxmlLoader.load();

        EditCourseViewController editCourseViewController = fxmlLoader.getController();
        editCourseViewController.setCourseSelected(course);

        Stage stage = (Stage) editCourseButton.getScene().getWindow();
        Pane mainPane = (Pane) stage.getScene().getRoot();
        mainPane.getChildren().clear();
        mainPane.getChildren().add(loginRoot);
    }

}

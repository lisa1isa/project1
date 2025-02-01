package com.app.userstable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;

public class TableViewController {
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> userIdColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> birthdateColumn;
    @FXML
    private TableColumn<User, String> salaryColumn;
    @FXML
    private TableColumn<User, Button> editColumn;
    @FXML
    private TableColumn<User, Button> deleteColumn;

    private DbFunctions dbFunctions;
    private Connection conn;

    public void initialize() {
        dbFunctions = new DbFunctions();
        conn = dbFunctions.connect_to_db("postgres", "postgres", "rootroot");

        // Настройка столбцов таблицы
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        editColumn.setCellValueFactory(new PropertyValueFactory<>("editButton"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        // Загрузка данных из базы данных
        loadData();
    }

    private void loadData() {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            ResultSet rs = dbFunctions.read_data(conn, "user_table");
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("birthdate"),
                        rs.getString("salary")
                );
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Сброс обработчиков событий для всех кнопок
        for (User user : userTable.getItems()) {
            user.resetButtons();
        }

        userTable.setItems(users);

        // Настройка действий для кнопок "Изменить" и "Удалить"
        setupTableButtons();
    }

    private void setupTableButtons() {
        for (User user : userTable.getItems()) {
            user.getEditButton().setOnAction(event -> handleEditUser(user));
            user.getDeleteButton().setOnAction(event -> handleDeleteUser(user));
        }
    }

    @FXML
    private void handleAddUser() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-user.fxml"));
            Parent parent = fxmlLoader.load();
            AddUserController controller = fxmlLoader.getController();
            controller.setTableController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEditUser(User user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit-user.fxml"));
            Parent parent = fxmlLoader.load();
            EditUserController controller = fxmlLoader.getController();
            controller.setTableController(this);
            controller.setUser(user);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleDeleteUser(User user) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to delete this user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            dbFunctions.delete_row_by_id(conn, "user_table", user.getUserId());
            refreshTable();
        }
    }

    public void refreshTable() {
        loadData();
    }

    public DbFunctions getDbFunctions() {
        return dbFunctions;
    }

    public Connection getConnection() {
        return conn;
    }
}
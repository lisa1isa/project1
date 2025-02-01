package com.app.userstable;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;

public class AddUserController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField birthdateField;
    @FXML
    private TextField salaryField;

    private TableViewController tableController;
    private DbFunctions dbFunctions;
    private Connection conn;

    public void setTableController(TableViewController tableController) {
        this.tableController = tableController;
        this.dbFunctions = tableController.getDbFunctions();
        this.conn = tableController.getConnection();
    }

    @FXML
    private void handleAdd() {
        String name = nameField.getText();
        String birthdate = birthdateField.getText();
        String salary = salaryField.getText();

        dbFunctions.insert_row(conn, "user_table", name, birthdate, salary);
        tableController.refreshTable();
        closeWindow();
    }

    @FXML
    private void handleClear() {
        nameField.clear();
        birthdateField.clear();
        salaryField.clear();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
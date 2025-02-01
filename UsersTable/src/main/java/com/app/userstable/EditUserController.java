package com.app.userstable;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditUserController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField birthdateField;
    @FXML
    private TextField salaryField;

    private TableViewController tableController;
    private User user;

    public void setTableController(TableViewController tableController) {
        this.tableController = tableController;
    }

    public void setUser(User user) {
        this.user = user;
        nameField.setText(user.getUserName());
        birthdateField.setText(user.getBirthdate());
        salaryField.setText(user.getSalary());
    }

    @FXML
    private void handleSave() {
        String name = nameField.getText();
        String birthdate = birthdateField.getText();
        String salary = salaryField.getText();

        tableController.getDbFunctions().update_name(tableController.getConnection(), "user_table", user.getUserId(), name);
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
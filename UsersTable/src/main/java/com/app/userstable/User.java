package com.app.userstable;

import javafx.scene.control.Button;

public class User {
    private int userId;
    private String userName;
    private String birthdate;
    private String salary;
    private Button editButton;
    private Button deleteButton;

    public User(int userId, String userName, String birthdate, String salary) {
        this.userId = userId;
        this.userName = userName;
        this.birthdate = birthdate;
        this.salary = salary;
        this.editButton = new Button("Edit");
        this.deleteButton = new Button("Delete");
    }

    // Геттеры и сеттеры
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    // Сброс обработчиков событий для кнопок
    public void resetButtons() {
        this.editButton.setOnAction(null);
        this.deleteButton.setOnAction(null);
    }
}
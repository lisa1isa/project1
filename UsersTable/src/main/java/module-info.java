module com.app.userstable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.app.userstable to javafx.fxml;
    exports com.app.userstable;
}
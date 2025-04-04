module org.example.uilocaliztion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.uilocaliztion to javafx.fxml;
    exports org.example.uilocaliztion;
}
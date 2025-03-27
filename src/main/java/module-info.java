module org.example.uilocaliztion {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.uilocaliztion to javafx.fxml;
    exports org.example.uilocaliztion;
}
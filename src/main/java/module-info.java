module com.example.finalprojectphonebook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.finalprojectphonebook to javafx.fxml;
    exports com.example.finalprojectphonebook;
}
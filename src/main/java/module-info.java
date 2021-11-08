module com.example.getpostdelupdatefx1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires json.simple;
    requires java.desktop;


    opens com.example.getpostdelupdatefx1 to javafx.fxml;
    exports com.example.getpostdelupdatefx1;
}
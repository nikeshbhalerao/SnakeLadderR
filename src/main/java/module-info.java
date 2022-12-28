module com.example.snakeladder2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeladder2 to javafx.fxml;
    exports com.example.snakeladder2;
}
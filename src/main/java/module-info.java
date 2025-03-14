module com.example.sdhcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sdhcalculator to javafx.fxml;
    exports com.example.sdhcalculator;
}
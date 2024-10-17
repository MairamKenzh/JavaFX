module org.example.javafxcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.javafxcalculator to javafx.fxml;
    exports org.example.javafxcalculator;
}
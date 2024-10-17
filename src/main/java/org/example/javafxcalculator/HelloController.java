package org.example.javafxcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private Label bmiResultLabel;
    @FXML
    private Label bmiStatusLabel;

    @FXML
    public void calculateBMI() {
        try {
            String heightText = heightField.getText().trim();
            String weightText = weightField.getText().trim();

            double height = 0.0;
            double weight = 0.0;

            // Check if height is in feet and inches or cm
            if (heightText.contains("'") || heightText.contains("\"")) {
                // Assuming input is in feet and inches (e.g., 5'10")
                String[] heightParts = heightText.split("[\"' ]");
                double feet = Double.parseDouble(heightParts[0].trim());
                double inches = Double.parseDouble(heightParts[1].trim());
                height = (feet * 12 + inches) * 2.54; // Convert feet and inches to cm
            } else {
                // Assuming input is in cm
                height = Double.parseDouble(heightText);
            }

            // Check if weight is in pounds or kg
            if (weightText.toLowerCase().contains("lbs")) {
                weight = Double.parseDouble(weightText.toLowerCase().replace("lbs", "").trim()) * 0.453592; // Convert lbs to kg
            } else {
                weight = Double.parseDouble(weightText); // Assuming input is in kg
            }

            double heightInMeters = height / 100; // Convert height from cm to meters
            double bmi = weight / (heightInMeters * heightInMeters);

            // Calculate ideal weight range
            double minIdealWeight = 18.5 * (heightInMeters * heightInMeters);
            double maxIdealWeight = 24.9 * (heightInMeters * heightInMeters);

            // Display results
            bmiResultLabel.setText(String.format("%.2f", bmi));
            bmiStatusLabel.setText(bmiStatusLabel(bmi)); // Correctly call the bmiStatusLabel method

        } catch (NumberFormatException e) {
            // Handle input errors with an alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter valid height and weight values.");
            alert.showAndWait();
        } catch (Exception e) {
            // Handle any other exceptions
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unexpected Error");
            alert.setHeaderText("An error occurred");
            alert.setContentText("Please check your inputs and try again.");
            alert.showAndWait();
        }
    }

    private String bmiStatusLabel(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Normal";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    @FXML
    public void onClear(ActionEvent actionEvent) {
        heightField.clear();
        weightField.clear();
        bmiResultLabel.setText(""); // Clear text instead of calling clear
        bmiStatusLabel.setText(""); // Clear text instead of calling clear
    }

    @FXML
    public void onExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void onAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About BMI Calculator");
        alert.setHeaderText(null);
        alert.setContentText("This BMI Calculator helps you calculate your BMI and ideal weight range.");
        alert.showAndWait();
    }
}

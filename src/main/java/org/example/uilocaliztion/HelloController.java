package org.example.uilocaliztion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button Hello;
    @FXML
    private Label lblDistance;
    @FXML
    private TextField txtDistance;
    @FXML
    private Label lblFuel;
    @FXML
    private TextField txtFuel;
    @FXML
    private Button btnCalculate;
    @FXML
    private Label lblResult;
    @FXML
    public Label localTime;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(rb.getString("hello"));
    }

    ResourceBundle rb;
    public void onCalculateClick(ActionEvent actionEvent) {
        if (txtDistance == null || txtFuel == null) {
            System.err.println("TextFields are empty");
            return;
        }
        try {
// Parse distance and height values
            double distance = Double.parseDouble(txtDistance.getText());
            double fuel = Double.parseDouble(txtFuel.getText());
// Calculate BMI using the formula: BMI = distance / (height * height)
            double fuel_needed = fuel * (distance / 100);
            System.out.println("Raw BMI: " + fuel_needed);
// Format the BMI value to 2 decimal places
            DecimalFormat df = new DecimalFormat("#0.00");
            String fuel_String = df.format(fuel_needed);
            System.out.println("Formatted BMI: " + fuel_String);
// Directly concatenate the BMI value to the result message
            lblResult.setText(String.format(rb.getString("result.label"), fuel_String));
        } catch (NumberFormatException e) {
// Handle invalid input
            lblResult.setText(rb.getString("invalid.input"));
        }
    }

    public void onENClick(ActionEvent actionEvent) {
        setLanguage(new Locale("en", "US"));
    }

    public void onFRClick(ActionEvent actionEvent) {
        setLanguage(new Locale("fr", "FR"));
    }

    public void onJPClick(ActionEvent actionEvent) {
        setLanguage(new Locale("ja", "JP"));
    }

    public void onIRClick(ActionEvent actionEvent) {
        setLanguage(new Locale("fa", "IR"));
    }

    public void initialize() {
        Locale locale = new Locale("en", "US");
        rb = ResourceBundle.getBundle("messages", locale);
        setLanguage(locale);
        getLocaleTime(locale);
    }

    private void getLocaleTime(Locale locale) {
        LocalDate currentDate = LocalDate.now();
        localTime.setText(String.valueOf(currentDate));
    }

    private void setLanguage(Locale locale) {
        lblResult.setText("");
        try {
            rb = ResourceBundle.getBundle("messages", locale);
            lblDistance.setText(rb.getString("distance.label"));
            lblFuel.setText(rb.getString("fuel.label"));
            btnCalculate.setText(rb.getString("calculate.button"));
        }catch(MissingResourceException e) {
            e.printStackTrace();
            lblResult.setText("Error loading resources error");
        }
    }
}
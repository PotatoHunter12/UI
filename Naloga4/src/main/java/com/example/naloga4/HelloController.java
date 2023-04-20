package com.example.naloga4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    @FXML
    private TextField izposoja1,izposoja2,ime,priimek,naslov,posta,email,tel,kartica,ccv;
    @FXML
    private ToggleGroup gear,gas;
    @FXML
    private ComboBox velikost,model,kraj1,kraj2;
    @FXML
    private Spinner starost,izpit;
    @FXML
    private Label cena;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void save() throws IOException {
        List<Object> inputFields = Arrays.asList(izposoja1, izposoja2, ime, priimek, naslov, posta, email, tel, kartica, ccv, velikost, model, kraj1,kraj2);
        boolean rt = false;
        for (Object field : inputFields) {
            if (field instanceof TextInputControl) {
                TextInputControl input = (TextInputControl) field;
                if (input.getText().isEmpty()) {
                    input.setStyle("-fx-border-color: red;");
                    rt = true;
                } else {
                    input.setStyle("");
                }
            } else if (field instanceof ComboBoxBase) {
                ComboBoxBase<?> input = (ComboBoxBase<?>) field;
                if (input.getValue() == null) {
                    input.setStyle("-fx-border-color: red;");
                    rt = true;
                } else {
                    input.setStyle("");
                }
            }
        }
        if (rt) return;

        String output = String.join("\n",
                "Čas izposoje: " + izposoja1.getText(),
                "Čas vrnitve: " + izposoja2.getText(),
                "Ime: " + ime.getText(),
                "Priimek: " + priimek.getText(),
                "Naslov: " + naslov.getText(),
                "Pošta: " + posta.getText(),
                "Email: " + email.getText(),
                "Telefon: " + tel.getText(),
                "Kartica: " + kartica.getText(),
                "CCV: " + ccv.getText(),
                "Gear: " + ((RadioButton) gear.getSelectedToggle()).getText(),
                "Gas: " + ((RadioButton) gas.getSelectedToggle()).getText(),
                "Velikost: " + (String) velikost.getValue(),
                "Model: " + (String) model.getValue(),
                "Kraj izposoje: " + (String) kraj1.getValue(),
                "Kraj vrnitve: " + (String) kraj2.getValue(),
                "Starost: " + (int) starost.getValue(),
                "Izpit: " + (int) izpit.getValue(),
                "Cena: " + cena.getText());

        Files.write(Paths.get("output.txt"), output.getBytes());
    }
}
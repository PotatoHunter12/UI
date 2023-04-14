package com.example.naloga3;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ComboBox znamka,gorivo,vrsta,zavarovanje,kasko;
    @FXML
    private ColorPicker barva;
    @FXML
    private DatePicker rojstni,datum;
    @FXML
    private Spinner sedezi;
    @FXML
    private CheckBox mv,z1,z2,z3,z4,z5,z6,z7;
    @FXML
    private TextField ime,priimek,naslov,kraj,posta,krajReg,registrska,oznaka,moc,prost;
    private Object[] stvari;
    private CheckBox[] boxi;
    @FXML
    private void onSave() throws IOException {
        boolean isNull = false;
        if(!posta.getText().matches("^\\d{4}$")){posta.setText("");}
        if(!moc.getText().matches("^\\d+$")){moc.setText("");}
        if(!prost.getText().matches("^\\d+$")){prost.setText("");}
        StringBuilder tekst = new StringBuilder();
        for (Object stvar:stvari) {
            if(stvar instanceof TextField){
                if(((TextField)stvar).getText().isBlank()) {
                    ((TextField)stvar).setStyle("-fx-border-color: 'red';-fx-border-width: 1.5");
                    isNull = true;
                }
                else tekst.append(((TextField) stvar).getText()).append(",");
            } else if (stvar instanceof ComboBox) {
                if(((ComboBox)stvar).getValue() == null) {
                    ((ComboBox)stvar).setStyle("-fx-border-color: 'red';-fx-border-width: 1.5");
                    isNull = true;
                }
                else tekst.append(((ComboBox) stvar).getValue()).append(",");
            }
            else {
                if(((DatePicker)stvar).getValue() == null) {
                    ((DatePicker)stvar).setStyle("-fx-border-color: 'red';-fx-border-width: 1.5");
                    isNull = true;
                }
                else tekst.append(((DatePicker) stvar).getValue()).append(",");
            }
        }


        if(!isNull){
            for (CheckBox cb: boxi) {
                tekst.append(cb.isSelected() ? "Da" : "Ne").append(",");
            }
            tekst.append(barva.getValue()).append(",").append(sedezi.getValue());
            File f = new FileChooser().showOpenDialog(null);
            FileWriter fw = new FileWriter(f);
            fw.write(tekst.toString());
            fw.close();
        }
    }
    @FXML
    private void onDelete(){
        for (Object stvar:stvari) {
            if(stvar instanceof TextField){
                ((TextField)stvar).setText("");
            } else if (stvar instanceof ComboBox) {
                ((ComboBox)stvar).setValue(null);
            } else {
                ((DatePicker)stvar).setValue(null);
            }
            ((Node)stvar).setStyle(null);
        }
        for (CheckBox cb:boxi) { cb.setSelected(false); }
        sedezi.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 0, 1));
        barva.setValue(Color.WHITE);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stvari = new Object[]{ime,priimek,naslov,kraj,posta,krajReg,registrska,oznaka,moc,prost,znamka,gorivo,vrsta,zavarovanje,kasko,rojstni,datum};
        boxi = new CheckBox[]{mv,z1,z2,z3,z4,z5,z6,z7};
        znamka.setItems(FXCollections.observableArrayList("VolksWagen","Renault","BMW","Opel","Mercedez","Ford","Tesla"));
        gorivo.setItems(FXCollections.observableArrayList("Bencin","Dizel","Elektrika"));
        vrsta.setItems(FXCollections.observableArrayList("Osebni avtomobil","Motor","Avtobus","Tovornjak","Traktor"));
        zavarovanje.setItems(FXCollections.observableArrayList("AO","AO+"));
        kasko.setItems(FXCollections.observableArrayList("Polno","Odbitna franšiza","Brez"));
        sedezi.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 0, 1));
    }
}
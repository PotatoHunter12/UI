package com.example.naloga3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

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
    private CheckBox mv,z1,z2,z3,z4,z5,z6,z7,z8,z9;
    @FXML
    private TextField ime,priimek,naslov,kraj,posta,krajReg,registrska,oznaka,moc,prost;
    private Object[] stvari = {znamka,gorivo,vrsta,zavarovanje,kasko,ime,priimek,naslov,kraj,posta,krajReg,registrska,oznaka,moc,prost,rojstni,datum};
    @FXML
    private void onSave(){
        System.out.println("banana");
        for (Object stvar: stvari) {
            System.out.println(stvar);
        }
    }
    @FXML
    private void onDelete(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
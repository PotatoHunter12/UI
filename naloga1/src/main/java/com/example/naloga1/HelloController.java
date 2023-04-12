package com.example.naloga1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField text;
    @FXML
    private ToggleGroup group;
    @FXML
    private ComboBox kombo;
    @FXML
    private Spinner spin;
    @FXML
    private CheckBox cb;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    protected void onButtonClicked() {
        if(group.getSelectedToggle() != null) {
            String t = ((ToggleButton) group.getSelectedToggle()).getText();

            if (t.equals("Dodaj") && !text.getText().equals("")) {
                String kraj = text.getText();
                if(!kombo.getItems().contains(kraj) || !cb.isSelected()){
                    kombo.getItems().add(kraj);
                }
            }
            if(t.equals("Odstrani prvega") && !kombo.getItems().isEmpty()){
                kombo.getItems().remove(0);
                System.out.println(kombo.getItems());
            }
            if(t.equals("Odstrani izbranega") && !kombo.getItems().isEmpty()){
                kombo.getItems().remove(kombo.getValue());
            }
        }
    }
    @FXML
    protected void onLetterClicked(ActionEvent e) {
        if(e.getSource().toString().contains("MenuItem")){
            lb2.setText(lb2.getText() + ((MenuItem)e.getSource()).getText().charAt(9));
        }
        else{
            lb2.setText(lb2.getText() + ((Button)e.getSource()).getText().charAt(3));
        }


    }
    @FXML
    protected void onOpenFC() {
        FileChooser fc = new FileChooser();
        fc.showOpenDialog(null);
    }
    @FXML
    protected void delete() { lb2.setText(""); }
    @FXML
    protected void exit() {
        System.exit(0);
    }
    @FXML
    protected void avtor() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Avtor");
        a.setHeaderText("Ambrož Perovšek");
        a.show();
        System.out.println("a");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kombo.getItems().add("Visoko");
        spin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, 20, 0, 1
        ));
        spin.valueProperty().addListener(obs -> {
            if((Integer)spin.getValue() < kombo.getItems().size()){
                lb1.setText(kombo.getItems().get((Integer)spin.getValue()).toString());
            }
            else{
                lb1.setText("Ni elementa");
            }
        });
    }
}
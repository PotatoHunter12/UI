package com.example.naloga2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label calcLb;
    @FXML
    private Label notificationLb;
    @FXML
    private TitledPane pane1;
    @FXML
    private TextArea textH;
    @FXML
    private TextArea textLog;
    private int openBracket = 0;
    private char last;
    private File f;
    @FXML
    protected void calculatorButton(ActionEvent e) throws ScriptException {
        char symbol = ((Button)e.getSource()).getText().charAt(0);
        if(last == '=') {
            calcLb.setText(calcLb.getText().split("=")[1]);
            last = '0';
        }
        if(calcLb.getText().equals("0") && (Character.isDigit(symbol) || symbol == '(')) calcLb.setText("");
        int ln = calcLb.getText().length();
        if(symbol == 'B'){
            calcLb.setText(ln > 1 ? calcLb.getText().substring(0,ln-1) : "0");
            return;
        }
        if(symbol == '(') openBracket++;
        else if(!Character.isDigit(symbol)){
            if(!Character.isDigit(last) && last != ')') return;
            if(symbol == ')') {
                if (openBracket > 0) openBracket--;
                else return;
            }
        }
        if(symbol == '='){
            ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
            textLog.setText(textLog.getText()+"\nIzračun ("+calcLb.getText()+")");
            calcLb.setText(calcLb.getText()+symbol+se.eval(calcLb.getText()));
            textH.setText(textH.getText()+"\n"+calcLb.getText());
        }
        else calcLb.setText(calcLb.getText()+symbol);
        last = symbol;
    }
    @FXML
    protected void onOpenFC() {
        f = new FileChooser().showOpenDialog(null);
        notificationLb.setText("Odprta datoteka: " + f.getName());
        textLog.setText(textLog.getText()+"\nOdprta datoteka (" + f.getName() + ")");
    }
    @FXML
    protected void delete() {
        calcLb.setText("0");
        textH.setText("");
        last = '0';
        textLog.setText(textLog.getText()+"\nVsebina kalkulatorja zbrisana");
    }
    @FXML
    protected void exit() {
        System.exit(0);
    }
    @FXML
    protected void avtor() {
        notificationLb.setText("Avtor: Ambrož Perovšek");
    }
    @FXML
    protected void save() throws IOException {
        if(!f.exists())notificationLb.setText("Izberite datoteko!");
        else if(!f.getName().contains(".txt")) notificationLb.setText("Izberite drugo datoteko!");
        else {
            FileWriter fw = new FileWriter(f);
            fw.write(textH.getText());
            fw.close();
            textLog.setText(textLog.getText()+"\nZgodovina izračunov shranjena v " + f.getName());

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane1.setExpanded(true);
    }
}
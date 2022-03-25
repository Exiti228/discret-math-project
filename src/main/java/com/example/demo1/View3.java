package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class View3 extends goToButtons {

    private Bank.Task3 task3;
    private String v0S;
    private String v1S;
    private String arg;

    @FXML
    private Button btnCalc;

    @FXML
    private Button exit;

    @FXML
    private TextField inputArg;

    @FXML
    private TextField inputOneVector;

    @FXML
    private TextField inputZeroVector;

    @FXML
    private Label setRes;

    @FXML
    private Label textRes;

    @FXML
    void onClickCalc() {
        v0S = inputZeroVector.getText();
        v1S = inputOneVector.getText();
        arg = inputArg.getText();
        if (v0S != null && arg != null && v1S != null) {
            boolean[] v0 = new boolean[v0S.length()];
            for (int i=0;i<v0S.length();i++) {
                if (v0S.charAt(i) == '1') {
                    v0[i] = true;
                }
                else if (v0S.charAt(i) == '0') {
                    v0[i] = false;
                }
                else {
                    printError();
                    return;
                }
            }
            boolean[] v1 = new boolean[v1S.length()];
            for (int i=0;i<v1S.length();i++) {
                if (v1S.charAt(i) == '1') {
                    v1[i] = true;
                }
                else if (v1S.charAt(i) == '0') {
                    v1[i] = false;
                }
                else {
                    printError();
                    return;
                }
            }
            int n;
            try {
                n = Integer.parseInt(arg);
            }
            catch (NumberFormatException e) {
                printError();
                return;
            }
            try {
                task3 = new Bank.Task3(v0, v1, n);
                boolean[] f = task3.getFunction();
                StringBuilder ans = new StringBuilder();
                for (boolean i : f) {
                    ans.append(i ? '1' : '0');
                }
                textRes.setText(ans.toString());
                textRes.setTextFill(Color.GREEN);
            }
            catch (IllegalArgumentException e) {
                printError();
            }
        }

    }

    void printError() {
        String errorMessage = "Error, invalid input";
        textRes.setText(errorMessage);
        textRes.setTextFill(Color.RED);
    }

}
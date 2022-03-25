package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class View12 extends goToButtons{

    private Bank.Task12 task12;
    private String vector;

    @FXML
    private Button btnCreate;

    @FXML
    private Button exit;

    @FXML
    private TextField inputVector;

    @FXML
    private Label outDNF;

    @FXML
    void onClickCreate(){
        vector = inputVector.getText();
        if (vector != null) {
            boolean[] v = new boolean[vector.length()];
            for (int i=0;i<vector.length();i++) {
                if (vector.charAt(i) == '1') {
                    v[i] = true;
                }
                else if (vector.charAt(i) == '0') {
                    v[i] = false;
                }
                else {
                    printError();
                    return;
                }
            }
            try {
                task12 = new Bank.Task12(v);

                outDNF.setText(task12.getMinDNF());
                outDNF.setTextFill(Color.GREEN);
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                printError();
            }
        }
    }

    void printError() {
        String errorMessage = "Error, invalid input";
        outDNF.setText(errorMessage);
        outDNF.setTextFill(Color.RED);
    }
}

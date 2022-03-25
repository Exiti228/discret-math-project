package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class View9 extends goToButtons {

    private Bank.Task9 task9;
    private String vector;

    @FXML
    private Button btnCreate;

    @FXML
    private Button exit;

    @FXML
    private TextField inputVector;

    @FXML
    private Label outSKNF;

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
                task9 = new Bank.Task9(v);

                outSKNF.setText(task9.getSknf());
                outSKNF.setTextFill(Color.GREEN);
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                printError();
            }
        }
    }

    @FXML
    void printError() {
        String errorMessage = "Error, invalid input";
        outSKNF.setText(errorMessage);
        outSKNF.setTextFill(Color.RED);
    }

}

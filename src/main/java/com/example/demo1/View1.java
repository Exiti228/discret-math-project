package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;


public class View1 extends goToButtons{

    @FXML
    private Button btnCalc;

    @FXML
    private TextField inputText;

    @FXML
    private Label setRes;

    @FXML
    private Label textRes;

    @FXML
    private void onClickCalc() {
        var count = inputText.getText();
        if (count != null) {
            try {
                Random rand = new Random();
                var ans = new StringBuilder();
                var size = 1 << Integer.parseInt(count);
                System.out.println(size + "");
                for (int i = 0; i < size; ++i) {
                    ans.append(rand.nextInt(2));
                }
                textRes.setText(ans.toString());
                textRes.setTextFill(Color.GREEN);
            }
            catch (NumberFormatException nfe) {
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

package com.example.demo1;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class View11 extends goToButtons{

    @FXML
    private TextField inputArg;

    @FXML
    private TextField inputType;

    @FXML
    private TextField inputVector;

    @FXML
    private Label textRes;

    @FXML
    private void onClickCalc() {
        var vector = inputVector.getText();
        var arg = inputArg.getText();
        var type = inputType.getText();
        if (vector != null && arg != null && type != null) {
            try {
                var argInt = Integer.parseInt(arg);
                var typeInt = Integer.parseInt(type);
                var ans = Solution.main(vector, typeInt, argInt);
                if (ans == null)
                    printError();
                else {
                    textRes.setText(ans);
                    textRes.setTextFill(Color.GREEN);
                }
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

package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class View6 extends goToButtons {

    private Bank.Task6 task6;
    private String randomVector;
    private String usersDNF;

    private boolean[] getBooleanVector(int n) {
        int sz = 1<<n;
        boolean[] vector = new boolean[sz];
        for (int i=0;i<sz;i++) {
            vector[i] = (Math.random() <= 0.5);
        }
        return vector;
    }

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnRandomVector;

    @FXML
    private Button exit;

    @FXML
    private TextField inputDNF;

    @FXML
    private Label inputRandomVector;

    @FXML
    private Label outCheck;

    @FXML
    private Label outDNF;

    @FXML
    void onClickGetRandomVector() {
        int n = (int)(Math.random() * 4 + 2);
        boolean flag = false;
        boolean[] v = {};
        while (!flag) {
            v = getBooleanVector(n);
            for (boolean i : v)
                flag |= i;
        }
        StringBuilder str = new StringBuilder();
        for (boolean i : v) str.append(i ? 1 : 0);

        inputRandomVector.setText(str.toString());
    }

    @FXML
    void onKeySimplify() {
        usersDNF = inputDNF.getText();
        randomVector = inputRandomVector.getText();
        if (usersDNF != null && randomVector != null) {
            boolean[] v = new boolean[randomVector.length()];
            for (int i=0;i<randomVector.length();i++) {
                v[i] = (randomVector.charAt(i) == '1');
            }
            try {
                task6 = new Bank.Task6(v, usersDNF);
                outDNF.setText(task6.getDnfView());
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                outDNF.setText("");
            }
        }
    }

    @FXML
    void onClickCheck() {
        usersDNF = inputDNF.getText();
        randomVector = inputRandomVector.getText();
        if (usersDNF != null && randomVector != null) {
            if (randomVector.equals("")) {
                return;
            }
            boolean[] v = new boolean[randomVector.length()];
            for (int i=0;i<randomVector.length();i++) {
                v[i] = (randomVector.charAt(i) == '1');
            }
            try {
                task6 = new Bank.Task6(v, usersDNF);
                if (task6.isCorrect()) {
                    printCorrect();
                }
                else {
                    printWrong();
                }
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                printWrong();
            }
        }
    }

    @FXML
    void printWrong() {
        String errorMessage = "Wrong answer";
        outCheck.setText(errorMessage);
        outCheck.setTextFill(Color.RED);
    }

    @FXML
    void printCorrect() {
        String errorMessage = "Correct answer";
        outCheck.setText(errorMessage);
        outCheck.setTextFill(Color.GREEN);
    }

}

package com.example.demo1;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.paint.Color;

import java.util.Random;

public class View4 extends goToButtons{

    private String vector;

    @FXML
    private Button buttonCheck;

    @FXML
    private Label setVector;
    
    @FXML
    private Label setRes;

    @FXML
    private Button updateButton;

    @FXML
    private MenuButton menu;

    @FXML
    void onClickCheck() {
        if (vector.equals(setVector.getText())) {
            setRes.setTextFill(Color.GREEN);
            setRes.setText("ПРАВИЛЬНО!");
        }
        else {
            setRes.setTextFill(Color.RED);
            setRes.setText("НЕПРАВИЛЬНО!");
        }
    }

    @FXML
    void set0000() {
        menu.setText("константа нуля");
        vector=("0000");
    }

    @FXML
    void set0001() {
        menu.setText("конъюкция");
        vector=("0001");
    }

    @FXML
    void set0010() {
        menu.setText("коимпликация");
        vector=("0010");
    }

    @FXML
    void set0011() {
        menu.setText("переменная x1");
        vector=("0011");
    }

    @FXML
    void set0100() {
        menu.setText("обратная коимпликация");
        vector=("0100");
    }

    @FXML
    void set0101() {
        menu.setText("переменная x2");
        vector=("0101");
    }

    @FXML
    void set0110() {
        menu.setText("сложение");
        vector=("0110");
    }

    @FXML
    void set0111() {
        menu.setText("дизъюнкция");
        vector=("0111");
    }

    @FXML
    void set1000() {
        menu.setText("стрелка пирса");
        vector=("1000");
    }

    @FXML
    void set1001() {
        menu.setText("эквивалентность");
        vector=("1001");
    }

    @FXML
    void set1010() {
        menu.setText("инверсия x2");
        vector=("1010");
    }

    @FXML
    void set1011() {
        menu.setText("обратная импликация");
        vector=("1011");
    }

    @FXML
    void set1100() {
        menu.setText("инверсия x1");
        vector=("1100");
    }

    @FXML
    void set1101() {
        menu.setText("импликация");
        vector=("1101");
    }

    @FXML
    void set1110() {
        menu.setText("штрих шеффера");
        vector=("1110");
    }

    @FXML
    void set1111() {
        menu.setText("константа еденицы");
        vector=("1111");
    }

    @FXML
    void onClickUpdate() {
        Random rand = new Random();
        var ans = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            ans.append(rand.nextInt(2));
        }
        setVector.setText(ans.toString());
        menu.setText("Название");
        setRes.setText("");
        vector="";
    }

}

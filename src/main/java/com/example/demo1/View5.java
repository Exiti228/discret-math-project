package com.example.demo1;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class View5 extends goToButtons{

    private Bank.Task5 task5 = new Bank.Task5();
    private int indTask = 2;
    private String vector = task5.getVector(indTask);
    private StringBuilder essCheck = task5.getEssVar(indTask);
    private StringBuilder dumCheck = task5.getDumVar(indTask);
    private int maxSize = task5.getPowers(indTask);
    private int indVar = 1;
    private StringBuilder leftVars = new StringBuilder();
    private StringBuilder rightVars = new StringBuilder();

    void generate() {
        indTask = (int)(Math.random() * 8);
        vector = task5.getVector(indTask);
        essCheck = task5.getEssVar(indTask);
        dumCheck = task5.getDumVar(indTask);
        maxSize = task5.getPowers(indTask);
        indVar = 1;
        leftVars = new StringBuilder();
        rightVars = new StringBuilder();

    }
    @FXML
    private Button buttonCheck;

    @FXML
    private Label dumVar;

    @FXML
    private Label essVar;

    @FXML
    private Label setRes;

    @FXML
    private Label setVar;

    @FXML
    private Label setVector;
    
    @FXML
    private Label toLeft;

    @FXML
    private Label toRight;

    @FXML
    private Button updateButton;

    @FXML
    void onClickCheck() {

        if (new String(leftVars).equals(new String(essCheck)) && new String(rightVars).equals(new String(dumCheck))) {
            setRes.setTextFill(Color.GREEN);
            setRes.setText("ПРАВИЛЬНО!");
        }
        else {
            setRes.setTextFill(Color.RED);
            setRes.setText("НЕПРАВИЛЬНО!");
        }
    }

    @FXML
    void onClickUpdate() {
        setVar.setText("Переменная 1");
        essVar.setText("");
        dumVar.setText("");
        generate();
        setVector.setText(vector);

    }
    @FXML
    void toLeftVar() {
        if (indVar > maxSize)
            return;
        if (!leftVars.isEmpty())
            leftVars.append(",");
        leftVars.append(indVar);
        essVar.setText(new String(leftVars));
        if (indVar++ >= maxSize)
            setVar.setText("");
        else
        setVar.setText(String.format("Переменная %d", indVar));
    }
    @FXML
    void toRightVar() {
        if (indVar > maxSize)
            return;
        if (!rightVars.isEmpty())
            rightVars.append(",");
        rightVars.append(indVar);
        dumVar.setText(new String(rightVars));
        if (indVar++ >= maxSize)
            setVar.setText("");
        else
        setVar.setText(String.format("Переменная %d", indVar));
    }


}

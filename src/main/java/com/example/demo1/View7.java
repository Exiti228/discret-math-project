package com.example.demo1;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.regex.Pattern;

public class View7 extends goToButtons{

    private int VectorSize;

    @FXML
    private Button btnRandomVector;

    @FXML
    private TextField inputKNF;

    @FXML
    private Label inputRandomVector;

    @FXML
    private Label outKNF;

    @FXML
    private Button btnCheck;

    @FXML
    private Label outCheck;

    private int solve_simple(String to_solve) {
        char tmp;
        int size=to_solve.length(), i=0;
        boolean solv=false, solv_t=false;
        while (i<size) {
            tmp=to_solve.charAt(i);
            if (tmp=='(') {
                while (tmp!=')') {
                    i++;
                    tmp=to_solve.charAt(i);
                    if (tmp=='v') {continue;}
                    else if (tmp=='1') {solv_t=true; break;}
                    else if (tmp=='0') {solv_t=false;}
                }
                if (!solv_t)
                    return 0;
            }
            i++;
        }
        return 1;
    }

    private boolean check_part_knf(String to_check) {
        // Работает с переменными вида x1, x2...
        System.out.println(to_check);
        return Pattern.matches("(¬?x[1-"+VectorSize+"][Vv∨])+¬?x[1-"+VectorSize+"]", to_check);
    }

    @FXML
    private void onClickGetRandomVector() {
        Random rand = new Random();
        var ans = new StringBuilder();
        VectorSize = rand.nextInt(2,3);
        int size = 1 << VectorSize;
        for (int i = 0; i < size; ++i) {
            ans.append(rand.nextInt(2));
        }
        inputRandomVector.setText(ans.toString());
    }

    @FXML
    private void onClickVerify() {
        String to_check=inputKNF.getText().replace(" ", "");
        var for_check = new StringBuilder();
        char tmp;
        int size=to_check.length(), comp=0;
        //inside function or not
        boolean flag=false, next_o=true;
        for (int i=0; i<size; i++) {
            tmp=to_check.charAt(i);
            if (!flag) {
                System.out.println(tmp);
                if (next_o) {
                    if (tmp!='(')
                        break;
                    for_check.setLength(0);
                    flag=true;
                }
                else {
                    if (!('⋀'==tmp || '^'==tmp || '∧'==tmp))
                        break;
                    else
                        next_o=true;
                }
            }
            else {
                if ('('==tmp || ')'==tmp) {
                    if ('('==tmp)
                        comp++;
                    else
                        if (comp==0) {
                            if (!check_part_knf(for_check.toString()))
                                break;
                            flag=false;
                            next_o=false;
                        }
                        else
                            comp--;
                }
                for_check.append(tmp);
            }
        }
        System.out.println(to_check);
        if (to_check.equals("None") || (size!=0 && comp==0 && !flag && !next_o )) {
            outKNF.setText("Correct");
            outKNF.setTextFill(Color.GREEN);
        }
        else {
            String errorMessage = "Error, invalid input";
            outKNF.setText(errorMessage);
            outKNF.setTextFill(Color.RED);
        }
    }

    @FXML
    private void onClickCheck() {
        int size = 1 << VectorSize;
        String tmp1, tmp2, res="";
        for (int i=0; i<size; i++) {
            tmp1=inputKNF.getText();
            tmp2=String.format("%0"+VectorSize+"d", Integer.parseInt(Integer.toBinaryString(i)));
            System.out.println("--"+tmp1+tmp2);
            for (int j=0; j<tmp2.length(); j++)
                tmp1=tmp1.replace("x"+(j+1),String.valueOf(tmp2.charAt(j)));
                System.out.println(tmp1);
            tmp1=tmp1.replace(" ","").replace("¬0","1").replace("¬1","0")
                .replace("V","v").replace("∨","v").replace("⋀","^").replace("∧","^");
            System.out.println(tmp1);
            res+=solve_simple(tmp1);
        }
        System.out.println(res);
        if (res.equals(inputRandomVector.getText()) || (inputKNF.getText().equals("None") && inputRandomVector.getText().equals("1111"))) {
            outCheck.setText("Правильно!");
            outCheck.setTextFill(Color.GREEN);
        }
        else {
            String errorMessage = "Неправильно!";
            outCheck.setText(errorMessage);
            outCheck.setTextFill(Color.RED);
    }
}
}

package com.example.demo1;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.Random;

public class View10 extends goToButtons{

    private int VectorSize;

    @FXML
    private Button btnRandomVector;

    @FXML
    private Label inputRandomVector;

    @FXML
    private Button btnCheck;

    @FXML
    private Label outCheck;

    @FXML
    private CheckBox set_T0;

    @FXML
    private CheckBox set_T1;

    @FXML
    private CheckBox set_S;

    @FXML
    private CheckBox set_M;

    @FXML
    private CheckBox set_L;

    private boolean check_M(String to_check, int size) {
        for (int i=0; i<size; i++) {
            int b_size=(int)Math.pow(2, i);
            int b_count=to_check.length()/b_size;
            String p1, p2;
            for (int j=0; j<b_count; j+=2) {
                p1=to_check.substring(j*b_size,(j+1)*b_size);
                p2=to_check.substring((j+1)*b_size,(j+2)*b_size);
                if (Integer.parseInt(p1)>Integer.parseInt(p2))
                    return false;
            }
        }
        return true;
    }

    private boolean check_L(String to_check, int n) {
        //Created by Fedorov Andrey (Andrfed)
        boolean[] v1 = new boolean[n];
        for (int i=0;i<n;i++) {
            v1[i] = to_check.charAt(i)=='0' ? false : true;
        }
        boolean[] p = new boolean[n];
        for (int i=1;i<n;i++) {
            p[i-1] = v1[i-1];
            boolean[] v2 = new boolean[n-i];
            for (int j=0;j<v2.length;j++) {
                v2[j] = v1[j + i - 1] ^ v1[j + i];
            }
            for (int j=0;j<v2.length;j++) {
                v1[j + i] = v2[j];
            }
        }
        p[n-1] = v1[n-1];
        for (int i=0;i<n;i++) {
            if ((i & (i-1)) != 0 && p[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean check_S(String to_check, int size) {
        for (int i=0; i<size; i++) {
            if (to_check.charAt(i)==to_check.charAt(size*2-1-i))
                return false;
        }
        return true;
    }

    @FXML
    private void onClickGetRandomVector() {
        Random rand = new Random();
        var ans = new StringBuilder();
        VectorSize = rand.nextInt(2,4);
        int size = 1 << VectorSize;
        for (int i = 0; i < size; ++i) {
            ans.append(rand.nextInt(2));
        }
        inputRandomVector.setText(ans.toString());
    }

    @FXML
    private void onClickCheck() {
        boolean is_ok0, is_ok1, is_ok2, is_ok3, is_ok4;
        String to_check=inputRandomVector.getText();
        is_ok0=(to_check.charAt(0)=='0')==set_T0.isSelected();
        is_ok1=(to_check.charAt(to_check.length()-1)=='1')==set_T1.isSelected();
        is_ok2=check_S(to_check, to_check.length()/2)==set_S.isSelected();
        is_ok3=check_M(to_check, VectorSize)==set_M.isSelected();
        is_ok4=check_L(to_check, to_check.length())==set_L.isSelected();
        System.out.println(check_L(to_check, VectorSize));
        if (is_ok0 && is_ok1 && is_ok2 && is_ok3 && is_ok4) {
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

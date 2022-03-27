package com.example.demo1;


import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.awt.*;

import javafx.scene.image.ImageView;
import java.awt.Color;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class View8 extends goToButtons{

    private int cur_pos = 0;
    private String latex = "";


    @FXML
    private TextField inputVector;

    @FXML
    private Label setRes;


    @FXML
    private ImageView imageDNF;
    @FXML
    void onClickCalc() throws IOException, InterruptedException {
        cur_pos = 0;
        String vector = inputVector.getText();
        if ( vector.chars().filter( x->x == '1' ).count() + vector.chars().filter( x->x == '0' ).count() != vector.length()
                || Integer.toBinaryString(vector.length()).chars().filter( x-> x== '1' ).count() != 1 ) {
            setRes.setTextFill(javafx.scene.paint.Color.RED);
            setRes.setText("НЕПРАВИЛЬНЫЙ ВВОД!");
            imageDNF.setImage(null);
        }
        else {
            latex = "";

            StringBuilder str = new StringBuilder();

            rec(vector, str);
            latex = "\\begin{array}(" + latex + "\\end{array}";
            System.out.println(latex);
            TeXFormula formula = new TeXFormula(latex);
            TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY).setSize(60).build();

            icon.setInsets(new Insets(5, 5, 5, 5));

            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = image.createGraphics();
            g2.setColor(Color.WHITE);
            g2.fillRect(0,0,icon.getIconWidth(),icon.getIconHeight());
            JLabel jl = new JLabel();
            jl.setForeground(new Color(0, 0, 0));
            icon.paintIcon(jl, g2, 0, 0);
            File file = new File("src/main/resources/com/example/demo1/Example4.png");
            ImageIO.write(image, "png", file.getAbsoluteFile());
            while (!file.exists())
                wait(100);
            //imageDNF.set
            Image im = new Image(String.valueOf(file.getAbsoluteFile()));
            imageDNF.setImage(im);
            setRes.setText("");
        }
    }
    void rec(String vector, StringBuilder str) {
        if ((1 << str.length()) == vector.length()) {

            if (vector.charAt(cur_pos) == '1') {
                StringBuilder add = new StringBuilder("(");

                for (int i = 0; i < str.length(); ++i) {

                    if (add.length() != 1)
                        add.append("\\cdot ");
                    if (str.charAt(i) == '0')
                        add.append(String.format("\\neg x_%d", i + 1));
                    else
                        add.append(String.format("x_%d", i + 1));
                }
                add.append(")");
                if (latex.length() != 0)
                    latex += "\\vee";
                latex += add;
            }
            cur_pos++;
            return;
        }
        str.append('0');

        rec(vector, str);
        str.deleteCharAt(str.length() - 1);
        str.append('1');

        rec(vector, str);
        str.deleteCharAt(str.length() - 1);
    }



}

package com.example.demo1;


import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;

public class goToButtons {

    @FXML
    private void onClickExit() {
        Platform.exit();
    }
    @FXML
    private void onTo1() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view1.fxml");
        app.setTitleName("Application 1");
        app.startWindow();
    }
    @FXML
    private void onTo2() throws IOException {
        var app = new HelloApplication();
        app.setFileName("hello-view.fxml");
        app.setTitleName("Application 2");
        app.startWindow();
    }
    @FXML
    private void onTo3() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view3.fxml");
        app.setTitleName("Application 3");
        app.startWindow();
    }
    @FXML
    private void onTo4() throws IOException {

        var app = new HelloApplication();
        app.setFileName("view4.fxml");
        app.setTitleName("Application 4");
        app.startWindow();
    }
    @FXML
    private void onTo5() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view5.fxml");
        app.setTitleName("Application 5");
        app.startWindow();
    }
    @FXML
    private void onTo6() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view6.fxml");
        app.setTitleName("Application 6");
        app.startWindow();
    }
    @FXML
    private void onTo7() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view7.fxml");
        app.setTitleName("Application 7");
        app.startWindow();
    }
    @FXML
    private void onTo8() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view8.fxml");
        app.setTitleName("Application 8");
        app.startWindow();
    }
    @FXML
    private void onTo9() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view9.fxml");
        app.setTitleName("Application 9");
        app.startWindow();
    }
    @FXML
    private void onTo10() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view10.fxml");
        app.setTitleName("Application 10");
        app.startWindow();
    }
    @FXML
    private void onTo11() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view11.fxml");
        app.setTitleName("Application 11");
        app.startWindow();
    }
    @FXML
    private void onTo12() throws IOException {
        var app = new HelloApplication();
        app.setFileName("view12.fxml");
        app.setTitleName("Application 12");
        app.startWindow();
    }
}

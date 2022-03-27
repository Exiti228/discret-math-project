package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private String fileName = "view1.fxml";
    private String titleName = "Application 1";
    static Stage stage = null;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public static void setStage(Stage stage) {
        HelloApplication.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
    @Override
    public void start(Stage stage) throws IOException {
        startWindow();

    }


    public void startWindow() throws IOException {
        System.out.println("Window init");
        if (stage == null) {
            System.out.println("Window create");
            stage = new Stage();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fileName));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle(titleName);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
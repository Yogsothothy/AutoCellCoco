package com.coco;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RightClickButtonExample extends Application {
    private Integer testNum = 0;
    private final Text testText = new Text(testNum.toString());
    VBox vBox = new VBox();
    Button button = new Button("Test");

    @Override
    public void start(Stage primaryStage) {

        HBox hBox = new HBox();
        hBox.getChildren().add(new Text("Test:"));
        hBox.getChildren().add(testText);

        HBox hBox1 = new HBox();
        hBox1.getChildren().add(button);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            testNum++;
            testText.setText(testNum.toString());
        });

        vBox.getChildren().add(hBox);
        vBox.getChildren().add(hBox1);
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//    public static void main(String[] args) {
//        Application.launch(RightClickButtonExample.class);
//    }
}

package com.coco;

import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * ClassName:RightClickTest
 * Package:com.coco
 * Description:
 *
 * @Author Coco
 * @Create 2024/3/30 15:11
 * @Version 1.0
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class RightClickButtonExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("右击我");

        // 创建一个鼠标右击事件处理程序
        EventHandler<MouseEvent> rightClickHandler = event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                System.out.println("helloworld");
            }
        };

        // 给按钮绑定鼠标右击事件处理程序
        button.setOnMouseClicked(rightClickHandler);

        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 200, 100);
        primaryStage.setTitle("右击事件示例");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

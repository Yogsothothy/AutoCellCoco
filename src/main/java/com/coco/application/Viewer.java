package com.coco.application;

import com.coco.application.components.CellFieldViewer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * ClassName: Application
 * Package: com.coco.application
 * Description:
 *
 * @Author Coco
 * @Create 2024/2/18 22:22
 * @Version 1.0
 */
public class Viewer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("Coco");
        Group group = new Group();
        Scene scene = new Scene(group,600,400);
        CellFieldViewer cellFieldViewer = new CellFieldViewer();
        GridPane gridPane = cellFieldViewer.getGridPane();
        System.out.println(gridPane);
        stage.setTitle("化身天灾");
        group.getChildren().add(gridPane);




        cellFieldViewer.run();

//        EventHandler<MouseEvent> eventHandler = mouseEvent -> {
//            System.out.println("鼠标左键事件触发了");
//        };
//        rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);

        stage.setScene(scene);
        stage.show();
    }
}

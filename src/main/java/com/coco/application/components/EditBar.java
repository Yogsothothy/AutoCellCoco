package com.coco.application.components;

import com.coco.utils.LocationType;
import com.coco.utils.PersonStatus;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class EditBar {
    private final ChoiceBox<LocationType> statusChoice = new ChoiceBox<>();
    private final VBox bar = new VBox();
    //    private final LifeCell cell= new LifeCell(0,0);
    private final Map<String, Integer> cell = new HashMap<>();
    private Button save = new Button("save");
    private final ChoiceBox<Integer> personStatusChoice = new ChoiceBox<>();

    /**
     * TODO 加一个用来addPerson的按钮
     */
    public EditBar() {
        statusChoice.getItems().add(0, LocationType.HOUSE);
        statusChoice.getItems().add(1, LocationType.AISLE);
        statusChoice.getItems().add(2, LocationType.PLAZA);
        statusChoice.setValue(LocationType.HOUSE);

        //数字的含义参照CovidCell.setStatus()方法
        personStatusChoice.getItems().add(0, 0);
        personStatusChoice.getItems().add(1, 1);
        personStatusChoice.getItems().add(2, 2);
        personStatusChoice.getItems().add(3, 3);
        personStatusChoice.getItems().add(4, 4);
        personStatusChoice.getItems().add(5, 5);
        personStatusChoice.getItems().add(6, 6);
        personStatusChoice.getItems().add(7, 7);//不变
        personStatusChoice.setValue(7);

//        save.setOnAction(event -> cell.setStatus(statusChoice.getValue()));
        save.setOnAction(event -> {
            cell.put("status", statusChoice.getValue().CODE);
            cell.put("personStatus", personStatusChoice.getValue());
        });
        bar.getChildren().add(statusChoice);
        bar.getChildren().add(personStatusChoice);
        bar.getChildren().add(save);
    }

    public VBox getBar() {
        return bar;
    }

    public Map<String, Integer> getCell() {
        return cell;
    }
}

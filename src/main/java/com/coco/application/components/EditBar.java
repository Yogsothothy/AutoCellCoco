package com.coco.application.components;

import com.coco.celldata.LifeCell;
import com.coco.utils.LocationType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

public class EditBar {
    private final ChoiceBox<Integer> statusChoice = new ChoiceBox<>();
    private final VBox bar = new VBox();
    private LifeCell cell= new LifeCell(0,0);
    private Button save = new Button("save");
    private final ChoiceBox<Integer> personStatusChoice = new ChoiceBox<>();

    /**
     * TODO 加一个用来addPerson的按钮
     */
    public EditBar() {
        statusChoice.getItems().add(0,LocationType.HOUSE.CODE);
        statusChoice.getItems().add(1,LocationType.AISLE.CODE);
        statusChoice.getItems().add(2,LocationType.PLAZA.CODE);
        statusChoice.setValue(LocationType.HOUSE.CODE);
        save.setOnAction(event -> cell.setStatus(statusChoice.getValue()));

        bar.getChildren().add(statusChoice);
        bar.getChildren().add(save);


    }

    public VBox getBar() {
        return bar;
    }
    public LifeCell getCell(){
        return cell;
    }
}

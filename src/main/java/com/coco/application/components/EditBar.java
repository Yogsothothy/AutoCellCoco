package com.coco.application.components;

import com.coco.celldata.LifeCell;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

public class EditBar {
    private ChoiceBox<Integer> statusChoice = new ChoiceBox<>();
    private final VBox bar = new VBox();
    private LifeCell cell= new LifeCell(0,0);
    private Button save = new Button("save");

    public EditBar() {
        statusChoice.getItems().add(0,0);
        statusChoice.getItems().add(1,1);
        statusChoice.setValue(0);
        save.setOnAction(event -> {
            cell.setStatus(statusChoice.getValue());
        });

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

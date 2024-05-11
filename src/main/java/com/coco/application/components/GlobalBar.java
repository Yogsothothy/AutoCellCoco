package com.coco.application.components;

import com.coco.utils.PersonType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lombok.Getter;

/**
 * ClassName:GlobalBar
 * Package:com.coco.application.components
 * Description:
 *
 * @Author Coco
 * @Create 2024/5/11 22:42
 * @Version 1.0
 */
public class GlobalBar {
    private final Text typeChoiceText = new Text("人员类型：");
    private final ChoiceBox<PersonType> typeChoice = new ChoiceBox<>();
    private final Text goOutText = new Text(" 活动率：");
    private final TextField goOutField = new TextField();
    private final Text SToEIText = new Text(" 感染率：");
    private final TextField SToEIField = new TextField();
    private final Text EIToRText = new Text(" 痊愈率：");
    private final TextField EIToRField = new TextField();
    private final Text IToDText = new Text(" 死亡率：");
    private final TextField IToDField = new TextField();
    private final Text EToIText = new Text(" 无症状转化率：");
    private final TextField EToIField = new TextField();


    @Getter
    private final HBox bar = new HBox();
    private Button save = new Button("save");

    /**
     *
     */
    public GlobalBar() {

        typeChoice.getItems().add(PersonType.NORMAL);
        typeChoice.getItems().add(PersonType.OLD);
        typeChoice.getItems().add(PersonType.CHILD);
        typeChoice.setValue(PersonType.NORMAL);

        save.setOnAction(event -> {
            typeChoice.getValue().chanceToGoOut = Integer.parseInt(goOutField.getText());
            typeChoice.getValue().chanceOfSToEI = Integer.parseInt(SToEIField.getText());
            typeChoice.getValue().chanceOfEIToR = Integer.parseInt(EIToRField.getText());
            typeChoice.getValue().chanceOfIToD = Integer.parseInt(IToDField.getText());
            typeChoice.getValue().chanceOfEToI = Integer.parseInt(EToIField.getText());
        });
        bar.getChildren().add(typeChoiceText);
        bar.getChildren().add(typeChoice);

        bar.getChildren().add(goOutText);
        bar.getChildren().add(goOutField);

        bar.getChildren().add(SToEIText);
        bar.getChildren().add(SToEIField);

        bar.getChildren().add(EIToRText);
        bar.getChildren().add(EIToRField);

        bar.getChildren().add(IToDText);
        bar.getChildren().add(IToDField);

        bar.getChildren().add(EToIText);
        bar.getChildren().add(EToIField);

        bar.getChildren().add(save);
    }


}

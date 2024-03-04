package com.coco.application.components;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * ClassName:ToolBar
 * Package:com.coco.application.components
 * Description:TODO 暂停/继续；调节速度,保存
 *
 * @Author Coco
 * @Create 2024/3/4 13:13
 * @Version 1.0
 */
public class ToolBar {
    private CellFieldViewer cellFieldViewer;
    private String startText = "开始/停止";
    private Text speedText = new Text("速度：");
//    private Slider speedSlider = new Slider(0, 1999, 1900);
    ToggleGroup speedButtonGroup = new ToggleGroup();
    RadioButton slowRadioButton = new RadioButton();
    RadioButton normalRadioButton = new RadioButton();
    RadioButton quickRadioButton = new RadioButton();
    private HBox speedBar = new HBox(speedText, slowRadioButton, normalRadioButton, quickRadioButton);
    private final Button startButton = new Button(startText);
    private final VBox bar = new VBox();

    public ToolBar(CellFieldViewer cellFieldViewer) {
        bar.getChildren().add(startButton);
        bar.getChildren().add(speedBar);

        slowRadioButton.setToggleGroup(speedButtonGroup);
        normalRadioButton.setToggleGroup(speedButtonGroup);
        quickRadioButton.setToggleGroup(speedButtonGroup);

        speedButtonGroup.selectToggle(normalRadioButton);

        this.cellFieldViewer = cellFieldViewer;
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (cellFieldViewer.getPauseFlag()) {
                start();
            } else {
                pause();
            }
        });
    }

    public VBox getBar() {
        return bar;
    }

    /**
     * TODO CellField开始的逻辑，这个方法将会被按钮的事件处理程序调用
     */
    private void start() {
        cellFieldViewer.changePauseFlag();
        if(speedButtonGroup.getSelectedToggle() == slowRadioButton) {
            cellFieldViewer.setRunSpeed(1000);
        }else if (speedButtonGroup.getSelectedToggle() == quickRadioButton)
        {
            cellFieldViewer.setRunSpeed(0);
        }else {
            cellFieldViewer.setRunSpeed(100);
        }
    }

    private void pause() {
        cellFieldViewer.changePauseFlag();
    }

    public Button getStartButton() {
        return startButton;
    }
}

package com.coco.application.components;

import com.coco.celldata.CellField;
import com.coco.celldata.CovidCell;
import com.coco.celldata.Person;
import com.coco.utils.CSVUtil;
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
    CellField field = CellField.getInstance();
    private CellFieldViewer cellFieldViewer;
    private String startText = "开始/停止";
    private Text speedText = new Text("速度：");
    private String saveDataText = "保存数据";
    private String loadDataText = "读取数据";
    private String testText = "调试";
    ToggleGroup speedButtonGroup = new ToggleGroup();
    RadioButton slowRadioButton = new RadioButton();
    RadioButton normalRadioButton = new RadioButton();
    RadioButton quickRadioButton = new RadioButton();
    private HBox speedBar = new HBox(speedText, slowRadioButton, normalRadioButton, quickRadioButton);
    private final Button startButton = new Button(startText);
    private final VBox bar = new VBox();
    private final Button saveDataButton = new Button(saveDataText);
    private final Button loadDataButton = new Button(loadDataText);
    private final Button testButton = new Button(testText);

    public ToolBar(CellFieldViewer cellFieldViewer) {
        bar.getChildren().add(startButton);
        bar.getChildren().add(speedBar);
        bar.getChildren().add(saveDataButton);
        bar.getChildren().add(loadDataButton);
        bar.getChildren().add(testButton);

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
        saveDataButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            CSVUtil.WriteField("a", CellField.getInstance());
        });
        loadDataButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            CSVUtil.ReadField();
            //读完之后给交互界面一个更新
            for (CellCube[] cellCubeLine : cellFieldViewer.getCellCubes()) {
                for (CellCube cellCube : cellCubeLine) {
                    CovidCell cell = (CovidCell) field.getCell(cellCube.getX(), cellCube.getY());
                    cellCube.clearPeople();
                    for (Person person : cell.getPeople()) {
                        cellCube.addPerson(person.getStatus());
                    }
                    cellCube.setLocation(cell.getLocation().CODE);
                }
            }
        });
        testButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            System.out.println(field.getCell(0, 0).toString());
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
        if (speedButtonGroup.getSelectedToggle() == slowRadioButton) {
            cellFieldViewer.setRunSpeed(500);
        } else if (speedButtonGroup.getSelectedToggle() == quickRadioButton) {
            cellFieldViewer.setRunSpeed(0);
        } else {
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

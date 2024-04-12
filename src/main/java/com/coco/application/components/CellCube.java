package com.coco.application.components;

import com.coco.celldata.CellField;
import com.coco.celldata.CovidCell;
import com.coco.celldata.Person;
import com.coco.utils.PersonStatus;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class CellCube {
    private int x;
    private int y;
    private CellField cellField = CellField.getInstance();
    @Getter
    private Rectangle cellCube = new Rectangle(30, 30);
    @Getter
    @Setter
    private Group group = new Group(cellCube);

    public void setLocation(int locationCode) {
        switch (locationCode) {
            case 0:
                cellCube.setFill(Color.WHITE);
                break;
            case 1:
                cellCube.setFill(Color.YELLOW);
                break;
            case 2:
                cellCube.setFill(Color.GREY);
                break;
            default:
                break;
        }
    }

    public void setEditBar(EditBar editBar) {
    }

    public CellCube(int x, int y, EditBar editBar) {
        this.x = x;
        this.y = y;
        //在此编写CellCube被点击之后的事件
        cellCube.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
//                setStatus(editBar.getCell().get("status") % 10);
//                for (int i = 1; i < group.getChildren().size(); i++) {
//                    group.getChildren().remove(1);
//                }
                clearPeople();
                //使方块变色，使EditBar里的数据被复制到对应的Cell里
                cellField.getCell(this.x, this.y).setStatus(editBar.getCell().get("status") + editBar.getCell().get("personStatus") * 10);
                cellField.getTempCell(this.x, this.y).setStatus(editBar.getCell().get("status") + editBar.getCell().get("personStatus") * 10);
                setLocation(editBar.getCell().get("status"));
                if (editBar.getCell().get("status") == 2) {
                    CovidCell.plazas.add((CovidCell) cellField.getTempCell(x, y));
                }
                if (editBar.getCell().get("status") == 0) {
                    ArrayList<Person> people = ((CovidCell) cellField.getTempCell(this.x, this.y)).getPeople();
                    for (Person person : people) {
                        addPerson(person.getStatus());
                    }
                }
            }
        });
    }

    public void addPerson(PersonStatus personStatus) {
        int index = group.getChildren().size();
        int personX = (index - 1) % 3 * 10;
        int personY = (index - 1) / 3 * 10;
        Rectangle person = new Rectangle(personX, personY, 10, 10);
        person.setFill(Color.BLACK);
        if (personStatus == PersonStatus.E || personStatus == PersonStatus.I) {
            person.setFill(Color.RED);
        }
        if (personStatus == PersonStatus.S) {
            person.setFill(Color.GREEN);
        }
        if (personStatus == PersonStatus.R) {
            person.setFill(Color.BLUE);
        }
        group.getChildren().add(person);
    }

    public void clearPeople() {
        while (group.getChildren().size() > 1) {
            group.getChildren().remove(1);
        }
    }

    public void setPersonStatus(int index, PersonStatus status) {
        if (status  == PersonStatus.S) {
            ((Rectangle) group.getChildren().get(index + 1)).setFill(Color.GREEN);
        }
        if (status == PersonStatus.E || status == PersonStatus.I){
            ((Rectangle) group.getChildren().get(index + 1)).setFill(Color.RED);
        }
        if (status == PersonStatus.R) {
            ((Rectangle) group.getChildren().get(index + 1)).setFill(Color.BLUE);
        }
    }

}

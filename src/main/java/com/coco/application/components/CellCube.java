package com.coco.application.components;

import com.coco.celldata.CellField;
import com.coco.celldata.CovidCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellCube {
    private int x;
    private int y;
    private CellField cellField = CellField.getInstance();
    private Rectangle cellCube = new Rectangle(30, 30);

    public void setStatus(int status) {
        switch (status) {
            case 1:
                cellCube.setFill(Color.WHITE);
                break;
            case 2:
                cellCube.setFill(Color.BLACK);
                break;
            case 3:
                cellCube.setFill(Color.GREY);
                break;
            default:
                break;
        }
    }

    public Rectangle getCellCube() {
        return cellCube;
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
                setStatus(1);
                //使方块变色，使EditBar里的数据被复制到对应的Cell里
                cellField.getCell(this.x, this.y).setStatus(editBar.getCell().get("status") + editBar.getCell().get("personStatus") * 10);
                cellField.getTempCell(this.x, this.y).setStatus(editBar.getCell().get("status") + editBar.getCell().get("personStatus") * 10);
                if (editBar.getCell().get("status") == 2) {
                    CovidCell.plazas.add((CovidCell) cellField.getTempCell(x, y));
                }
            }
        });
        setStatus(1);
    }
}

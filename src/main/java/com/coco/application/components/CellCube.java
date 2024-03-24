package com.coco.application.components;

import com.coco.celldata.CellField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellCube {
    private int x;
    private int y;
    private CellField cellField = CellField.getInstance();
    private Rectangle cellCube = new Rectangle(10, 10);

    public void setStatus(int status) {
        switch (status) {
            case 0:
                cellCube.setFill(Color.WHITE);
                break;
            case 1:
                cellCube.setFill(Color.BLACK);
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
            setStatus(editBar.getCell().getStatus());
            //使方块变色，使EditBar里的数据被复制到对应的Cell里
            cellField.getCell(this.x, this.y).setStatus(editBar.getCell().getStatus());
            cellField.getTempCell(this.x, this.y).setStatus(editBar.getCell().getStatus());
        });
        setStatus(0);
    }
}

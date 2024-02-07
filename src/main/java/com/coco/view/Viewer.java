package com.coco.view;

import com.coco.celldata.CellField;
import com.coco.celldata.LifeCell;

import java.lang.reflect.Field;

/**
 * ClassName:Viewer
 * Package:com.coco.view
 * Description:
 *
 * @Author Coco
 * @Create 2024/2/7 12:44
 * @Version 1.0
 */
public class Viewer {
    CellField field = CellField.getInstance();

    public void paint() {
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                if (((LifeCell) field.getCell(x, y)).getStatus()) {
                    System.out.print("x ");
                }else {
                    System.out.print("o ");
                }
            }
            System.out.println();
        }
    }
}

package com.coco.service;

import com.coco.celldata.Cell;
import com.coco.celldata.CellField;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:CovidService
 * Package:com.coco.service
 * Description:
 *
 * @Author Coco
 * @Create 2024/3/27 16:02
 * @Version 1.0
 */
public class CovidService {
    /**
     * 获取CellField单例
     */
    CellField field = CellField.getInstance();

    /**
     * 元胞自动机每轮的演化
     * @return 状态变化过的细胞集合
     */
    public List<Cell> next() {
        List<Cell> cells = new ArrayList<>();
        field.getTempCell(0, 0).beforeRoundStrategy();
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                Cell tempCell = field.getTempCell(x, y);
                if (tempCell.cellStrategy()){
                    cells.add(tempCell);
                }
            }
        }
        field.getTempCell(0, 0).afterRoundStrategy();
        field.save();
        return cells;
    }

    public void randomIni() {
    }
}

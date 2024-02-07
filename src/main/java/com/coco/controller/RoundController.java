package com.coco.controller;

import com.coco.celldata.CellField;

/**
 * ClassName:RoundController
 * Package:com.coco.controller
 * Description:
 *
 * @Author Coco
 * @Create 2024/2/7 12:38
 * @Version 1.0
 */
public class RoundController {
    /**
     * 获取CellField单例
     */
    CellField field = CellField.getInstance();

    /**
     * 元胞自动机每轮的演化
     */
    public void next() {
        field.getTempCell(0, 0).beforeRoundRule();
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                field.getTempCell(x, y).cellRule();
            }
        }
        field.getTempCell(0, 0).afterRoundRule();
        field.save();
    }

    /**
     * 对元胞自动机的随机初始化
     */
    public void randomIni() {
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
//                if (Math.random() < 0.2){
//                    field.getCell(x,y).randomIni();
//                }
                if (x == 2 && y == 2) {
                    field.getCell(x, y).randomIni();
                    field.getTempCell(x, y).randomIni();
                }
                if (x == 2 && y == 3) {
                    field.getCell(x, y).randomIni();
                    field.getTempCell(x, y).randomIni();
                }
                if (x == 2 && y == 4) {
                    field.getCell(x, y).randomIni();
                    field.getTempCell(x, y).randomIni();
                }
            }
        }
    }
}

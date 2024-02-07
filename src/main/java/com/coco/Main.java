package com.coco;

import com.coco.celldata.CellField;
import com.coco.celldata.LifeCell;
import com.coco.controller.RoundController;
import com.coco.view.Viewer;

/**
 * ClassName:Main
 * Package:com.coco
 * Description:
 *
 * @Author Coco
 * @Create 2024/2/7 12:35
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        RoundController roundController = new RoundController();
        CellField field = CellField.getInstance();
        Viewer viewer = new Viewer();
        field.fieldIni(LifeCell.class);

        int Rounds = 1000;
        roundController.randomIni();
        viewer.paint();

        for (int i = 0; i < Rounds; i++) {
            System.out.println("------------------------------------------------");
            roundController.next();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            viewer.paint();

        }
    }
}

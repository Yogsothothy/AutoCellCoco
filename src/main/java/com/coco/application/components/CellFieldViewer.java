package com.coco.application.components;

import com.coco.celldata.Cell;
import com.coco.celldata.CellField;
import com.coco.celldata.LifeCell;
import com.coco.service.RoundService;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * ClassName: CellFieldViewer
 * Package: com.coco.application.components
 * Description:
 *
 * @Author Coco
 * @Create 2024/2/18 22:57
 * @Version 1.0
 */
public class CellFieldViewer {
    /**
     * 用一个网格窗格来容纳细胞，每个细胞对应其中一格
     */
    private final GridPane gridPane = new GridPane();
    private final Rectangle[][] field = new Rectangle[CellField.getInstance().getWidth()][CellField.getInstance().getHeight()];
    RoundService roundService = new RoundService();

    public CellFieldViewer() {
        init();
    }

    private void init() {
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        gridPane.setGridLinesVisible(true);
        CellField.getInstance().fieldIni(LifeCell.class);
        roundService.randomIni();
        for (int x = 0; x < CellField.getInstance().getWidth(); x++) {
            for (int y = 0; y < CellField.getInstance().getHeight(); y++) {
                Rectangle rectangle = new Rectangle(10, 10);
                if (CellField.getInstance().getCell(x, y).getStatus() == 0) {
                    rectangle.setFill(Color.WHITE);
                } else {
                    rectangle.setFill(Color.BLACK);
                }
                gridPane.add(rectangle, x, y);
                field[x][y] = rectangle;
            }
        }

    }

    public void run() {
        new Thread(() -> {
            for (int i = 0; i < 1000;i++) {
                List<Cell> cellList = roundService.next();
                for (Cell cell : cellList) {
                    if (cell.getStatus() == 1) {
                        field[cell.getX()][cell.getY()].setFill(Color.BLACK);
                    } else {
                        field[cell.getX()][cell.getY()].setFill(Color.WHITE);
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}

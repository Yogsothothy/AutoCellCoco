package com.coco.application.components;

import com.coco.application.ApplicationStorage;
import com.coco.celldata.Cell;
import com.coco.celldata.CellField;
import com.coco.celldata.LifeCell;
import com.coco.service.RoundService;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Map;

/**
 * ClassName: CellFieldViewer
 * Package: com.coco.application.components
 * Description:这个类既需要处理元胞自动机的逻辑，又需要处理图形界面的绘制。这导致了耦合度的提高，
 * 将会在之后的版本中被优化掉(也许不会
 *
 * @Author Coco
 * @Create 2024/2/18 22:57
 * @Version 1.0
 */
public class CellFieldViewer {
    private ApplicationStorage storage = ApplicationStorage.getInstance();
    private final CellField field = CellField.getInstance();
    /**
     * 用一个网格窗格来容纳细胞，每个细胞对应其中一格
     */
    private final GridPane gridPane = new GridPane();
    /**
     * 这个正方形数组会容纳Cell数组里的信息
     */
    private final Rectangle[][] rectangles = new Rectangle[CellField.getInstance().getWidth()][CellField.getInstance().getHeight()];
    /**
     * 在这个类中需要调用roundService
     */
    RoundService roundService = new RoundService();
    /**
     * 通过这个变量的值控制程序是否暂停
     */
    private boolean pauseFlag = true;
    private int runSpeed = 100;

    /**
     * 实例化的同时会初始化这个类
     */
    public CellFieldViewer() {

    }

    /**
     * 初始化组件，绘制gridPane的区域，并同时在此初始化CellField
     */
    public void init() {
        Map map = storage.getMap();
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        //用黑色的实线来分隔每一个格子。
        gridPane.setGridLinesVisible(true);
        field.fieldIni(LifeCell.class);
        //
        roundService.randomIni();
        //根据field里的信息来初始化整个图形区域，这里需要进一步修改来降低耦合度（也许不用
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
//                Rectangle rectangle = new Rectangle(10, 10);
                CellCube cellCube = new CellCube(x, y,(EditBar) map.get("editBar"));
//                if (field.getCell(x, y).getStatus() == 0) {
//                    rectangle.setFill(Color.WHITE);
//                } else {
//                    rectangle.setFill(Color.BLACK);
//                }
                if (field.getCell(x, y).getStatus() == 0) {
                    cellCube.setStatus(0);
                } else {
                    cellCube.setStatus(1);
                }
                gridPane.add(cellCube.getCellCube(), x, y);
                rectangles[x][y] = cellCube.getCellCube();
            }
        }
    }

    /**
     * 运行这个方法来新开一个线程使组件运作起来。
     * TODO 将来要实现暂停/继续功能，并由其他组件调用
     */
    public void run() {
        new Thread(() -> {
            //每次循环是元胞自动机的一轮更新
            while (true) {
                //执行策略使cellField内部的数据更新，并接收更新过的cell
                List<Cell> cellList = roundService.next();
                //在此检测是否应当暂停
                while (pauseFlag) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //将cell的更新作用到图形界面上
                //TODO 为了未来用在疫情模拟上，这里需要更丰富的色彩（至少4种
                for (Cell cell : cellList) {
                    if (cell.getStatus() == 1) {
                        rectangles[cell.getX()][cell.getY()].setFill(Color.BLACK);
                    } else {
                        rectangles[cell.getX()][cell.getY()].setFill(Color.WHITE);
                    }
                }

                //在此控制程序执行速度
                try {
                    Thread.sleep(runSpeed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    /**
     * 返回这个组件
     *
     * @return gridPane
     */
    public GridPane getGridPane() {
        return gridPane;
    }

    /**
     * pauseFlag的set方法
     */
    public void changePauseFlag() {
        pauseFlag = !pauseFlag;
    }

    public boolean getPauseFlag() {
        return pauseFlag;
    }

    public int getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(int runSpeed) {
        this.runSpeed = runSpeed;
    }
}

package com.coco.celldata;

/**
 * ClassName:LifeCell
 * Package:com.coco.celldata
 * Description:
 *
 * @Author Coco
 * @Create 2024/2/7 12:54
 * @Version 1.0
 */
public class LifeCell extends Cell {
    private boolean status = false;

    public LifeCell(int x, int y) {
        super(x, y);
    }

    @Override
    public void randomIni() {
        changeStatus();
    }

    @Override
    public void copy(Cell tempCell) {
        if (status != ((LifeCell)tempCell).getStatus()){
            changeStatus();
        }
    }

    @Override
    public void cellRule() {
        int count = getNeighbors();
        //存活时的情况
        if (status) {
            //周围细胞数为2，3时，继续存活，否则死掉
            if (count < 2 || count > 3) {
                changeStatus();
            }
        }
        //死亡时的情况
        if (!status) {
            //周围细胞数为3时复活
            if (count == 3) {
                changeStatus();
            }
        }
    }

    @Override
    public void beforeRoundRule() {

    }

    @Override
    public void afterRoundRule() {

    }

    public boolean getStatus() {
        return status;
    }

    private int getNeighbors() {
        int count = 0;
        for (int x = this.x - 1; x <= this.x + 1; x++) {
            for (int y = this.y - 1; y <= this.y + 1; y++) {
                if (!(x == this.x && y == this.y) && (x >= 0 && y >= 0) && (x < field.getWidth() && y < field.getHeight())) {
                    if (((LifeCell) field.getCell(x, y)).getStatus()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void changeStatus() {
        status = !status;
    }
}

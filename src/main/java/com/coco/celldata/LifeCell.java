package com.coco.celldata;

import lombok.ToString;

/**
 * ClassName:LifeCell
 * Package:com.coco.celldata
 * Description:
 *
 * @Author Coco
 * @Create 2024/2/7 12:54
 * @Version 1.0
 */
@ToString
public class LifeCell extends Cell {
    private int status = 0;

    public LifeCell(int x, int y) {
        super(x, y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void randomIni() {
        changeStatus();
    }

    @Override
    public void copy(Cell tempCell) {
        if (status != ((LifeCell) tempCell).getStatus()) {
            changeStatus();
        }
    }

    @Override
    public boolean cellStrategy() {
        int count = getNeighbors();
        boolean flag = false;
        //存活时的情况
        if (status != 0) {
            //周围细胞数为2，3时，继续存活，否则死掉
            if (count < 2 || count > 3) {
                changeStatus();
                flag = true;
            }
        }
        //死亡时的情况
        if (status == 0) {
            //周围细胞数为3时复活
            if (count == 3) {
                changeStatus();
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void beforeRoundStrategy() {

    }

    @Override
    public void afterRoundStrategy() {

    }

    public int getStatus() {
        return status;
    }

    private int getNeighbors() {
        int count = 0;
        for (int x = this.x - 1; x <= this.x + 1; x++) {
            for (int y = this.y - 1; y <= this.y + 1; y++) {
                if (!(x == this.x && y == this.y) && (x >= 0 && y >= 0) && (x < field.getWidth() && y < field.getHeight())) {
                    if (field.getCell(x, y).getStatus() != 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void changeStatus() {
        status = status == 0 ? 1 : 0;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

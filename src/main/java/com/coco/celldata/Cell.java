package com.coco.celldata;

/**
 * ClassName:Cell
 * Package:com.coco.celldata
 * Description:
 *
 * @Author Coco
 * @Create 2024/2/7 14:46
 * @Version 1.0
 */
public abstract class Cell {

    /**
     * 允许在Cell调用单例field里的方法
     */
    CellField field = CellField.getInstance();
    /**
     * 细胞自身的横坐标
     */
    int x;
    /**
     * 细胞自身的纵坐标
     */
    int y;
    /**
     * 当前细胞的状态
     */
    int status;

    /**
     * 在一回合中每个细胞都会调用一次该方法
     * @return 如果细胞状态被改变了，会返回true，否则返回false。该返回值用于调用处统计被修改的细胞列表
     */
    abstract public boolean cellStrategy();

    /**
     * 每回合开始前调用一次的方法
     */
    abstract public void beforeRoundStrategy();

    /**
     * 每回合结束后调用一次的方法
     */
    abstract public void afterRoundStrategy();

    /**
     * 创建一个坐标为(x,y)的细胞
     * @param x 横坐标
     * @param y 纵坐标
     */
    public Cell(int x,int y){
        this.x = x;
        this.y = y;
    }
    abstract public int getX();
    abstract public int getY();

    /**
     * 初始化时调用的方法
     */
    abstract public void randomIni();

    /**
     * 使当前细胞的状态改变至和目标细胞一致
     * @param tempCell 目标细胞
     */
    abstract public void copy(Cell tempCell);
    abstract public int getStatus();

    abstract public void setStatus(int status);
}

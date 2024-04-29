package com.coco.celldata;

import java.lang.reflect.InvocationTargetException;

/**
 * ClassName:CellField
 * Package:com.coco.celldata
 * Description:
 *
 * @Author Coco
 * @Create 2024/2/7 11:54
 * @Version 1.0
 */
public class CellField {
    /**
     * field的宽
     */
    private int width = 10;
    /**
     * field的高
     */
    private int height = 10;
    /**
     * 用于存放Cell的容器
     */
    private final Cell[][] field = new Cell[width][height];
    /**
     * 用于缓存field的容器
     */
    private final Cell[][] tempField = new Cell[width][height];

    /**
     * 单例模式的实例
     */
    private static final CellField instance;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    static {
        instance = new CellField();
    }

    /**
     * 单例模式中有且只有一个空参私有构造器
     */
    private CellField() {
    }

    /**
     * 单例模式中用于获取实例的方法
     *
     * @return 返回唯一的实例
     */
    public static CellField getInstance() {
        return instance;
    }

    /**
     * 初始化field和tempField，用指定类型的Cell填满两个区域
     *
     * @param cellClass 初始化时要使用的Cell的具体类型
     */
    public void fieldIni(Class cellClass) {
        try {
            for (int x = 0; x < this.width; x++) {
                for (int y = 0; y < this.height; y++) {
                    field[x][y] = (Cell) cellClass.getConstructor(int.class, int.class).newInstance(x, y);
                    tempField[x][y] = (Cell) cellClass.getConstructor(int.class, int.class).newInstance(x, y);
                }
            }
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据坐标从field中取出Cell
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @return 目标Cell
     */
    public Cell getCell(int x, int y) {
        return field[x][y];
    }

    public Cell getTempCell(int x, int y) {
        return tempField[x][y];
    }

    public void save() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                getCell(x,y).copy(getTempCell(x,y));
            }
        }
    }

    public Cell[][] getField() {
        return field;
    }

    public Cell[][] getTempField() {
        return tempField;
    }
}

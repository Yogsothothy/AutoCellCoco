package com.coco.application.components;

import com.coco.celldata.CellField;
import com.coco.celldata.CovidCell;
import com.coco.celldata.Person;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

/**
 * 用于显示全局数据，例如感染者数量、当前天数
 * 这些信息被保存在此处
 */
@Getter
@Setter
public class DataBar {
    private final Text dateText = new Text("天数：");
    private Integer date = 0;
    private final Text dateView = new Text(date.toString());
    private final HBox dateBox = new HBox(dateText, dateView);

    private final Text personNumText = new Text("；总人数：");
    private Integer personNum = 0;
    private final Text personNumView = new Text(personNum.toString());
    private final HBox personNumBox = new HBox(personNumText, personNumView);

    private final Text sNumText = new Text("；S：");
    private Integer sNum = 0;
    private final Text sNumView = new Text(sNum.toString());
    private final HBox sNumBox = new HBox(sNumText, sNumView);

    private final Text eNumText = new Text("；E：");
    private Integer eNum = 0;
    private final Text eNumView = new Text(eNum.toString());
    private final HBox eNumBox = new HBox(eNumText, eNumView);

    private final Text iNumText = new Text("；I：");
    private Integer iNum = 0;
    private final Text iNumView = new Text(iNum.toString());
    private final HBox iNumBox = new HBox(iNumText, iNumView);

    private final Text rNumText = new Text("；R：");
    private Integer rNum = 0;
    private final Text rNumView = new Text(rNum.toString());
    private final HBox rNumBox = new HBox(rNumText, rNumView);

    private final Text dNumText = new Text("；D：");
    private Integer dNum = 0;
    private final Text dNumView = new Text(dNum.toString());
    private final HBox dNumBox = new HBox(dNumText, dNumView);

    @Getter
    private final HBox bar = new HBox(dateBox, personNumBox, sNumBox, eNumBox, iNumBox, rNumBox, dNumBox);

    CellField field = CellField.getInstance();

    /**
     * TODO 初始化
     */
    public DataBar() {

    }

    /**
     * 使显示数据响应变化
     */
    public void update() {
        int[] values = new int[]{0, 0, 0, 0, 0};
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                CovidCell cell = (CovidCell) field.getTempCell(x, y);
//                if (cell.getLocation()!= LocationType.HOUSE){
//                    continue;
//                }
                for (Person person : (cell).getPeople()) {
                    values[person.getStatus().CODE - 1]++;
                }
            }
        }
        sNum = values[0];
        eNum = values[1];
        iNum = values[2];
        rNum = values[3];
        dNum = values[4];
        personNum = sNum + eNum + iNum + rNum + dNum;

        dateView.setText(date.toString());
        personNumView.setText(personNum.toString());
        sNumView.setText(sNum.toString());
        eNumView.setText(eNum.toString());
        iNumView.setText(iNum.toString());
        rNumView.setText(rNum.toString());
        dNumView.setText(dNum.toString());
        date++;
    }
}

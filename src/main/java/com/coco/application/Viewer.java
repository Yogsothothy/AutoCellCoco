package com.coco.application;

import com.coco.application.components.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Map;

/**
 * ClassName: Application
 * Package: com.coco.application
 * Description:程序的主界面
 *             TODO 用一个BorderPane来容纳所有组件，CellFieldViewer会被放置在中间
 *                  还需要实现的功能有：（左）暂停/继续；调节速度,保存（暂停后可以通过点击细胞修改其内部状态(ToolBar
 *                                  （右）设定被修改的细胞会成为何种状态(EditBar
 *                                  （上）待定
 *                                  （下）部分数据显示（DataBar
 *
 * @Author Coco
 * @Create 2024/2/18 22:22
 * @Version 1.0
 */
public class Viewer extends Application {
    CellFieldViewer cellFieldViewer = new CellFieldViewer();
    ApplicationStorage storage = ApplicationStorage.getInstance();
    ToolBar toolBar = new ToolBar(cellFieldViewer);
    EditBar editBar = new EditBar();
    DataBar dataBar = new DataBar();
    GlobalBar globalBar = new GlobalBar();
    ChartPainter chartPainter = new ChartPainter();

    @Override
    public void start(Stage stage) throws Exception {
        Map map = storage.getMap();
        map.put("editBar",editBar);
        map.put("dataBar",dataBar);
        map.put("chartPainter",chartPainter);
        cellFieldViewer.init();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(cellFieldViewer.getGridPane());
        borderPane.setLeft(toolBar.getBar());
        borderPane.setRight(editBar.getBar());
        borderPane.setBottom(dataBar.getBar());
        borderPane.setTop(globalBar.getBar());

        cellFieldViewer.run();//今后把这个功能移动到组件里

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("AutoCellCoco");
        stage.show();
    }
}

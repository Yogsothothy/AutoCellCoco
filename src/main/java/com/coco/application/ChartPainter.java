package com.coco.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * ClassName:ChartPainter
 * Package:com.coco.application
 * Description:
 *
 * @Author Coco
 * @Create 2024/5/19 22:33
 * @Version 1.0
 */
public class ChartPainter {
    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    JFreeChart lineChart = ChartFactory.createLineChart(
            "感染者数趋势图",
            "天数",
            "感染者数",
            dataset,
            PlotOrientation.VERTICAL,
            false, true, false
    );
    public void paint(){
        ChartFrame frame = new ChartFrame("感染者数趋势图", lineChart);
        frame.pack();
        frame.setVisible(true);
    }
    public void sample(int count,Integer date){
        dataset.addValue(count,"count",date);
    }
}

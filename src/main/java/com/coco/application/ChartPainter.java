package com.coco.application;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
    private XYSeries series1 = new XYSeries("Series 1");
    private XYSeriesCollection dataset1 = new XYSeriesCollection();
    private XYSeries series2 = new XYSeries("Series 2");
    private XYSeriesCollection dataset2 = new XYSeriesCollection();

    public ChartPainter() {

    }

    public void paint() {
        dataset1.addSeries(series1);
        dataset2.addSeries(series2);
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "infect chart",
                "days",
                "E and I count",
                dataset1,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        XYPlot plot = lineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, java.awt.Color.RED);
        plot.setRenderer(renderer);

        plot.setDataset(1, dataset2);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer2.setSeriesPaint(0, java.awt.Color.BLUE);
        plot.setRenderer(1, renderer2);

        ChartPanel chartPanel = new ChartPanel(lineChart);

        ChartFrame frame = new ChartFrame("", lineChart);
        frame.pack();
        frame.setVisible(true);
    }

    public void sample(Integer count,Integer dCount, Integer date) {
        series1.add(date,count);
        series2.add(date,dCount);
    }
}

package graphvisualizer;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class ChartMaker {
    public JFreeChart createChart(DefaultXYDataset dataset1, DefaultXYDataset dataset2) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            "",
            "Raman Shift",
            "Intensity",
            null, // Set dataset to null initially
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer1 = new XYSplineRenderer();
        renderer1.setSeriesPaint(0, new Color(0,153,0));
        renderer1.setSeriesShapesVisible(0, false);
        plot.setDataset(0, dataset1); // Set the first dataset
        plot.setRenderer(0, renderer1); // Set the renderer for the first dataset

        XYLineAndShapeRenderer renderer2 = new XYSplineRenderer();
        renderer2.setSeriesPaint(0, Color.RED);
        renderer2.setSeriesShapesVisible(0, false);
        plot.setDataset(1, dataset2); // Set the second dataset
        plot.setRenderer(1, renderer2); // Set the renderer for the second dataset

        return chart;
    }
    
    public JFreeChart createChart(DefaultXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            "",
            "Raman Shift",
            "Intensity",
            null, // Set dataset to null initially
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer1 = new XYSplineRenderer();
        renderer1.setSeriesPaint(0, new Color(0,153,0));
        renderer1.setSeriesShapesVisible(0, false);
        plot.setDataset(0, dataset); // Set the first dataset
        plot.setRenderer(0, renderer1); // Set the renderer for the first dataset

        return chart;
    }
}

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {
    public static void main(String[] args) {
        // Create a dataset for the chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1, "Series 1", "Category 1");
        dataset.addValue(2, "Series 1", "Category 2");
        dataset.addValue(3, "Series 1", "Category 3");
        dataset.addValue(4, "Series 1", "Category 4");
        dataset.addValue(5, "Series 1", "Category 5");

        // Create the chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Line Chart Example", // Chart title
                "Category", // X-axis label
                "Value", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation
                true, // Include legend
                true, // Include tooltips
                false // Include URLs
        );

        // Create a Swing frame to display the chart
        JFrame frame = new JFrame("Line Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create a chart panel and add it to the frame
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.getContentPane().add(chartPanel);

        // Display the frame
        frame.setVisible(true);
    }
}

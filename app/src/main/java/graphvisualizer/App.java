package graphvisualizer;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class App extends ApplicationFrame {
    
    private static ChartPanel chartPanel1;
    private static ChartPanel chartPanel2;
    
    private double[][] data1 = { { 0 }, { 0 } };
    private double[][] data2 = { { 0 }, { 0 } };
    
    public DefaultXYDataset dataset1;
    public DefaultXYDataset dataset2;
    
    public Boolean isSplitScreen = false;
    
    public App(String title) {
        super(title);
    }
        
    private XYDataset createDataset1() {
        dataset1 = new DefaultXYDataset();
        dataset1.removeSeries("Negative");
        dataset1.addSeries("Negative", data1);
        return dataset1;
    }
    
    private XYDataset createDataset2() {
        dataset2 = new DefaultXYDataset();
        dataset2.removeSeries("Positive");
        dataset2.addSeries("Positive", data2);
        return dataset2;
    }
    
    public JPanel sidebar() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setPreferredSize(new Dimension(200, 800));
        sidebarPanel.setBackground(Color.LIGHT_GRAY);
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
   
        JButton firstButton = new JButton("Open File");
        firstButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                //fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "txt"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    FileReader reader = new FileReader(selectedFile.getAbsolutePath());
                    data1 = DataProcessor.processData(reader.getLines());
                    createDataset1();
                    XYPlot plot = chartPanel1.getChart().getXYPlot();
                    plot.setDataset(0, dataset1);
                    
                    chartPanel1.repaint();
                    chartPanel2.repaint();
                }
            }
        });
        sidebarPanel.add(firstButton);
        
        JButton secondButton = new JButton("Open File");
        secondButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                //fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "txt"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    FileReader reader = new FileReader(selectedFile.getAbsolutePath());
                    
                    data2 = DataProcessor.processData(reader.getLines());
                    createDataset2();
                    XYPlot plot = chartPanel2.getChart().getXYPlot();
                    plot.setDataset(1, dataset2);
                    
                    chartPanel1.repaint();
                    chartPanel2.repaint();
                }
            }
        });
        sidebarPanel.add(secondButton);
        
        JToggleButton toggleButton = new JToggleButton("Toggle");
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                if (toggleButton.isSelected()) {
//                    chartPanel1.setSize(new Dimension(1000, 700));
//                    chartPanel2.setSize(new Dimension(1000, 0));
//                    isSplitScreen = true;
//                } else {
//                    chartPanel1.setSize(new Dimension(1000, 350));
//                    chartPanel2.setSize(new Dimension(1000, 350));
//                    isSplitScreen = false;
//                }
            }
        });
        sidebarPanel.add(toggleButton);
        
        return sidebarPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChartMaker chartMaker = new ChartMaker();
            App window = new App("Visualizer");
            JFreeChart chart1 = chartMaker.createChart(window.dataset1, window.dataset2);
            JFreeChart chart2 = chartMaker.createChart(window.dataset2);
            chartPanel1 = new ChartPanel(chart1);
            chartPanel1.setPreferredSize(new Dimension(1000, 350));
            chartPanel2 = new ChartPanel(chart2);
            chartPanel2.setPreferredSize(new Dimension(1000, 350));
            
            JSplitPane chartSplitPane = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT,
                    chartPanel1,
                    chartPanel2
            );
            
            JPanel sidebarPanel = window.sidebar();

            JSplitPane splitPane = new JSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT, 
                    chartSplitPane, 
                    sidebarPanel
            );
            splitPane.setPreferredSize(new Dimension(1200, 800));
            splitPane.setDividerLocation(1000);

            window.getContentPane().add(splitPane);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setVisible(true);
        });
    }
}
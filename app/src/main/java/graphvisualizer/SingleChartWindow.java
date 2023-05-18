package graphvisualizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class SingleChartWindow extends ApplicationFrame {        
    public static ChartPanel chartPanel;
    
    public double[][] data1 = { { 0 }, { 0 } };
    public double[][] data2 = { { 0 }, { 0 } };
    
    public DefaultXYDataset dataset1;
    public DefaultXYDataset dataset2;
    
    public SingleChartWindow(String title) {
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
                    XYPlot plot = chartPanel.getChart().getXYPlot();
                    plot.setDataset(0, dataset1);
                    
                    chartPanel.repaint();
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
                    XYPlot plot = chartPanel.getChart().getXYPlot();
                    plot.setDataset(1, dataset2);
                    
                    chartPanel.repaint();
                }
            }
        });
        sidebarPanel.add(secondButton);
        
        return sidebarPanel;
    }
}

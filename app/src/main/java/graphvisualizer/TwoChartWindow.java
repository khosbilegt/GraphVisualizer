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

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.Box;
import javax.swing.JLabel;

public class TwoChartWindow extends ApplicationFrame {        
    public static ChartPanel chartPanel1;
    public static ChartPanel chartPanel2;
    
    public double[][] data1 = { { 0 }, { 0 } };
    public double[][] data2 = { { 0 }, { 0 } };
    
    public DefaultXYDataset dataset1;
    public DefaultXYDataset dataset2;
    
    public TwoChartWindow(String title) {
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
            //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Box firstBox = Box.createHorizontalBox();
        JLabel firstLabel = new JLabel("1 дахь дата");
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
        firstBox.add(firstLabel);
        firstBox.add(firstButton);
        
        Box secondBox = Box.createHorizontalBox();
        JLabel secondLabel = new JLabel("2 дахь дата");
        sidebarPanel.add(secondLabel);
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
        secondBox.add(secondLabel);
        secondBox.add(secondButton);
        
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(firstBox);
        verticalBox.add(secondBox);
        sidebarPanel.add(verticalBox);
        
        return sidebarPanel;
    }
}

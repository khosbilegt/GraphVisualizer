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
import static graphvisualizer.SingleChartWindow.chartPanel;
import java.util.Arrays;
import java.util.List;
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
    
    private void firstButtonFileChoose() {
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
    
    private void secondButtonFileChoose() {
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
    
    public JPanel sidebar() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setPreferredSize(new Dimension(200, 800));
        sidebarPanel.setBackground(Color.LIGHT_GRAY);
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
   
        JButton firstFileButton = new JButton("Open File");
        firstFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstButtonFileChoose();
            }
        });
        sidebarPanel.add(firstFileButton);
        
        JButton firstTextButton = new JButton("Use Text");
        firstTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = CustomDialog.showDialog(null);
                String[] lines = input.split("\n");
                List<String> linesList = Arrays.asList(lines);
                data1 = DataProcessor.processData(linesList);
                createDataset1();
                XYPlot plot = chartPanel1.getChart().getXYPlot();
                plot.setDataset(0, dataset1);

                chartPanel1.repaint();
                chartPanel2.repaint();
            }
        });
        sidebarPanel.add(firstTextButton);
        
        JButton secondFileButton = new JButton("Open File");
        secondFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secondButtonFileChoose();
            }
        });
        sidebarPanel.add(secondFileButton);
        
        JButton secondTextButton = new JButton("Use Text");
        secondTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = CustomDialog.showDialog(null);
                String[] lines = input.split("\n");
                List<String> linesList = Arrays.asList(lines);
                
                data2 = DataProcessor.processData(linesList);
                createDataset2();
                XYPlot plot = chartPanel2.getChart().getXYPlot();
                plot.setDataset(1, dataset2);

                chartPanel1.repaint();
                chartPanel2.repaint();
            }
        });
        sidebarPanel.add(secondTextButton);
        
        return sidebarPanel;
    }
}

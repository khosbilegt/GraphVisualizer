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

import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
        
        JButton firstTextButton = new JButton("Use Text");
        firstTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = TextInputDialog.showDialog(null).get(0);
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
        
        JButton secondFileButton = new JButton("Open File");
        secondFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secondButtonFileChoose();
            }
        });
        
        JButton secondTextButton = new JButton("Use Text");
        secondTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = TextInputDialog.showDialog(null).get(0);
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
        
        JLabel firstLabel = new JLabel("1-р Хүснэгт");
        firstLabel.setHorizontalAlignment(SwingConstants.LEFT);
        Font firstFont = firstLabel.getFont();
        firstLabel.setFont(firstFont.deriveFont(Font.PLAIN, 12f));
        
        JLabel secondLabel = new JLabel("2-р Хүснэгт");
        secondLabel.setHorizontalAlignment(SwingConstants.LEFT);
        Font secondFont = secondLabel.getFont();
        secondLabel.setFont(secondFont.deriveFont(Font.PLAIN, 12f));
        
        Box firstHorizontalBox = Box.createHorizontalBox();
        firstHorizontalBox.add(firstFileButton);
        firstHorizontalBox.add(firstTextButton);
        
        Box secondHorizontalBox = Box.createHorizontalBox();
        secondHorizontalBox.add(secondFileButton);
        secondHorizontalBox.add(secondTextButton);
        
        sidebarPanel.add(firstLabel);
        sidebarPanel.add(firstHorizontalBox);
        sidebarPanel.add(secondLabel);
        sidebarPanel.add(secondHorizontalBox);
        
        return sidebarPanel;
    }
}

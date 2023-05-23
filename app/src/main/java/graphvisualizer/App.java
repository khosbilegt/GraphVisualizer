package graphvisualizer;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;

public class App extends ApplicationFrame  {
    
    File databaseFolder = FileSystemView.getFileSystemView().getHomeDirectory();
   
    public App(String title) {
        super(title);
    }
    
    private void newComparisonWindow() {
        ChartMaker chartMaker = new ChartMaker();

        SingleChartWindow window = new SingleChartWindow("Comparison Visualization");
        JFreeChart chart1 = chartMaker.createChart(window.dataset1, window.dataset2);
        window.chartPanel = new ChartPanel(chart1);
        window.chartPanel.setPreferredSize(new Dimension(1000, 700));

        JPanel sidebarPanel = window.sidebar();

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, 
                window.chartPanel, 
                sidebarPanel
        );
        splitPane.setPreferredSize(new Dimension(1200, 800));
        splitPane.setDividerLocation(1000);

        window.getContentPane().add(splitPane);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
    
    private void newDualWindow() {
        ChartMaker chartMaker = new ChartMaker();
            
        TwoChartWindow window = new TwoChartWindow("Comparison Visualization");
        JFreeChart chart1 = chartMaker.createChart(window.dataset1);
        JFreeChart chart2 = chartMaker.createChart(window.dataset2);
        window.chartPanel1 = new ChartPanel(chart1);
        window.chartPanel1.setPreferredSize(new Dimension(1000, 350));
        window.chartPanel2 = new ChartPanel(chart2);
        window.chartPanel2.setPreferredSize(new Dimension(1000, 350));

        JSplitPane chartSplitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                window.chartPanel1,
                window.chartPanel2
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
    }
    
    private void addFileToDatabase() {
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            FileReader reader = new FileReader(selectedFile.getAbsolutePath());
            String input = CustomDialog.showDialog(null);
        }
    }
        
    private JPanel mainArea() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 400));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        try {
            //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel label = new JLabel("Та ашиглах төрлөө сонгоно уу");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20f));
        
        JButton comparisonButton = new JButton("Давхарласан Хүснэгт");
        comparisonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newComparisonWindow();
            }
        });
        
        JButton dualButton = new JButton("Салангид Хүснэгт");
        dualButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newDualWindow();
            }
        });
        
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createHorizontalGlue());
        horizontalBox.add(comparisonButton);
        horizontalBox.add(Box.createRigidArea(new Dimension(10, 0))); // Adds a fixed horizontal space of 10 pixels
        horizontalBox.add(dualButton);
        horizontalBox.add(Box.createHorizontalGlue());

        comparisonButton.setMaximumSize(new Dimension(200, 100));
        dualButton.setMaximumSize(new Dimension(200, 100));
        
        JButton chooseFolderButton = new JButton("Өгөгдлийн сан сонгох");
        chooseFolderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    databaseFolder = fileChooser.getSelectedFile();
                    System.out.println(databaseFolder.getAbsolutePath());
                }
            }
        });
        
        JButton addFileButton = new JButton("Файл нэмэх");
        addFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFileToDatabase();
            }
        });
        
        JButton addDialogButton = new JButton("Гараас нэмэх");
        addDialogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //newDualWindow();
                String input = CustomDialog.showDialog(null);
            }
        });
        
        Box addHorizontalBox = Box.createHorizontalBox();
        addHorizontalBox.add(Box.createHorizontalGlue());
        addHorizontalBox.add(chooseFolderButton);
        addHorizontalBox.add(Box.createRigidArea(new Dimension(10, 0))); 
        addHorizontalBox.add(addFileButton);
        addHorizontalBox.add(Box.createRigidArea(new Dimension(10, 0))); // Adds a fixed horizontal space of 10 pixels
        addHorizontalBox.add(addDialogButton);
        addHorizontalBox.add(Box.createHorizontalGlue());

        chooseFolderButton.setMaximumSize(new Dimension(200, 100));
        addFileButton.setMaximumSize(new Dimension(200, 100));
        addDialogButton.setMaximumSize(new Dimension(200, 100));
        
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(label);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(addHorizontalBox);
        verticalBox.add(Box.createVerticalStrut(10));
        verticalBox.add(horizontalBox);

        panel.add(verticalBox);
        
        pack();
        return panel;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            App app = new App("Visualizer");
            JPanel mainArea = app.mainArea();
            
            app.getContentPane().add(mainArea);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.pack();
            app.setVisible(true);
        });
    }
}
package graphvisualizer;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;

public class App extends ApplicationFrame  {
    
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
        label.setAlignmentY(0.5f);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, 20f));
        
        JButton comparisonButton = new JButton("Comparison Chart");
        comparisonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newComparisonWindow();
            }
        });
        
        JButton dualButton = new JButton("Dual Chart");
        dualButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newDualWindow();
            }
        });
        
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(comparisonButton);
        horizontalBox.add(dualButton);
        
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(label);
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
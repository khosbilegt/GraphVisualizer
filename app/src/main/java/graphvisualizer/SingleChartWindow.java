package graphvisualizer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
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
    
    File databaseFolder = FileSystemView.getFileSystemView().getHomeDirectory();
    List<String> files = new ArrayList<>();
    
    public SingleChartWindow(String title, File databaseFolder) {
        super(title);
        this.databaseFolder = databaseFolder;
    }
            
    private XYDataset createDataset1(String fileName) {
        dataset1 = new DefaultXYDataset();
        dataset1.removeSeries(fileName);
        dataset1.addSeries(fileName, data1);
        return dataset1;
    }
    
    private XYDataset createDataset2(String fileName) {
        dataset2 = new DefaultXYDataset();
        dataset2.removeSeries(fileName);
        dataset2.addSeries(fileName, data2);
        return dataset2;
    }
    
    public JPanel sidebar() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setPreferredSize(new Dimension(200, 800));
        sidebarPanel.setBackground(Color.LIGHT_GRAY);
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        files = FolderHandler.getAllFilesInFolder(databaseFolder.getAbsolutePath());
        
        DefaultListModel<String> model = new DefaultListModel<>();
        for(int i = 0; i < files.size(); i++) {
            if(files.get(i).contains(".txt")) {
                model.addElement(files.get(i));
            }
        }

        JList<String> buttonList = new JList<>(model);
        buttonList.setCellRenderer(new ButtonCellRenderer());
        buttonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        buttonList.setVisibleRowCount(-1);
        buttonList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = buttonList.locationToIndex(e.getPoint());
                if (index != -1) {
                    FileReader reader = new FileReader(databaseFolder.getAbsolutePath() + "/" + files.get(index));
                    if (SwingUtilities.isRightMouseButton(e)) {
                        data1 = DataProcessor.processData(reader.getLines());
                        createDataset1(files.get(index));
                        XYPlot plot = chartPanel.getChart().getXYPlot();
                        plot.setDataset(0, dataset1);
                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        data2 = DataProcessor.processData(reader.getLines());
                        createDataset2(files.get(index));
                        XYPlot plot = chartPanel.getChart().getXYPlot();
                        plot.setDataset(1, dataset2);
                    }
                    chartPanel.repaint();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(buttonList);
        
        sidebarPanel.add(scrollPane);
        return sidebarPanel;
    }
}


package graphvisualizer;


import graphvisualizer.ButtonCellRenderer;
import graphvisualizer.DataProcessor;
import graphvisualizer.FileReader;
import graphvisualizer.FolderHandler;
import static graphvisualizer.TwoChartWindow.chartPanel1;
import static graphvisualizer.TwoChartWindow.chartPanel2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;



public class FileListWindow extends ApplicationFrame {
    File databaseFolder = FileSystemView.getFileSystemView().getHomeDirectory();
    List<String> files = new ArrayList<>();
    
    public FileListWindow(String title, File databaseFolder) {
        super(title);
        this.databaseFolder = databaseFolder;
    }
    
    public JPanel mainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(200, 800));
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
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

        JScrollPane scrollPane = new JScrollPane(buttonList);
        
        mainPanel.add(scrollPane);
        return mainPanel;
    }
}

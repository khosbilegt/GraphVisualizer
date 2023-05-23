package graphvisualizer;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileInputDialog extends JDialog {
    private JTextField fileNameField;
    
    private FileInputDialog(JFrame parent) {
        super(parent, "Мэдээлэл оруулах хэсэг", true);
        
        // Row 1
        fileNameField = new JTextField();
        fileNameField.setPreferredSize(new Dimension(200, 50));
        
        Box nameHorizontalBox = Box.createHorizontalBox();
        nameHorizontalBox.add(new JLabel("Өгөгдлийн нэрийг оруулна уу:"));
        nameHorizontalBox.add(Box.createHorizontalGlue());
        nameHorizontalBox.add(fileNameField);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nameHorizontalBox);
        panel.add(okButton);
        
        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }
    
    public static String showDialog(JFrame parent) {
        FileInputDialog dialog = new FileInputDialog(parent);
        dialog.setVisible(true);
        return dialog.fileNameField.getText();
    }
}
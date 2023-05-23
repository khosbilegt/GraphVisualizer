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

public class CustomDialog extends JDialog {
    private JTextArea textArea;
    private JTextField fileNameField;
    
    private CustomDialog(JFrame parent) {
        super(parent, "Мэдээлэл оруулах хэсэг", true);
        
        // Row 1
        fileNameField = new JTextField();
        fileNameField.setPreferredSize(new Dimension(200, 50));
        
        Box nameHorizontalBox = Box.createHorizontalBox();
        nameHorizontalBox.add(new JLabel("Өгөгдлийн нэрийг оруулна уу:"));
        nameHorizontalBox.add(Box.createHorizontalGlue());
        nameHorizontalBox.add(fileNameField);
        
        // Row 2
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(200, 100));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        Box contentHorizontalBox = Box.createHorizontalBox();
        contentHorizontalBox.add(new JLabel("Мэдээллийг оруулна уу:"));
        contentHorizontalBox.add(Box.createHorizontalGlue());
        contentHorizontalBox.add(scrollPane);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose());
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nameHorizontalBox);
        panel.add(contentHorizontalBox);
        panel.add(okButton);
        
        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }
    
    public static String showDialog(JFrame parent) {
        CustomDialog dialog = new CustomDialog(parent);
        dialog.setVisible(true);
        return dialog.textArea.getText();
    }
}
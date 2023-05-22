package graphvisualizer;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CustomDialog extends JDialog {
    private JTextArea textArea;
    
    private CustomDialog(JFrame parent) {
        super(parent, "Мэдээлэл оруулах хэсэг", true);
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(200, 100));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JButton okButton = new JButton("OK");
        
        okButton.addActionListener(e -> dispose()); // Close the dialog
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Мэдээллийг оруулна уу:"));
        panel.add(scrollPane);
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
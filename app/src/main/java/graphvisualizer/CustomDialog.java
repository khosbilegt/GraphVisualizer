package graphvisualizer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomDialog extends JDialog {
    private JTextField textField;
    
        private CustomDialog(JFrame parent) {
        super(parent, "Мэдээлэл оруулах хэсэг", true);
        textField = new JTextField(20);
        JButton okButton = new JButton("OK");
        
        okButton.addActionListener(e -> dispose()); // Close the dialog
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Мэдээллийг оруулна уу:"));
        panel.add(textField);
        panel.add(okButton);
        
        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }
    
    public static String showDialog(JFrame parent) {
        CustomDialog dialog = new CustomDialog(parent);
        dialog.setVisible(true);
        return dialog.textField.getText();
    }
}
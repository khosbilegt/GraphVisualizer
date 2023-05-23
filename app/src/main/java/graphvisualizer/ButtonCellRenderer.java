package graphvisualizer;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class ButtonCellRenderer extends JButton implements ListCellRenderer<String> {
    public ButtonCellRenderer() {
        setOpaque(true);
    }
    
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
            boolean isSelected, boolean cellHasFocus) {
        setText(value);
        return this;
    }
}
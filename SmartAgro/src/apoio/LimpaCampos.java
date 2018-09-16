package apoio;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class LimpaCampos {

    public static void limparCampos(Container container) {
        Component c[] = container.getComponents();
        for (int i = 0; i < c.length; i++) {
            if (c[i] instanceof JFormattedTextField) {
                JFormattedTextField field = (JFormattedTextField) c[i];
                field.setValue(null);
                field.setText("");
                field.setBackground(Color.white);
            } else if (c[i] instanceof JTextField) {
                JTextField field = (JTextField) c[i];
                field.setText("");
                field.setBackground(Color.white);
            } else if (c[i] instanceof JScrollPane) {
                if ((((JScrollPane) c[i]).getViewport().getComponent(0)) instanceof JTextArea) {
                    ((JTextArea) ((JScrollPane) c[i]).getViewport().getComponent(0)).setText("");
                    ((JTextArea) ((JScrollPane) c[i]).getViewport().getComponent(0)).setBackground(Color.white);
                }
            } else if (c[i] instanceof JComboBox) {
                JComboBox cb = (JComboBox) c[i];
                cb.setSelectedIndex(-1);
                cb.setBackground(null);
            } else if (c[i] instanceof JCheckBox) {
                JCheckBox ckb = (JCheckBox) c[i];
                ckb.setSelected(false);
                ckb.setBackground(null);
            } else if (c[i] instanceof JRadioButton) {
                JRadioButton rb = (JRadioButton) c[i];
                rb.setSelected(false);
                rb.setBackground(null);
            } else if (c[i] instanceof JDateChooser) {
                JDateChooser dt = (JDateChooser) c[i];
                dt.setDate(null);
                dt.setBackground(Color.white);
            }
        }
    }
}

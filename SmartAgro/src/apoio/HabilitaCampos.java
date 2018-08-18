/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Morgana
 */
public class HabilitaCampos {

    public static void habilitaCampos(Container container, boolean flag) {
        Component c[] = container.getComponents();
        for (int i = 0; i < c.length; i++) {
            if (c[i] instanceof JFormattedTextField) {
                JFormattedTextField field = (JFormattedTextField) c[i];
                field.setEditable(flag);
            } else if (c[i] instanceof JTextField) {
                JTextField field = (JTextField) c[i];
                field.setEditable(flag);
            } else if (c[i] instanceof JScrollPane) {
                ((JTextArea) ((JScrollPane) c[i]).getViewport().getComponent(0)).setEditable(flag);
            } else if (c[i] instanceof JComboBox) {
                JComboBox cb = (JComboBox) c[i];
                cb.setEnabled(flag);
            } else if (c[i] instanceof JCheckBox) {
                JCheckBox ckb = (JCheckBox) c[i];
                ckb.setEnabled(flag);
            } else if (c[i] instanceof JButton) {
                JButton btn = (JButton) c[i];
                btn.setEnabled(flag);
            } else if (c[i] instanceof JDateChooser) {
                JDateChooser field = (JDateChooser) c[i];
                field.setEnabled(flag);
            }
        }
    }
}

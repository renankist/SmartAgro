/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Morgana
 */
public class ColoreCampos {
    
    private static Color amarelinho = new Color(255, 255, 153);
    
    public static void pintarCampo(Component c, boolean pintar) {
        if (c instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) c;
            if (pintar) {
                field.setBackground(amarelinho);
            } else {
                field.setBackground(Color.white);
            }
        } else if (c instanceof JTextField) {
            JTextField field = (JTextField) c;
            if (pintar) {
                field.setBackground(amarelinho);
            } else {
                field.setBackground(Color.white);
            }
        } else if (c instanceof JScrollPane) {
            if (pintar) {
                ((JTextArea) ((JScrollPane) c).getViewport().getComponent(0)).setBackground(amarelinho);
            } else {
                ((JTextArea) ((JScrollPane) c).getViewport().getComponent(0)).setBackground(Color.white);
            }
        } else if (c instanceof JComboBox) {
            JComboBox field = (JComboBox) c;
            if (pintar) {
                field.setBackground(amarelinho);
            } else {
                field.setBackground(null);
            }
        } else if (c instanceof JCheckBox) {
            JCheckBox field = (JCheckBox) c;
            if (pintar) {
                field.setBackground(amarelinho);
            } else {
                field.setBackground(null);
            }
        } else if (c instanceof JRadioButton) {
            JRadioButton field = (JRadioButton) c;
            if (pintar) {
                field.setBackground(amarelinho);
            } else {
                field.setBackground(null);
            }
        } else if (c instanceof JDateChooser) {
            JDateChooser field = (JDateChooser) c;
            if (pintar) {
                ((JTextField)field.getDateEditor().getUiComponent()).setBackground(amarelinho);
            } else {
                ((JTextField)field.getDateEditor().getUiComponent()).setBackground(Color.white);
            }
        }
    }
}

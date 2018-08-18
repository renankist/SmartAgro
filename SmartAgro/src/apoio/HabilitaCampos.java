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
<<<<<<< HEAD
=======

    public static void controlaBotoes(javax.swing.event.ChangeEvent evt, JButton btnSalvar, JButton btnEditar, JButton btnExcluir) {
        JTabbedPane abas = (JTabbedPane) evt.getSource();
        JPanel painel = (JPanel) abas.getSelectedComponent();

        switch (painel.getName()) {
            case "pnlCadastro":
                btnSalvar.setEnabled(true);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                break;

            case "pnlConsulta":
                btnSalvar.setEnabled(false);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                break;

            case "pnlRelatorio":
                btnSalvar.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                break;
        }
    }

    public static void controlaPainelCadastro(java.awt.event.FocusEvent evt, boolean emEdicao) {
        if (emEdicao) {
            return;
        }

        JTabbedPane abas = (JTabbedPane) evt.getSource();
        JPanel painel = (JPanel) abas.getSelectedComponent();

        if (painel.getName() == "pnlCadastro") {
            for (Component component : painel.getComponents()) {
                if (component instanceof JPanel) {
                    LimpaCampos.limparCampos((JPanel) component);
                }
            }
        }
    }
>>>>>>> parent of bce151b... Merge branch 'master' of https://github.com/renankist/SmartAgro
}

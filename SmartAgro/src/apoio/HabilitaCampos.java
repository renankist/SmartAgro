/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import com.toedter.calendar.JDateChooser;
import entidade.Permissaoacesso;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import smartagro.VerificaPermissao;

/**
 *
 * @author Morgana
 */
public class HabilitaCampos {

    public static void habilitaCampos(Container container, boolean flag) {
        Component c[] = container.getComponents();
        for (int i = 0; i < c.length; i++) {
            habilitaCampo(c[i], flag);
        }
    }

    public static void habilitaCampo(Component comp, boolean flag) {
        if (comp instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) comp;
            field.setEditable(flag);
        } else if (comp instanceof JTextField) {
            JTextField field = (JTextField) comp;
            field.setEditable(flag);
        } else if (comp instanceof JScrollPane) {
            ((JTextArea) ((JScrollPane) comp).getViewport().getComponent(0)).setEditable(flag);
        } else if (comp instanceof JComboBox) {
            JComboBox cb = (JComboBox) comp;
            cb.setEnabled(flag);
        } else if (comp instanceof JCheckBox) {
            JCheckBox ckb = (JCheckBox) comp;
            ckb.setEnabled(flag);
        } else if (comp instanceof JButton) {
            JButton btn = (JButton) comp;
            btn.setEnabled(flag);
        } else if (comp instanceof JDateChooser) {
            JDateChooser field = (JDateChooser) comp;
            field.setEnabled(flag);
        }
    }

    public static void controlaBotoes(Object tela, VerificaPermissao permissoes, JButton btnSalvar, JButton btnEditar, JButton btnExcluir) {
        JTabbedPane abas = (JTabbedPane) tela;
        JPanel painel = (JPanel) abas.getSelectedComponent();  

        // Ajusta os botoes conforme as pemissoes
        if (permissoes != null) {
            permissoes.ajustaInterfacePermissao();
        }

        switch (painel.getName()) {
            case "pnlCadastro":
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                break;

            case "pnlConsulta":
                btnSalvar.setEnabled(false);
                break;

            case "pnlRelatorio":
                btnSalvar.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                break;
        }
    }
    
    public static void controlaBotaoSalvar(boolean editando, JButton btnSalvar, VerificaPermissao permissoes){
        if (editando) {
            btnSalvar.setEnabled(!btnSalvar.isEnabled());
        } else {
            permissoes.ajustaInterfacePermissao();
        }
    }

    public static void controlaPainelCadastro(java.awt.event.FocusEvent evt, boolean emEdicao) {
        if (emEdicao) {
            return;
        }

        JTabbedPane abas = (JTabbedPane) evt.getSource();
        JPanel painel = (JPanel) abas.getSelectedComponent();

        // Tenta limpar o campo, se não limpar, é porque tem mais panels dentro do panel principal
        try {
           LimpaCampos.limparCampos(painel);
        } catch (Exception e) {
        }

        if (painel.getName() == "pnlCadastro") {
            for (Component component : painel.getComponents()) {
                if (component instanceof JPanel) {
                  LimpaCampos.limparCampos((JPanel) component);
                }
            }
        }
    }
}

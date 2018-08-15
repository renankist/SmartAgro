/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.util.ArrayList;
import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Morgana
 */
public class VerificadorCampos extends InputVerifier {

    private JComponent[] components;
    private ArrayList<String> mensagens;

    public VerificadorCampos(JComponent[] components) {
        this.components = components;
    }

    public boolean validaCampos() {
        boolean inputOK = false;
        String mensagem = "";
        JComponent campoFoco = null;

        // Método da própria classe InputVerifier que chama a função que para validar os campos
        // Se este método retornar false, o foco permanece no campo que não foi validado
        if (this.components != null) {
            for (JComponent campo : components) {

                inputOK = shouldYieldFocus(campo);

                if (!inputOK) {

                    if (mensagem.trim().isEmpty()) {
                        mensagem = retornaMensagem(campo);
                    }

                    if (campoFoco == null) {
                        campoFoco = campo;
                    }
                }
            }
        }

        if (!mensagem.trim().isEmpty()) {
            Mensagem.mostraInformacao("Atenção", mensagem);
            campoFoco.requestFocus();
        }

        return inputOK;
    }

    @Override
    public boolean verify(JComponent input) {
        // Aqui vai verificar se os campos estão OK
        boolean inputOK = validaCampo(input);

        // Pinta o campo caso não estiver de acordo
        ColoreCampos.pintarCampo(input, !inputOK);

        return inputOK;
    }

    private boolean validaCampo(JComponent input) {

        boolean campoOK = false;

        if (input instanceof JTextField) {
            campoOK = !(((JTextField) input).getText().trim().isEmpty());
        } else if (input instanceof JFormattedTextField) {
            campoOK = !(((JFormattedTextField) input).getText().trim().isEmpty());
        } else if (input instanceof JComboBox) {
            campoOK = !(((JComboBox) input).getSelectedIndex() == 0);
        } else if (input instanceof JRadioButton) {
            campoOK = !(((JRadioButton) input).getSelectedObjects() == null);
        } else {
            campoOK = true;
        }

        return campoOK;
    }

    private String retornaMensagem(JComponent input) {

        String mensagem = "";

        if (input instanceof JTextField || input instanceof JFormattedTextField) {
            mensagem = "Preencha o campo corretamente";
        } else if (input instanceof JComboBox || input instanceof JRadioButton) {
            mensagem = "Selecione uma opção";
        } else {
            mensagem = "";
        }

        return mensagem;
    }
}

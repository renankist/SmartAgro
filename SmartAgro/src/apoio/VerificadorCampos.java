/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Morgana
 */
public class VerificadorCampos extends InputVerifier implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // Pega o objeto que disparou o evento
        JComponent componente = (JComponent) e.getSource();

        // Método da própria classe InputVerifier que chama a função que para validar os campos
        // Se este método retornar false, o foco permanece no campo que não foi validado
        shouldYieldFocus(componente);
    }

    @Override
    public boolean verify(JComponent input) {
        // Aqui vai verificar se os campos estão OK
        boolean inputOK = validaCampo(input);
    
        // Pinta o campo caso não estiver de acordo
        ColoreCampos.pintarCampo(input, !inputOK);
        
        if (!inputOK){
            JOptionPane.showMessageDialog(null, retornaMensagem(input), "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
        
        return inputOK;
    }
    
    private boolean validaCampo(JComponent input){
        
        boolean campoOK = false;
        
        if (input instanceof JTextField) {
            JTextField txt = (JTextField) input;
            campoOK = !(txt.getText().trim().isEmpty());
        } else {
            campoOK = true;
        }
        
        return campoOK;
    }
    
    private String retornaMensagem(JComponent input){
        
        String mensagem = "";
        
        if (input instanceof JTextField) {
            mensagem = "Preencha o campo corretamente";
        } else {
            mensagem = "";
        }
        
        return mensagem;
    }
}

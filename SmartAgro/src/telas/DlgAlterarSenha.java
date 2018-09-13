/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.Criptografia;
import apoio.LimitaNroCaracteres;
import dao.ColaboradorDAO;
import entidade.Colaborador;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;


public class DlgAlterarSenha extends javax.swing.JDialog {

    private Colaborador colab;
    private ColaboradorDAO dao;;
    private ArrayList<String> errosCampos; 
    public DlgAlterarSenha(java.awt.Frame parent, boolean modal, Colaborador colab) {
        super(parent, modal);
        this.colab = colab;
        dao = new ColaboradorDAO();
        initComponents();   
        jpfSenhaAtual.setDocument(new LimitaNroCaracteres(15));
        jpfSenha.setDocument(new LimitaNroCaracteres(15));
        jpfSenhaConfirmada.setDocument(new LimitaNroCaracteres(15));
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jpfSenha = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jpfSenhaConfirmada = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jpfSenhaAtual = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar senha de usuário");
        setResizable(false);

        jToggleButton1.setText("Alterar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText(" Nova senha *");

        jpfSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpfSenhaMouseClicked(evt);
            }
        });

        jLabel2.setText("Confirme a senha *");

        jpfSenhaConfirmada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpfSenhaConfirmadaMouseClicked(evt);
            }
        });

        jLabel4.setText("Senha atual *");

        jpfSenhaAtual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpfSenhaAtualMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(244, 15, 15));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 16));
        jLabel5.setText("* A senha precisa conter no mínimo 6 caracteres e no máximo 15");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jpfSenhaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jpfSenhaConfirmada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpfSenhaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpfSenhaConfirmada, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (validaCampos().size() < 1) {
            
             String senhaAtual = (String.copyValueOf(jpfSenhaAtual.getPassword()));
             String senhaNova =  (String.copyValueOf(jpfSenha.getPassword())); 
            
             if(!dao.consultarSenha(colab.getUsuario(), Criptografia.criptografar(senhaAtual))){
                jLabel3.setText("Senha atual incorreta"); 
             }     
             else if(!Arrays.equals(jpfSenha.getPassword(), jpfSenhaConfirmada.getPassword())){
                 
                jLabel3.setText("Senhas novas não são iguais"); 
             
             }else if(senhaNova.length() < 6) {
                    jLabel3.setText("Senha precisa ter mais que 6 caracteres.");

             }  
                
             else{
                 colab.setSenhausuario(Criptografia.criptografar(senhaNova));
                    if (dao.atualizar(colab)) {
                        JOptionPane.showMessageDialog(rootPane, "Senha do usuário " + colab.getUsuario() + " atualizada com sucesso.", "Confirmação de edição", JOptionPane.PLAIN_MESSAGE);
                        FrmPrincipal.usuario = colab;
                        jLabel3.setText("");
                        jpfSenhaAtual.setText("");
                        jpfSenha.setText("");
                        jpfSenhaConfirmada.setText("");
                    }
             }
            
        } else {

            String mensagem = "";
            for (int x = 0; x < errosCampos.size(); x++) {
                mensagem = mensagem + "" + errosCampos.get(x) + "\n";
            }

            JOptionPane.showMessageDialog(rootPane,
                    "Antes de alterar, veririque:\n" + mensagem, "Verifique campos", 1);

        }


    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jpfSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpfSenhaMouseClicked
        jLabel3.setText(""); 
    }//GEN-LAST:event_jpfSenhaMouseClicked
    public ArrayList<String> validaCampos() {
        errosCampos = new ArrayList();
        
        
        if(jpfSenhaAtual.getText().isEmpty()){ 
            errosCampos.add("Senha atual: Campo obrigatório;");    
        }
        
        if (jpfSenha.getText().isEmpty()) {

            errosCampos.add("Nova senha: Campo obrigatório;");

        }

        if (jpfSenhaConfirmada.getText().isEmpty()) {

            errosCampos.add("Confirme a senha: Campo obrigatório;");

        }

        return errosCampos;
    }
    private void jpfSenhaConfirmadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpfSenhaConfirmadaMouseClicked
        jLabel3.setText(""); 
    }//GEN-LAST:event_jpfSenhaConfirmadaMouseClicked

    private void jpfSenhaAtualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpfSenhaAtualMouseClicked
        jLabel3.setText(""); 
    }//GEN-LAST:event_jpfSenhaAtualMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DlgAlterarSenha.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgAlterarSenha.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgAlterarSenha.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgAlterarSenha.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPasswordField jpfSenha;
    private javax.swing.JPasswordField jpfSenhaAtual;
    private javax.swing.JPasswordField jpfSenhaConfirmada;
    // End of variables declaration//GEN-END:variables
}

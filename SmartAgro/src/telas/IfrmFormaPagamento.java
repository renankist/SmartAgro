/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.HabilitaCampos;
import apoio.LimpaCampos;
import apoio.VerificadorCampos;
import dao.GenericDAO;
import dao.GenericDAO;
import entidade.Formapagamento;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Morgana
 */
public class IfrmFormaPagamento extends javax.swing.JInternalFrame {

    private Formapagamento forma;
    private GenericDAO<Formapagamento> dao;
    private ArrayList<Formapagamento> formas;
    private boolean editando = false;

    public IfrmFormaPagamento() {

        initComponents();
        //Deixar o focus no campo de descrição
        focus();

    }

    private void focus() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tfdDescricao.requestFocusInWindow();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        tabAbas = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        tfdDescricao = new javax.swing.JTextField();
        lblDescricao = new javax.swing.JLabel();
        pnlConsulta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFormasPagamento = new javax.swing.JTable();
        lblDescricaoConsulta = new javax.swing.JLabel();
        tfdDescricaoConsulta = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Formas de Pagamento");

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        tabAbas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabAbasStateChanged(evt);
            }
        });
        tabAbas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabAbasFocusLost(evt);
            }
        });

        pnlCadastro.setName("pnlCadastro"); // NOI18N

        lblDescricao.setText("Descrição *");

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfdDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(441, Short.MAX_VALUE))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(tfdDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(234, Short.MAX_VALUE))
        );

        tabAbas.addTab("Cadastro", pnlCadastro);

        pnlConsulta.setName("pnlConsulta"); // NOI18N

        jTableFormasPagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableFormasPagamento);

        lblDescricaoConsulta.setText("Descrição:");

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlConsultaLayout = new javax.swing.GroupLayout(pnlConsulta);
        pnlConsulta.setLayout(pnlConsultaLayout);
        pnlConsultaLayout.setHorizontalGroup(
            pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultaLayout.createSequentialGroup()
                .addGroup(pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConsultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDescricaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdDescricaoConsulta)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlConsultaLayout.setVerticalGroup(
            pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricaoConsulta)
                    .addComponent(tfdDescricaoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabAbas.addTab("Consulta", pnlConsulta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabAbas)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(tabAbas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        //Pega o valor da primeira coluna da linha selecionada na tabela de serviços e atribuiu a uma variável do tipo inteiro
        int id = Integer.parseInt(jTableFormasPagamento.getValueAt(jTableFormasPagamento.getSelectedRow(), 0).toString());
        //Busca um servico com o codigo/id pego anteriomente
        forma = dao.consultarPorId(id, "Formapagamento");
        //Defini o editando como true(o registro está sendo editado...)
        
        LimpaCampos.limparCampos(pnlCadastro);

        if (forma != null) { //Se o objeto buscado no método do ServidoDao for diferente de null
            tfdDescricao.setText(forma.getDescricao());//Seta no campo Descrição do formulário de serviços o valor da Descrição do obejto do tipo Servico
            tabAbas.setSelectedIndex(0);//Passa da tela de "Consulta" para a "Manutenção"
            tfdDescricao.requestFocus();//Poem o cursor no campo Descriçã
            editando = true;
        }


    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Aplica o validador
        JComponent[] components = new JComponent[]{tfdDescricao};
        VerificadorCampos verifier = new VerificadorCampos(components);
        
        if (!verifier.validaCampos()) {
            return;
        }

        this.dao = new GenericDAO<>();

        //Modo edição
        if (editando) {
            forma.setDescricao(tfdDescricao.getText());
            if (this.dao.atualizar(forma)) {
                JOptionPane.showMessageDialog(rootPane, "Forma de pagamento " + forma.getDescricao() + " atualizada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Problema para atualizar forma de pagamento.", "Problemas", JOptionPane.ERROR_MESSAGE);
            }
            editando = false;
            
            //Modo inserção
        } else {
            this.forma = new Formapagamento();
            forma.setDescricao(tfdDescricao.getText());
            if (this.dao.salvar(forma)) {
                JOptionPane.showMessageDialog(rootPane, "Forma de pagamento " + forma.getDescricao() + " inserida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Problema para inserir forma de pagamento.", "Problemas", JOptionPane.ERROR_MESSAGE);
            }

        }

        LimpaCampos.limparCampos(pnlCadastro);
        
        focus();

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        //Pega //Pega o valor da primeira coluna da linha selecionada na tabela de serviços e atribuiu a uma variável do tipo inteiro
        int id = Integer.parseInt(jTableFormasPagamento.getValueAt(jTableFormasPagamento.getSelectedRow(), 0).toString());
        forma = dao.consultarPorId(id, "Formapagamento");

        //Abre uma mensagem pedindo se o usuário realmente quer excluír o registro
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a forma de pagamento: " + forma.getDescricao() + " ?", "Atenção", JOptionPane.YES_NO_OPTION);
        //Se a respota for sim
        if (resposta == JOptionPane.YES_OPTION) {

            if (dao.deletar(forma)) {//Chamado o método do ServicoDao de excluir um registro, e caso o retorno é true, retorna uma mensagem de sucesso
                JOptionPane.showMessageDialog(rootPane, "Forma de pagamento excluída", "Confirmação de exclusão", JOptionPane.PLAIN_MESSAGE);
                this.formas = dao.consultarComCriterio("Formapagamento", "descricao", tfdDescricaoConsulta.getText());
                this.jTableFormasPagamento.setModel(new jtmFormasPagamento(formas));
                //this.tblServicos.setDefaultRenderer(Object.class, new RenderizadorTabelas());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Problema para excluir forma de pagamento", "Problema", JOptionPane.PLAIN_MESSAGE);
            }
        }
    

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        this.dao = new GenericDAO<>();

        this.formas = new ArrayList();

        this.formas = dao.consultarComCriterio("Formapagamento", "descricao", tfdDescricaoConsulta.getText());

        this.jTableFormasPagamento.setModel(new jtmFormasPagamento(formas));

        //this.jTableFormasPagamento.setDefaultRenderer(Object.class, new RenderizadorTabelas());

    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void tabAbasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabAbasFocusLost
        HabilitaCampos.controlaPainelCadastro(evt, editando);
    }//GEN-LAST:event_tabAbasFocusLost

    private void tabAbasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabAbasStateChanged
        HabilitaCampos.controlaBotoes(evt, btnSalvar, btnEditar, btnExcluir);
    }//GEN-LAST:event_tabAbasStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableFormasPagamento;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDescricaoConsulta;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlConsulta;
    private javax.swing.JTabbedPane tabAbas;
    private javax.swing.JTextField tfdDescricao;
    private javax.swing.JTextField tfdDescricaoConsulta;
    // End of variables declaration//GEN-END:variables
}

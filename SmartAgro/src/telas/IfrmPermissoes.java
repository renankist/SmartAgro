/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.*;
import dao.GenericDAO;
import entidade.Colaborador;
import entidade.Modulo;
import entidade.Operacao;
import entidade.Operacoesmodulo;
import entidade.Permissaoacesso;
import entidade.PermissaoacessoPK;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Morgana
 */
public class IfrmPermissoes extends javax.swing.JInternalFrame {

    private GenericDAO<Colaborador> dao;
    Colaborador usuario;
    private Permissaoacesso permissao;
    private ArrayList<Permissaoacesso> permissoes;
    private jtmEstruturaPermissoes modelPermissoes;

    private DlgColaboradores dlgColaboradores;

    private boolean editando = false;

    /**
     * Creates new form IfrmUnidadeMedida
     */
    public IfrmPermissoes() {
        initComponents();

        // Preenche a tabela de consulta com as colunas corretas
        permissoes = new ArrayList();
        tblPermissoes.setModel(new jtmPermissoes(permissoes));

        dlgColaboradores = new DlgColaboradores(null, true);

        modelPermissoes = new jtmEstruturaPermissoes();
        montaTreeOperacoes(null);

        //Deixar o focus no campo de descrição
        focus();
    }

    private void focus() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    private void montaTreeOperacoes(ArrayList<Permissaoacesso> permissoesUsuario) {

        ArrayList<Modulo> modulos = new GenericDAO<Modulo>().consultarTodos("Modulo");

        Object[][] tree = new Object[modulos.size()][50];

        int contModulo = 0;
        int contOperacao = 0;

        for (Modulo m : modulos) {

            tree[contModulo][contOperacao] = retornaPermissaoTree(m, null);
            contOperacao++;

            Permissaoacesso objeto = new Permissaoacesso();
            objeto.setAcesso(false);

            // Carrega as operações do modulo
            for (Operacao o : m.getOperacoesCollection()) {

                Permissaoacesso pGenerica = retornaPermissaoTree(m, o);

                // Se o usuário já tem permissoes, atualiza o valor dos mesmos
                if (permissoesUsuario != null) {
                    for (Permissaoacesso p : permissoesUsuario) {
                        if (p.getOperacoesmodulo().getModulo().getId() == pGenerica.getOperacoesmodulo().getModulo().getId()
                                && p.getOperacoesmodulo().getOperacao().getId() == pGenerica.getOperacoesmodulo().getOperacao().getId()) {
                            pGenerica = p;
                        }
                    }
                }

                tree[contModulo][contOperacao] = pGenerica;
                contOperacao++;
            }

            // Reseta os contadores
            contModulo++;
            contOperacao = 0;

        }

        modelPermissoes.criaTabela(jtrPermissoes, tree);
    }

    private Permissaoacesso retornaPermissaoTree(Modulo m, Operacao o) {

        Permissaoacesso objeto = new Permissaoacesso();
        objeto.setAcesso(false);

        Operacoesmodulo om = new Operacoesmodulo();
        om.setModulo(m);
        om.setOperacao(o);
        objeto.setOperacoesmodulo(om);

        PermissaoacessoPK pk = new PermissaoacessoPK();
        pk.setOperacao(om);
        pk.setUsuario(null);
        objeto.setPermissaoacessoPK(pk);

        return objeto;
    }

    private boolean getEditando() {
        return editando;
    }

    private void setEditando(boolean editando) {
        this.editando = editando;
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
        tabAbas = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        lblUnidade = new javax.swing.JLabel();
        tfdUsuario = new javax.swing.JTextField();
        btnZoomVendedor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtrPermissoes = new org.jdesktop.swingx.JXTreeTable();
        pnlConsulta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPermissoes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfdCriterio = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Permissões do Sistema");

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

        lblUnidade.setText("Usuário *");

        tfdUsuario.setEditable(false);

        btnZoomVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/zoom.png"))); // NOI18N
        btnZoomVendedor.setToolTipText("Pesquisar");
        btnZoomVendedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnZoomVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomVendedorActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jtrPermissoes);

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addComponent(lblUnidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnZoomVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 407, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnZoomVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabAbas.addTab("Cadastro", pnlCadastro);

        pnlConsulta.setName("pnlConsulta"); // NOI18N

        tblPermissoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(tblPermissoes);

        jLabel1.setText("Usuário:");

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
                .addContainerGap()
                .addGroup(pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlConsultaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlConsultaLayout.setVerticalGroup(
            pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfdCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))
        );

        tabAbas.addTab("Consulta", pnlConsulta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addContainerGap())
            .addComponent(tabAbas)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(tabAbas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnEditar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // Pega o model da tabela para pegar o objeto
        jtmPermissoes model = (jtmPermissoes) tblPermissoes.getModel();

        permissao = model.get(tblPermissoes.getSelectedRow());

        LimpaCampos.limparCampos(pnlCadastro);

        if (permissao != null) {
            usuario = new GenericDAO<Colaborador>().consultarPorId(permissao.getPermissaoacessoPK().getUsuario().getId(), "Colaborador");
            tfdUsuario.setText(usuario.getNomecompleto());

            permissoes = new ArrayList<Permissaoacesso>(usuario.getPermissoesCollection());
            montaTreeOperacoes(permissoes);

            tabAbas.setSelectedIndex(0);
            setEditando(true);
            focus();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Aplica o validador
        JComponent[] components = new JComponent[]{tfdUsuario};
        VerificadorCampos verifier = new VerificadorCampos(components);
        if (!verifier.validaCampos()) {
            return;
        }

        ArrayList<Permissaoacesso> ps = modelPermissoes.getPermissoes();

        for (int i = 0; i < ps.size(); i++) {
            PermissaoacessoPK pk = ps.get(i).getPermissaoacessoPK();
            pk.setUsuario(usuario);
            ps.get(i).setPermissaoacessoPK(pk);
            ps.get(i).setUsuario(usuario);
        }
        
        usuario.setPermissoesCollection(ps);

        dao = new GenericDAO();
        
        if (dao.atualizar(usuario)) {
            Mensagem.mostraInformacao("Sucesso", "Permissões do usuário atualizadas com sucesso");
            LimpaCampos.limparCampos(pnlCadastro);
        } else {
            Mensagem.mostraErro("Problema", "Problema ao atualizar as permissões do usuário");
        }

        focus();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        this.permissoes = new ArrayList();
        this.permissoes = new GenericDAO<Permissaoacesso>().consultarTodos("Permissaoacesso");

        tblPermissoes.setModel(new jtmPermissoes(permissoes));
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void tabAbasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabAbasFocusLost
        HabilitaCampos.controlaPainelCadastro(evt, editando);
    }//GEN-LAST:event_tabAbasFocusLost

    private void tabAbasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabAbasStateChanged
    }//GEN-LAST:event_tabAbasStateChanged

    private void btnZoomVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomVendedorActionPerformed
        dlgColaboradores.setVisible(true);
        if (dlgColaboradores.getColaborador() != null) {
            usuario = dlgColaboradores.getColaborador();
            tfdUsuario.setText(dlgColaboradores.getColaboradorToString());
        }
    }//GEN-LAST:event_btnZoomVendedorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnZoomVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXTreeTable jtrPermissoes;
    private javax.swing.JLabel lblUnidade;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlConsulta;
    private javax.swing.JTabbedPane tabAbas;
    private javax.swing.JTable tblPermissoes;
    private javax.swing.JTextField tfdCriterio;
    private javax.swing.JTextField tfdUsuario;
    // End of variables declaration//GEN-END:variables
}

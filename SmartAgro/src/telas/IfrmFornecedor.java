/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.*;
import dao.GenericDAO;
import entidade.Fornecedor;
import entidade.Estado;
import entidade.Cidade;
import entidade.Endereco;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Morgana
 */
public class IfrmFornecedor extends javax.swing.JInternalFrame {

    private Fornecedor fornecedor;
    private Endereco endereco;
    private GenericDAO<Fornecedor> dao;
    private ArrayList<Fornecedor> fornecedores;
    private ArrayList<Estado> ufs;
    private boolean editando = false;

    /**
     * Creates new form IfrmUnidadeMedida
     */
    public IfrmFornecedor(int aba) {
        initComponents();

        // Abre na aba passada por parametro
        tabAbas.setSelectedIndex(aba);

        // Preenche a tabela de consulta com as colunas corretas
        fornecedores = new ArrayList();
        tblFornecedores.setModel(new jtmFornecedor(fornecedores));

        // Preenche o combo
        popularCombos();

        //Deixar o focus no campo de descrição
        focus();
    }

    private void popularCombos() {
        // Popula combo UF
        GenericDAO<Estado> ufdao = new GenericDAO<>();
        this.ufs = new ArrayList();
        this.ufs = ufdao.consultarTodos("Estado");
        cmbUF.setModel(new EstadoComboModel(this.ufs));
    }

    private void focus() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                rbtJuridica.requestFocusInWindow();
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

        btgPessoa = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        tabAbas = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        rbtFisica = new javax.swing.JRadioButton();
        rbtJuridica = new javax.swing.JRadioButton();
        pnlGeral = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfdNome = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        tfdCodigo = new javax.swing.JTextField();
        tfdRazaoSocial = new javax.swing.JTextField();
        lblNome1 = new javax.swing.JLabel();
        tfdCNPJ = new javax.swing.JTextField();
        pnlEndereco = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tfdLogradouro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfdNumero = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfdBairro = new javax.swing.JTextField();
        ffdCEP = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        tfdCidade = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfdComplemento = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cmbUF = new javax.swing.JComboBox();
        btnZoom = new javax.swing.JButton();
        pnlConsulta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFornecedores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfdCriterio = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Fornecedores");

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

        btgPessoa.add(rbtFisica);
        rbtFisica.setText("Pessoa Física");
        rbtFisica.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtFisicaStateChanged(evt);
            }
        });

        btgPessoa.add(rbtJuridica);
        rbtJuridica.setText("Pessoa Jurídica");
        rbtJuridica.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtJuridicaStateChanged(evt);
            }
        });

        pnlGeral.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblNome.setText("Nome *");

        jLabel2.setText("Razão social");

        jLabel25.setText("Código");

        tfdCodigo.setEditable(false);
        tfdCodigo.setFocusable(false);

        lblNome1.setText("CPF/CNPJ");

        javax.swing.GroupLayout pnlGeralLayout = new javax.swing.GroupLayout(pnlGeral);
        pnlGeral.setLayout(pnlGeralLayout);
        pnlGeralLayout.setHorizontalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNome)
                    .addComponent(jLabel2)
                    .addComponent(jLabel25)
                    .addComponent(lblNome1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdNome)
                    .addComponent(tfdRazaoSocial)
                    .addGroup(pnlGeralLayout.createSequentialGroup()
                        .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlGeralLayout.setVerticalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(tfdCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome1)
                    .addComponent(tfdCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(tfdNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfdRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel10.setText("Logradouro *");

        jLabel11.setText("Número");

        jLabel12.setText("Bairro *");

        jLabel15.setText("CEP *");

        try {
            ffdCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel13.setText("Cidade *");

        tfdCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfdCidadeFocusLost(evt);
            }
        });

        jLabel14.setText("Complemento");

        jLabel16.setText("UF *");

        btnZoom.setToolTipText("Pesquisar");
        btnZoom.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnZoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEnderecoLayout = new javax.swing.GroupLayout(pnlEndereco);
        pnlEndereco.setLayout(pnlEnderecoLayout);
        pnlEnderecoLayout.setHorizontalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEnderecoLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(pnlEnderecoLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(37, 37, 37)))
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(32, 32, 32)))
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addComponent(tfdCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfdBairro, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfdLogradouro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addComponent(cmbUF, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ffdCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfdComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        pnlEnderecoLayout.setVerticalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tfdNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(tfdComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(cmbUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(ffdCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tfdLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(tfdBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfdCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addComponent(btnZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addComponent(rbtFisica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtJuridica)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtFisica)
                    .addComponent(rbtJuridica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabAbas.addTab("Cadastro", pnlCadastro);

        tblFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(tblFornecedores);

        jLabel1.setText("Nome:");

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
            .addGroup(pnlConsultaLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdCriterio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisar))
        );
        pnlConsultaLayout.setVerticalGroup(
            pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultaLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfdCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
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
                .addComponent(tabAbas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // Pega o código do registro para consultar o objeto
        int id = Integer.parseInt(tblFornecedores.getValueAt(tblFornecedores.getSelectedRow(), 0).toString());
        this.fornecedor = dao.consultarPorId(id, "Fornecedor");

        // Pega os dados se existir objeto
        if (this.fornecedor != null) {
            if (this.fornecedor.getCnpj() != null) {
                tfdCNPJ.setText(this.fornecedor.getCnpj());
            } else {
                tfdCNPJ.setText(this.fornecedor.getCpf());
            }
            tfdCodigo.setText(String.valueOf(this.fornecedor.getId()));
            tfdNome.setText(this.fornecedor.getNome());
            tfdRazaoSocial.setText(this.fornecedor.getRazaosocial());
            tfdLogradouro.setText(this.fornecedor.getEndereco().getRua());
            tfdNumero.setText(this.fornecedor.getEndereco().getNumero());
            tfdBairro.setText(this.fornecedor.getEndereco().getBairro());
            tfdComplemento.setText(this.fornecedor.getEndereco().getComplemento());
            tfdCidade.setText(this.fornecedor.getEndereco().getCidade().getNome());
            ffdCEP.setText(this.fornecedor.getEndereco().getCep());
            cmbUF.setSelectedItem(this.fornecedor.getEndereco().getCidade().getEstado());
            tabAbas.setSelectedIndex(0);
            editando = true;
            focus();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        JComponent[] components = new JComponent[]{tfdNome, tfdLogradouro, tfdBairro, tfdCidade, cmbUF, ffdCEP};
        VerificadorCampos verifier = new VerificadorCampos(components);
        if (!verifier.validaCampos()) {
            return;
        }

        this.dao = new GenericDAO();

        if (editando) {
            endereco.setRua(tfdLogradouro.getText());
            endereco.setNumero(tfdNumero.getText());
            endereco.setBairro(tfdBairro.getText());
            endereco.setComplemento(tfdComplemento.getText());
            endereco.setCep(ffdCEP.getText());
            ArrayList<Cidade> cidades = new GenericDAO<Cidade>().consultarComCriterioIgualA("Cidade", "nome", tfdCidade.getText());
            for (Cidade cidade : cidades) {
                if (cidade.getEstado().getId() == ((Estado) cmbUF.getSelectedItem()).getId()) {
                    endereco.setCidade(cidade);
                    break;
                }
            }

            fornecedor.setEndereco(endereco);
            if (rbtFisica.isSelected()) {
                fornecedor.setCpf(tfdCNPJ.getText());
            } else {
                fornecedor.setCnpj(tfdCNPJ.getText());
            }
            fornecedor.setNome(tfdNome.getText());
            fornecedor.setRazaosocial(tfdRazaoSocial.getText());
       

            try {
                if (!new GenericDAO<>().atualizar(endereco)) {
                    throw new Exception("Erro ao atualizar endereco - fornecedor");
                }

                if (!dao.atualizar(fornecedor)) {
                    throw new Exception("Erro ao atualizar fornecedor");
                }

                Mensagem.mostraInformacao("Sucesso", "Fornecedor " + fornecedor.getNome() + " atualizado com sucesso");

            } catch (Exception e) {
                Mensagem.mostraInformacao("Problema", "Problema ao atualizar fornecedor");
            }
            editando = false;
        } else {
            fornecedor = new Fornecedor();
            endereco = new Endereco();

            endereco.setRua(tfdLogradouro.getText());
            endereco.setNumero(tfdNumero.getText());
            endereco.setBairro(tfdBairro.getText());
            endereco.setComplemento(tfdComplemento.getText());
            endereco.setCep(ffdCEP.getText());
            
            ArrayList<Cidade> cidades = new GenericDAO<Cidade>().consultarComCriterioIgualA("Cidade", "nome", tfdCidade.getText());
            for (Cidade cidade : cidades) {
                if (cidade.getEstado().getId() == ((Estado) cmbUF.getSelectedItem()).getId()) {
                    endereco.setCidade(cidade);
                    break;
                }
            }

            fornecedor.setEndereco(endereco);

            if (rbtFisica.isSelected()) {
                fornecedor.setCpf(tfdCNPJ.getText());
            } else {
                fornecedor.setCnpj(tfdCNPJ.getText());
            }

            fornecedor.setNome(tfdNome.getText());
            fornecedor.setRazaosocial(tfdRazaoSocial.getText());
         
            try {
                if (!new GenericDAO<>().salvar(endereco)) {
                    throw new Exception("Erro ao salvar endereco - fornecedor");
                }

                if (!dao.salvar(fornecedor)) {
                    throw new Exception("Erro ao salvar fornecedor");
                }

                Mensagem.mostraInformacao("Sucesso", "Fornecedor " + fornecedor.getNome() + " inserido com sucesso");

            } catch (Exception e) {
                Mensagem.mostraInformacao("Problema", "Problema para inserir fornecedor");
            }
        }

        LimpaCampos.limparCampos(pnlGeral);
        LimpaCampos.limparCampos(pnlEndereco);
        focus();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // Pega o código do registro para consultar o objeto
        int id = Integer.parseInt(tblFornecedores.getValueAt(tblFornecedores.getSelectedRow(), 0).toString());

        this.fornecedor = dao.consultarPorId(id, "Fornecedor");
        this.endereco = this.fornecedor.getEndereco();

        //Abre uma mensagem pedindo se o usuário realmente quer excluír o registro
        boolean resposta = Mensagem.confirmaMensagem("Atenção", "Deseja realmente excluir o fornecedor: " + this.fornecedor.getNome() + "?");

        if (resposta) {
            // Exclui o registro
            if (dao.deletar(fornecedor)) {
                Mensagem.mostraInformacao("Confirmação de exclusão", "Fornecedor excluído");

                this.fornecedores = dao.consultarComCriterio("Fornecedor", "nome", tfdCriterio.getText());
                this.tblFornecedores.setModel(new jtmFornecedor(this.fornecedores));
            } else {
                Mensagem.mostraErro("Problema", "Problema para excluir fornecedor");
            }

        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        this.dao = new GenericDAO<>();
        this.fornecedores = new ArrayList();

        this.fornecedores = dao.consultarComCriterio("Fornecedor", "nome", tfdCriterio.getText());
        tblFornecedores.setModel(new jtmFornecedor(fornecedores));
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnZoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomActionPerformed
        //        Estado uf = (Estado) cmbUF.getSelectedItem();
        //        DlgCidades dlgCidades = new DlgCidades(null, true, uf.getIdEstado());
        //        dlgCidades.setVisible(true);
        //        if (dlgCidades.seleciou() && dlgCidades.getCidade() != null) {
        //            tfdCidade.setText(dlgCidades.getCidade());
        //        }
    }//GEN-LAST:event_btnZoomActionPerformed

    private void tfdCidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfdCidadeFocusLost
        //        if (tfdCidade.getText().trim().isEmpty()) {
        //            return;
        //        }
        //
        //        Object validou = new CidadeDAO().consultarNome(tfdCidade.getText().trim(), (Estado) cmbUF.getSelectedItem());
        //
        //        if (validou == null) {
        //            JOptionPane.showMessageDialog(this, "Cidade não cadastrada");
        //            tfdCidade.requestFocus();
        //        }
    }//GEN-LAST:event_tfdCidadeFocusLost

    private void rbtJuridicaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtJuridicaStateChanged

    }//GEN-LAST:event_rbtJuridicaStateChanged

    private void rbtFisicaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtFisicaStateChanged

    }//GEN-LAST:event_rbtFisicaStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgPessoa;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnZoom;
    private javax.swing.JComboBox cmbUF;
    private javax.swing.JFormattedTextField ffdCEP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlConsulta;
    private javax.swing.JPanel pnlEndereco;
    private javax.swing.JPanel pnlGeral;
    private javax.swing.JRadioButton rbtFisica;
    private javax.swing.JRadioButton rbtJuridica;
    private javax.swing.JTabbedPane tabAbas;
    private javax.swing.JTable tblFornecedores;
    private javax.swing.JTextField tfdBairro;
    private javax.swing.JTextField tfdCNPJ;
    private javax.swing.JTextField tfdCidade;
    private javax.swing.JTextField tfdCodigo;
    private javax.swing.JTextField tfdComplemento;
    private javax.swing.JTextField tfdCriterio;
    private javax.swing.JTextField tfdLogradouro;
    private javax.swing.JTextField tfdNome;
    private javax.swing.JTextField tfdNumero;
    private javax.swing.JTextField tfdRazaoSocial;
    // End of variables declaration//GEN-END:variables
}

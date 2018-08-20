/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.*;
import dao.GenericDAO;
import entidade.Colaborador;
import entidade.Estado;
import entidade.Endereco;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Morgana
 */
public class IfrmColaborador extends javax.swing.JInternalFrame {

    private Colaborador colab;
    private Endereco endereco;
    private GenericDAO<Colaborador> dao;
    private ArrayList<Colaborador> colabs;
    private ArrayList<Estado> ufs;
    private DlgCidades dlgCidades;
    private boolean editando = false;

    /**
     * Creates new form IfrmUnidadeMedida
     */
    public IfrmColaborador(int aba) {
        initComponents();
       
        dlgCidades = new DlgCidades(null, true);
        // Abre na aba passada por parametro
        tabAbas.setSelectedIndex(aba);

        // Preenche a tabela de consulta com as colunas corretas
        colabs = new ArrayList();
        // tblFornecedores.setModel(new jtmFornecedor(fornecedores));

        //Deixar o focus no campo de descrição
        focus();
    }

    private void focus() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                tfdNome.requestFocusInWindow();
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
        pnlGeral = new javax.swing.JPanel();
        lblFuncao = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        tfdFuncao = new javax.swing.JTextField();
        tfdUsuario = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        lblTipoUsuario = new javax.swing.JLabel();
        rbtOperador = new javax.swing.JRadioButton();
        rbtAdministrador = new javax.swing.JRadioButton();
        tfdNome = new javax.swing.JTextField();
        pnlEndereco = new javax.swing.JPanel();
        lblLogradouro = new javax.swing.JLabel();
        tfdLogradouro = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        tfdNumero = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        lblCep = new javax.swing.JLabel();
        tfdBairro = new javax.swing.JTextField();
        ffdCEP = new javax.swing.JFormattedTextField();
        lblCidade = new javax.swing.JLabel();
        tfdCidade = new javax.swing.JTextField();
        lblComplemento = new javax.swing.JLabel();
        tfdComplemento = new javax.swing.JTextField();
        btnZoom = new javax.swing.JButton();
        pnlContato = new javax.swing.JPanel();
        lblCelular = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        tfdEmail = new javax.swing.JTextField();
        ffdCelular = new javax.swing.JFormattedTextField();
        pnlConsulta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblColaboradores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfdCriterio = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Colaboradores");

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

        pnlGeral.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados gerais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblFuncao.setText("Função *");

        lblUsuario.setText("Usuário *");

        lblNome.setText("Nome *");

        lblTipoUsuario.setText("Tipo Usuário * ");

        btgPessoa.add(rbtOperador);
        rbtOperador.setText("Operador");
        rbtOperador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtOperadorItemStateChanged(evt);
            }
        });
        rbtOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOperadorActionPerformed(evt);
            }
        });

        btgPessoa.add(rbtAdministrador);
        rbtAdministrador.setText("Administrador");
        rbtAdministrador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtAdministradorItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlGeralLayout = new javax.swing.GroupLayout(pnlGeral);
        pnlGeral.setLayout(pnlGeralLayout);
        pnlGeralLayout.setHorizontalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGeralLayout.createSequentialGroup()
                        .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFuncao)
                            .addComponent(lblUsuario)
                            .addComponent(lblNome))
                        .addGap(42, 42, 42)
                        .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfdNome, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                            .addComponent(tfdFuncao, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                            .addComponent(tfdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlGeralLayout.createSequentialGroup()
                        .addComponent(lblTipoUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtOperador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtAdministrador)))
                .addContainerGap(559, Short.MAX_VALUE))
        );
        pnlGeralLayout.setVerticalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(tfdNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFuncao)
                    .addComponent(tfdFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(tfdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOperador)
                    .addComponent(rbtAdministrador)
                    .addComponent(lblTipoUsuario))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblLogradouro.setText("Logradouro *");

        lblNumero.setText("Número");

        lblBairro.setText("Bairro *");

        lblCep.setText("CEP *");

        try {
            ffdCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblCidade.setText("Cidade *");

        tfdCidade.setEditable(false);

        lblComplemento.setText("Complemento");

        btnZoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/zoom.png"))); // NOI18N
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
                            .addComponent(lblLogradouro)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(pnlEnderecoLayout.createSequentialGroup()
                            .addComponent(lblBairro)
                            .addGap(37, 37, 37)))
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addComponent(lblCidade)
                        .addGap(32, 32, 32)))
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addComponent(tfdCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfdBairro, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfdLogradouro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblNumero)
                        .addComponent(lblComplemento))
                    .addComponent(lblCep, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ffdCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        pnlEnderecoLayout.setVerticalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumero)
                            .addComponent(tfdNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblComplemento)
                            .addComponent(tfdComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCep)
                            .addComponent(ffdCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLogradouro)
                            .addComponent(tfdLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBairro)
                            .addComponent(tfdBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfdCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCidade))
                            .addComponent(btnZoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pnlContato.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblCelular.setText("Celular *");

        lblEmail.setText("E-mail *");

        tfdEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdEmailActionPerformed(evt);
            }
        });

        try {
            ffdCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pnlContatoLayout = new javax.swing.GroupLayout(pnlContato);
        pnlContato.setLayout(pnlContatoLayout);
        pnlContatoLayout.setHorizontalGroup(
            pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addComponent(lblCelular))
                .addGap(44, 44, 44)
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ffdCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlContatoLayout.setVerticalGroup(
            pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContatoLayout.createSequentialGroup()
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCelular)
                    .addComponent(ffdCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(tfdEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlContato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tabAbas.addTab("Cadastro", pnlCadastro);

        pnlConsulta.setName("pnlConsulta"); // NOI18N

        tblColaboradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Celular", "E-mail", "Função", "Usuário", "Tipo Usuário", "Cidade/Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblColaboradores);

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
        );

        tabAbas.addTab("Consulta", pnlConsulta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tabAbas, javax.swing.GroupLayout.PREFERRED_SIZE, 1033, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        // Pega o código do registro para consultar o objeto
        int id = Integer.parseInt(tblColaboradores.getValueAt(tblColaboradores.getSelectedRow(), 0).toString());
        this.colab = dao.consultarPorId(id, "Colaborador");

        this.endereco = this.colab.getEndereco();

        LimpaCampos.limparCampos(pnlGeral);
        LimpaCampos.limparCampos(pnlEndereco);
        LimpaCampos.limparCampos(pnlContato);

        // Pega os dados se existir objeto
        if (this.colab != null) {
            String tmp = this.colab.getTipousuario() + "";
            if (tmp.equals("a")) {
                rbtAdministrador.setSelected(true);

            } else {

                rbtOperador.setSelected(true);
            }
            tfdFuncao.setText(this.colab.getFuncao());
            tfdNome.setText(this.colab.getNomecompleto());
            tfdUsuario.setText(this.colab.getUsuario());
            tfdLogradouro.setText(this.colab.getEndereco().getRua());
            tfdNumero.setText(this.colab.getEndereco().getNumero());
            tfdBairro.setText(this.colab.getEndereco().getBairro());
            tfdComplemento.setText(this.colab.getEndereco().getComplemento());
            tfdCidade.setText(this.colab.getEndereco().getCidade().getNome() + " - " + this.colab.getEndereco().getCidade().getEstado().getSigla());
            ffdCEP.setText(this.colab.getEndereco().getCep());
            tfdEmail.setText(this.colab.getEmail());
            ffdCelular.setText(this.colab.getCelular());
            tabAbas.setSelectedIndex(0);
            editando = true;
            focus();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private boolean validaCampos() {
        ArrayList<JComponent> components = new ArrayList();

        components.add(tfdNome);
        components.add(tfdLogradouro);
        components.add(tfdBairro);
        components.add(tfdCidade);
        components.add(ffdCEP);
        components.add(tfdEmail);
        components.add(ffdCelular);
        components.add(tfdFuncao);
        components.add(tfdUsuario);

        // Verifica se um radio foi selecionado, se não foi valida os dois
        if (btgPessoa.getSelection() == null) {
            components.add(rbtAdministrador);
            components.add(rbtOperador);
        } else {
            ColoreCampos.pintarCampo(rbtOperador, false);
            ColoreCampos.pintarCampo(rbtAdministrador, false);
        }

        JComponent[] simpleArray = new JComponent[components.size()];
        components.toArray(simpleArray);
        VerificadorCampos verifier = new VerificadorCampos(simpleArray);

        if (!verifier.validaCampos()) {
            return false;
        }

        return true;
    }


    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (!validaCampos()) {
            return;
        }

        this.dao = new GenericDAO<>();

        if (!editando) {
            colab = new Colaborador();
            endereco = new Endereco();
        }

        endereco.setRua(tfdLogradouro.getText());
        endereco.setNumero(tfdNumero.getText());
        endereco.setBairro(tfdBairro.getText());
        endereco.setComplemento(tfdComplemento.getText());
        endereco.setCep(ffdCEP.getText());

        if (dlgCidades.getCidade() != null) {

            endereco.setCidade(dlgCidades.getCidade());
        } else {
            endereco.setCidade(this.colab.getEndereco().getCidade());

        }
        
        if(rbtOperador.isSelected()){
            colab.setTipousuario('o');
        }else{
           colab.setTipousuario('a'); 
        }

        colab.setEndereco(endereco);
        colab.setNomecompleto(tfdNome.getText());
        colab.setFuncao(tfdFuncao.getText());
        colab.setEmail(tfdEmail.getText());
        colab.setCelular(ffdCelular.getText());
        colab.setUsuario(tfdUsuario.getText());

        if (editando) {

            try {
                if (!new GenericDAO<>().atualizar(endereco)) {
                    throw new Exception("Erro ao atualizar endereco - colaborador");
                }

                if (!dao.atualizar(colab)) {
                    throw new Exception("Erro ao atualizar colaborador");
                }

                Mensagem.mostraInformacao("Sucesso", "Colaborador " + colab.getNomecompleto() + " atualizado com sucesso");
              
                LimpaCampos.limparCampos(pnlEndereco);
                LimpaCampos.limparCampos(pnlGeral);
                LimpaCampos.limparCampos(pnlContato);
                btgPessoa.clearSelection();
                focus();

            } catch (Exception e) {
                Mensagem.mostraInformacao("Problema", "Problema ao atualizar colaborador");
            }
            editando = false;

        } else {

            colab.setSenhausuario(Criptografia.criptografar("12345"));
            try {
                if (!new GenericDAO<>().salvar(endereco)) {
                    throw new Exception("Erro ao salvar endereco - colaborador");
                }

                if (!dao.salvar(colab)) {
                    throw new Exception("Erro ao salvar colaborador");
                }

                Mensagem.mostraInformacao("Sucesso", "Colaborador " + colab.getNomecompleto() + " inserido com sucesso");
               
                LimpaCampos.limparCampos(pnlEndereco);
                LimpaCampos.limparCampos(pnlGeral);
                LimpaCampos.limparCampos(pnlContato);
                btgPessoa.clearSelection();
                
                focus();
           
            } catch (Exception e) {
                Mensagem.mostraInformacao("Problema", "Problema para inserir colaborador");
            }
        }


    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // Pega o código do registro para consultar o objeto
        int id = Integer.parseInt(tblColaboradores.getValueAt(tblColaboradores.getSelectedRow(), 0).toString());

        this.colab = dao.consultarPorId(id, "Colaborador");
        this.endereco = this.colab.getEndereco();

        //Abre uma mensagem pedindo se o usuário realmente quer excluír o registro
        boolean resposta = Mensagem.confirmaMensagem("Atenção", "Deseja realmente excluir o colaborador: " + this.colab.getNomecompleto() + "?");

        if (resposta) {
            // Exclui o registro
            if (dao.deletar(colab)) {
                Mensagem.mostraInformacao("Confirmação de exclusão", "Colaborador excluído");

                this.colabs = dao.consultarComCriterio("Colaborador", "nomecompleto", tfdCriterio.getText());
                this.tblColaboradores.setModel(new jtmColaborador(this.colabs));
            } else {
                Mensagem.mostraErro("Problema", "Problema para excluir colaborador");
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        this.dao = new GenericDAO<>();
        this.colabs = new ArrayList();

        this.colabs = dao.consultarComCriterio("Colaborador", "nomecompleto", tfdCriterio.getText());
        tblColaboradores.setModel(new jtmColaborador(colabs));
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnZoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomActionPerformed

        dlgCidades.setVisible(true);
        if (dlgCidades.seleciou() && dlgCidades.getCidade() != null) {
            String cidade = dlgCidades.getCidade().getNome() + " - " + dlgCidades.getCidade().getEstado().getSigla();
            tfdCidade.setText(cidade);
        }
    }//GEN-LAST:event_btnZoomActionPerformed

    private void rbtOperadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtOperadorItemStateChanged

    }//GEN-LAST:event_rbtOperadorItemStateChanged

    private void rbtAdministradorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtAdministradorItemStateChanged

    }//GEN-LAST:event_rbtAdministradorItemStateChanged

    private void tabAbasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabAbasStateChanged
        HabilitaCampos.controlaBotoes(evt, btnSalvar, btnEditar, btnExcluir);
    }//GEN-LAST:event_tabAbasStateChanged

    private void tabAbasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabAbasFocusLost
        HabilitaCampos.controlaPainelCadastro(evt, editando);
    }//GEN-LAST:event_tabAbasFocusLost

    private void rbtOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOperadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtOperadorActionPerformed

    private void tfdEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdEmailActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgPessoa;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnZoom;
    private javax.swing.JFormattedTextField ffdCEP;
    private javax.swing.JFormattedTextField ffdCelular;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCep;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblComplemento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFuncao;
    private javax.swing.JLabel lblLogradouro;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlConsulta;
    private javax.swing.JPanel pnlContato;
    private javax.swing.JPanel pnlEndereco;
    private javax.swing.JPanel pnlGeral;
    private javax.swing.JRadioButton rbtAdministrador;
    private javax.swing.JRadioButton rbtOperador;
    private javax.swing.JTabbedPane tabAbas;
    private javax.swing.JTable tblColaboradores;
    private javax.swing.JTextField tfdBairro;
    private javax.swing.JTextField tfdCidade;
    private javax.swing.JTextField tfdComplemento;
    private javax.swing.JTextField tfdCriterio;
    private javax.swing.JTextField tfdEmail;
    private javax.swing.JTextField tfdFuncao;
    private javax.swing.JTextField tfdLogradouro;
    private javax.swing.JTextField tfdNome;
    private javax.swing.JTextField tfdNumero;
    private javax.swing.JTextField tfdUsuario;
    // End of variables declaration//GEN-END:variables
}
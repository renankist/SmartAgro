/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.Client;
import apoio.Formatacao;
import apoio.Mensagem;
import apoio.RSAcriptografia;
import static apoio.RSAcriptografia.LOCAL_CHAVE_PRIVADA;
import static apoio.RSAcriptografia.decriptografa;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import dao.GenericDAO;
import dao.GraficoDAO;
import dao.ReleaseDAO;
import entidade.Config;
import entidade.Licenca;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import relatorios.GeraGraficoBarra;
import relatorios.GeraGraficoLinha;
import relatorios.GeraGraficoPizza;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author Morgana
 */
public class FrmPrincipal extends javax.swing.JFrame {

    private static Config parametros;
    private GenericDAO<Config> dao;
    private GraficoDAO grafdao;
    private static Client c;

    //Graficos
    private GeraGraficoPizza vendasPorVendedor;
    private GeraGraficoPizza valorVendidoPorVendedor;
    private GeraGraficoBarra vendasPorMes;
    private GeraGraficoLinha valorVendidoPorMes;

    public static Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {

        initComponents();
        lbUsuario.setText("Olá, " + jfrLogin.getUsuarioLogado().getNomecompleto());
        dao = new GenericDAO();

        try {
            //Inserindo datas 
            Calendar cal = Calendar.getInstance();
            int mes = cal.get(Calendar.MONTH);
            mes += 1;
            String ultimo = cal.get(Calendar.YEAR) + "/" + mes + "/" + cal.getActualMinimum(Calendar.DAY_OF_MONTH);
            String pattern = "yyyy/MM/dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date d;
            d = format.parse(ultimo);
            this.dchInicio.setDate(d);
            this.dchFim.setDate(new Date());
        } catch (ParseException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        parametros = new Config();
        parametros = dao.consultarPorId(1, "Config");
        try {
            c = new Client("localhost", 5000, jtaNotificacoes);
            c.start();
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        // dashboard();

        /* Abrir a tela maximizada */
        //setExtendedState(MAXIMIZED_BOTH);
        /* Define o icone da aplicação */
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/stack.png")));

        //Caso o usuário for administrador
        if (jfrLogin.getUsuarioLogado().getTipousuario() == 'a') {

            //Gráficos: 
            grafdao = new GraficoDAO();

            vendasPorVendedor = new GeraGraficoPizza("Vendas realizadas - por vendedor");
            valorVendidoPorVendedor = new GeraGraficoPizza("Valor total vendido - por vendedor");
            vendasPorMes = new GeraGraficoBarra("Quantidade de vendas realizadas - por mês", 0);
            vendasPorMes.setDescricaoY("Vendas realizadas");
            vendasPorMes.setDescricaoX("Mês do ano");

            valorVendidoPorMes = new GeraGraficoLinha("Valor total vendido - por mês");
            valorVendidoPorMes.setDescricaoX("Mês");
            valorVendidoPorMes.setDescricaoY("Valor vendido");
            valorVendidoPorMes.setLegenda(false);

            montarGraficos();

        } else {
            alteraStatusDash(false);
            btnAtualizarDash.setVisible(false);
            btnOcultarDash.setVisible(false);
        }

    }

    private void montarGraficos() {

        //Indicador
        labelValorVendas.setText("R$ " + this.grafdao.valorTotalVendidoNoMes(dchInicio.getDate(), dchFim.getDate()) + "");
        labelQuantidadeVendas.setText(this.grafdao.quantidadeDeVendasrealizadas(dchInicio.getDate(), dchFim.getDate()) + "");
        //Vendas por vendedor           
        ArrayList<String> dados = this.grafdao.vendaPorColaborador(dchInicio.getDate(), dchFim.getDate());
        this.vendasPorVendedor.criarGraficoPizza(dados, "Inteiro");
        this.jplVendasPorVendedor.setLayout(new BorderLayout());
        this.jplVendasPorVendedor.add(this.vendasPorVendedor.getPainel());

        //Valor total vendido por vendedor           
        this.valorVendidoPorVendedor.criarGraficoPizza(this.grafdao.valorVendidoPorColaborador(dchInicio.getDate(), dchFim.getDate()), "Decimal");
        this.jplValorVendidoPorVendedor.setLayout(new BorderLayout());
        this.jplValorVendidoPorVendedor.add(this.valorVendidoPorVendedor.getPainel());

        this.vendasPorMes.criarGraficoBarras(this.grafdao.quantidadeDeVendasNoAnoPorMes(dchInicio.getDate(), dchFim.getDate()), "Inteiro");
        jplBarra.setLayout(new BorderLayout());
        jplBarra.add(this.vendasPorMes.getPainel());

        this.valorVendidoPorMes.criaGraficoLinha(this.grafdao.valorTotalVendidoNoAno(dchInicio.getDate(), dchFim.getDate()), "Decimal");
        jplValorVendidoPorMes.setLayout(new BorderLayout());
        jplValorVendidoPorMes.add(valorVendidoPorMes.getPainel());
        valorVendidoPorMes.getGrafico().getPlot().setInsets(RectangleInsets.ZERO_INSETS);
    }

    public static Config getParametros() {
        return parametros;
    }

    public static void setParametros(Config parametros) {
        parametros = parametros;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dskArea = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaNotificacoes = new javax.swing.JTextArea();
        lblNotific = new java.awt.Label();
        JtnLimpar = new javax.swing.JButton();
        jplVendasPorVendedor = new javax.swing.JPanel();
        jplValorVendidoPorVendedor = new javax.swing.JPanel();
        jplBarra = new javax.swing.JPanel();
        jplValorVendidoPorMes = new javax.swing.JPanel();
        pnlPeriodo = new javax.swing.JPanel();
        dchInicio = new com.toedter.calendar.JDateChooser();
        dchFim = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlTotalVendido = new javax.swing.JPanel();
        labelValorVendas = new java.awt.Label();
        pnlQtdVendida = new javax.swing.JPanel();
        labelQuantidadeVendas = new java.awt.Label();
        btnOcultarDash = new javax.swing.JButton();
        lbUsuario = new java.awt.Label();
        btnAtualizarDash = new javax.swing.JButton();
        barMenu = new javax.swing.JMenuBar();
        mnuCliente = new javax.swing.JMenu();
        itmCadastroCliente = new javax.swing.JMenuItem();
        itmConsultaCliente = new javax.swing.JMenuItem();
        itmRelatorioCliente = new javax.swing.JMenuItem();
        mnuProduto = new javax.swing.JMenu();
        itmCadastroProduto = new javax.swing.JMenuItem();
        itmConsultaProduto = new javax.swing.JMenuItem();
        itmRelatorioProduto = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itmUnidadeMedida = new javax.swing.JMenuItem();
        mnuVendas = new javax.swing.JMenu();
        itmCadastroVenda = new javax.swing.JMenuItem();
        itmConsultaVenda = new javax.swing.JMenuItem();
        itmRelatorioVenda = new javax.swing.JMenuItem();
        mnuFornecedor = new javax.swing.JMenu();
        itmCadastroFornecedor = new javax.swing.JMenuItem();
        itmConsultaFornecedor = new javax.swing.JMenuItem();
        itmRelatorioFornecedor = new javax.swing.JMenuItem();
        mnuCompras = new javax.swing.JMenu();
        itmCadastroCompra = new javax.swing.JMenuItem();
        itmConsultaCompra = new javax.swing.JMenuItem();
        itmRelatorioCompra = new javax.swing.JMenuItem();
        mnuFinanceiro = new javax.swing.JMenu();
        itmContasReceber = new javax.swing.JMenuItem();
        itmContasPagar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itmFormasPagamento = new javax.swing.JMenuItem();
        mnuColaborador = new javax.swing.JMenu();
        itmCadastroColaborador = new javax.swing.JMenuItem();
        itmConsultaColaborador = new javax.swing.JMenuItem();
        itmRelatorioColaborador = new javax.swing.JMenuItem();
        mnuSistema = new javax.swing.JMenu();
        itmSair = new javax.swing.JMenuItem();
        itmSair2 = new javax.swing.JMenuItem();
        itmSair3 = new javax.swing.JMenuItem();
        itmParametros = new javax.swing.JMenuItem();
        itmPermissoes = new javax.swing.JMenuItem();
        itmSobre = new javax.swing.JMenuItem();
        itmSair1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartAgro");
        setBackground(new java.awt.Color(254, 254, 254));
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        dskArea.setPreferredSize(new java.awt.Dimension(900, 900));

        jtaNotificacoes.setColumns(20);
        jtaNotificacoes.setFont(new java.awt.Font("Noto Sans", 0, 10)); // NOI18N
        jtaNotificacoes.setForeground(new java.awt.Color(255, 31, 0));
        jtaNotificacoes.setRows(5);
        jtaNotificacoes.setEnabled(false);
        jScrollPane1.setViewportView(jtaNotificacoes);

        lblNotific.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNotific.setText("Notificações:");

        JtnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/eraser.png"))); // NOI18N
        JtnLimpar.setText("Limpar");
        JtnLimpar.setToolTipText("Limpar");
        JtnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JtnLimparActionPerformed(evt);
            }
        });

        jplVendasPorVendedor.setOpaque(false);

        javax.swing.GroupLayout jplVendasPorVendedorLayout = new javax.swing.GroupLayout(jplVendasPorVendedor);
        jplVendasPorVendedor.setLayout(jplVendasPorVendedorLayout);
        jplVendasPorVendedorLayout.setHorizontalGroup(
            jplVendasPorVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jplVendasPorVendedorLayout.setVerticalGroup(
            jplVendasPorVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jplValorVendidoPorVendedorLayout = new javax.swing.GroupLayout(jplValorVendidoPorVendedor);
        jplValorVendidoPorVendedor.setLayout(jplValorVendidoPorVendedorLayout);
        jplValorVendidoPorVendedorLayout.setHorizontalGroup(
            jplValorVendidoPorVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );
        jplValorVendidoPorVendedorLayout.setVerticalGroup(
            jplValorVendidoPorVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jplBarraLayout = new javax.swing.GroupLayout(jplBarra);
        jplBarra.setLayout(jplBarraLayout);
        jplBarraLayout.setHorizontalGroup(
            jplBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );
        jplBarraLayout.setVerticalGroup(
            jplBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jplValorVendidoPorMesLayout = new javax.swing.GroupLayout(jplValorVendidoPorMes);
        jplValorVendidoPorMes.setLayout(jplValorVendidoPorMesLayout);
        jplValorVendidoPorMesLayout.setHorizontalGroup(
            jplValorVendidoPorMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jplValorVendidoPorMesLayout.setVerticalGroup(
            jplValorVendidoPorMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        pnlPeriodo.setBackground(new java.awt.Color(254, 254, 254));
        pnlPeriodo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Período", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(1, 1, 1))); // NOI18N
        pnlPeriodo.setForeground(new java.awt.Color(1, 1, 1));

        dchInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/calendar.png")));
        dchInicio.setInheritsPopupMenu(true);

        dchFim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/calendar.png")));
        dchFim.setInheritsPopupMenu(true);

        jLabel3.setText("Início");

        jLabel4.setText("Fim");

        javax.swing.GroupLayout pnlPeriodoLayout = new javax.swing.GroupLayout(pnlPeriodo);
        pnlPeriodo.setLayout(pnlPeriodoLayout);
        pnlPeriodoLayout.setHorizontalGroup(
            pnlPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dchFim, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(dchInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPeriodoLayout.setVerticalGroup(
            pnlPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dchInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(pnlPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dchFim, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTotalVendido.setBackground(new java.awt.Color(254, 254, 254));
        pnlTotalVendido.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor total vendido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(1, 1, 1))); // NOI18N
        pnlTotalVendido.setForeground(new java.awt.Color(1, 1, 1));

        labelValorVendas.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelValorVendas.setText("R$ 40.500,60");

        javax.swing.GroupLayout pnlTotalVendidoLayout = new javax.swing.GroupLayout(pnlTotalVendido);
        pnlTotalVendido.setLayout(pnlTotalVendidoLayout);
        pnlTotalVendidoLayout.setHorizontalGroup(
            pnlTotalVendidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalVendidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelValorVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTotalVendidoLayout.setVerticalGroup(
            pnlTotalVendidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalVendidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelValorVendas, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlQtdVendida.setBackground(new java.awt.Color(254, 254, 254));
        pnlQtdVendida.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vendas realizadas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(1, 1, 1))); // NOI18N
        pnlQtdVendida.setForeground(new java.awt.Color(1, 1, 1));

        labelQuantidadeVendas.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelQuantidadeVendas.setText("R$ 40.500,60");

        javax.swing.GroupLayout pnlQtdVendidaLayout = new javax.swing.GroupLayout(pnlQtdVendida);
        pnlQtdVendida.setLayout(pnlQtdVendidaLayout);
        pnlQtdVendidaLayout.setHorizontalGroup(
            pnlQtdVendidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQtdVendidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelQuantidadeVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlQtdVendidaLayout.setVerticalGroup(
            pnlQtdVendidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQtdVendidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelQuantidadeVendas, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        dskArea.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskArea.setLayer(lblNotific, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskArea.setLayer(JtnLimpar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskArea.setLayer(jplVendasPorVendedor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskArea.setLayer(jplValorVendidoPorVendedor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskArea.setLayer(jplBarra, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskArea.setLayer(jplValorVendidoPorMes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskArea.setLayer(pnlPeriodo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskArea.setLayer(pnlTotalVendido, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskArea.setLayer(pnlQtdVendida, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dskAreaLayout = new javax.swing.GroupLayout(dskArea);
        dskArea.setLayout(dskAreaLayout);
        dskAreaLayout.setHorizontalGroup(
            dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dskAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dskAreaLayout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(lblNotific, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(JtnLimpar))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlTotalVendido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlQtdVendida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(28, 28, 28)
                .addGroup(dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jplBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dskAreaLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jplVendasPorVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dskAreaLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jplValorVendidoPorVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(408, Short.MAX_VALUE))
                    .addGroup(dskAreaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jplValorVendidoPorMes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        dskAreaLayout.setVerticalGroup(
            dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dskAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dskAreaLayout.createSequentialGroup()
                        .addComponent(pnlPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlTotalVendido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlQtdVendida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jplVendasPorVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jplValorVendidoPorVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dskAreaLayout.createSequentialGroup()
                        .addGroup(dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jplValorVendidoPorMes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jplBarra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(136, 136, 136))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dskAreaLayout.createSequentialGroup()
                        .addGroup(dskAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JtnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNotific, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );

        pnlPeriodo.getAccessibleContext().setAccessibleName("pnlIndicador");

        btnOcultarDash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/stop.png"))); // NOI18N
        btnOcultarDash.setText("Ocultar Dash");
        btnOcultarDash.setToolTipText("Limpar");
        btnOcultarDash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarDashActionPerformed(evt);
            }
        });

        lbUsuario.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbUsuario.setText("label1");

        btnAtualizarDash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/barras.png"))); // NOI18N
        btnAtualizarDash.setText("Atualizar Dash");
        btnAtualizarDash.setToolTipText("Limpar");
        btnAtualizarDash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarDashActionPerformed(evt);
            }
        });

        barMenu.setAutoscrolls(true);

        mnuCliente.setMnemonic('C');
        mnuCliente.setText("Clientes");

        itmCadastroCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/register.png"))); // NOI18N
        itmCadastroCliente.setText("Cadastro");
        itmCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCadastroClienteActionPerformed(evt);
            }
        });
        mnuCliente.add(itmCadastroCliente);

        itmConsultaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/browser.png"))); // NOI18N
        itmConsultaCliente.setText("Consulta");
        itmConsultaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmConsultaClienteActionPerformed(evt);
            }
        });
        mnuCliente.add(itmConsultaCliente);

        itmRelatorioCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/report.png"))); // NOI18N
        itmRelatorioCliente.setText("Relatório");
        itmRelatorioCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmRelatorioClienteActionPerformed(evt);
            }
        });
        mnuCliente.add(itmRelatorioCliente);

        barMenu.add(mnuCliente);

        mnuProduto.setMnemonic('P');
        mnuProduto.setText("Produtos");

        itmCadastroProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/register.png"))); // NOI18N
        itmCadastroProduto.setText("Cadastro");
        itmCadastroProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCadastroProdutoActionPerformed(evt);
            }
        });
        mnuProduto.add(itmCadastroProduto);

        itmConsultaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/browser.png"))); // NOI18N
        itmConsultaProduto.setText("Consulta");
        itmConsultaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmConsultaProdutoActionPerformed(evt);
            }
        });
        mnuProduto.add(itmConsultaProduto);

        itmRelatorioProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/report.png"))); // NOI18N
        itmRelatorioProduto.setText("Relatório");
        itmRelatorioProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmRelatorioProdutoActionPerformed(evt);
            }
        });
        mnuProduto.add(itmRelatorioProduto);
        mnuProduto.add(jSeparator1);

        itmUnidadeMedida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/compass.png"))); // NOI18N
        itmUnidadeMedida.setText("Unidades de Medida");
        itmUnidadeMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmUnidadeMedidaActionPerformed(evt);
            }
        });
        mnuProduto.add(itmUnidadeMedida);

        barMenu.add(mnuProduto);

        mnuVendas.setMnemonic('V');
        mnuVendas.setText("Vendas");

        itmCadastroVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/register.png"))); // NOI18N
        itmCadastroVenda.setText("Cadastro");
        itmCadastroVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCadastroVendaActionPerformed(evt);
            }
        });
        mnuVendas.add(itmCadastroVenda);

        itmConsultaVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/browser.png"))); // NOI18N
        itmConsultaVenda.setText("Consulta");
        itmConsultaVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmConsultaVendaActionPerformed(evt);
            }
        });
        mnuVendas.add(itmConsultaVenda);

        itmRelatorioVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/report.png"))); // NOI18N
        itmRelatorioVenda.setText("Relatório");
        itmRelatorioVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmRelatorioVendaActionPerformed(evt);
            }
        });
        mnuVendas.add(itmRelatorioVenda);

        barMenu.add(mnuVendas);

        mnuFornecedor.setMnemonic('F');
        mnuFornecedor.setText("Fornecedores");

        itmCadastroFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/register.png"))); // NOI18N
        itmCadastroFornecedor.setText("Cadastro");
        itmCadastroFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCadastroFornecedorActionPerformed(evt);
            }
        });
        mnuFornecedor.add(itmCadastroFornecedor);

        itmConsultaFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/browser.png"))); // NOI18N
        itmConsultaFornecedor.setText("Consulta");
        itmConsultaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmConsultaFornecedorActionPerformed(evt);
            }
        });
        mnuFornecedor.add(itmConsultaFornecedor);

        itmRelatorioFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/report.png"))); // NOI18N
        itmRelatorioFornecedor.setText("Relatório");
        itmRelatorioFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmRelatorioFornecedorActionPerformed(evt);
            }
        });
        mnuFornecedor.add(itmRelatorioFornecedor);

        barMenu.add(mnuFornecedor);

        mnuCompras.setMnemonic('M');
        mnuCompras.setText("Compras");

        itmCadastroCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/register.png"))); // NOI18N
        itmCadastroCompra.setText("Cadastro");
        itmCadastroCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCadastroCompraActionPerformed(evt);
            }
        });
        mnuCompras.add(itmCadastroCompra);

        itmConsultaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/browser.png"))); // NOI18N
        itmConsultaCompra.setText("Consulta");
        itmConsultaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmConsultaCompraActionPerformed(evt);
            }
        });
        mnuCompras.add(itmConsultaCompra);

        itmRelatorioCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/report.png"))); // NOI18N
        itmRelatorioCompra.setText("Relatório");
        itmRelatorioCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmRelatorioCompraActionPerformed(evt);
            }
        });
        mnuCompras.add(itmRelatorioCompra);

        barMenu.add(mnuCompras);

        mnuFinanceiro.setMnemonic('F');
        mnuFinanceiro.setText("Financeiro");

        itmContasReceber.setText("Contas a Receber");
        mnuFinanceiro.add(itmContasReceber);

        itmContasPagar.setText("Contas a Pagar");
        mnuFinanceiro.add(itmContasPagar);
        mnuFinanceiro.add(jSeparator2);

        itmFormasPagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/money.png"))); // NOI18N
        itmFormasPagamento.setText("Formas de Pagamento");
        itmFormasPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmFormasPagamentoActionPerformed(evt);
            }
        });
        mnuFinanceiro.add(itmFormasPagamento);

        barMenu.add(mnuFinanceiro);

        mnuColaborador.setText("Colaboradores");

        itmCadastroColaborador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/register.png"))); // NOI18N
        itmCadastroColaborador.setText("Cadastro");
        itmCadastroColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCadastroColaboradorActionPerformed(evt);
            }
        });
        mnuColaborador.add(itmCadastroColaborador);

        itmConsultaColaborador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/browser.png"))); // NOI18N
        itmConsultaColaborador.setText("Consulta");
        itmConsultaColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmConsultaColaboradorActionPerformed(evt);
            }
        });
        mnuColaborador.add(itmConsultaColaborador);

        itmRelatorioColaborador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/report.png"))); // NOI18N
        itmRelatorioColaborador.setText("Relatório");
        itmRelatorioColaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmRelatorioColaboradorActionPerformed(evt);
            }
        });
        mnuColaborador.add(itmRelatorioColaborador);

        barMenu.add(mnuColaborador);

        mnuSistema.setMnemonic('S');
        mnuSistema.setText("Sistema");

        itmSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/senha.png"))); // NOI18N
        itmSair.setText("Alterar Senha Usuário");
        itmSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSairActionPerformed(evt);
            }
        });
        mnuSistema.add(itmSair);

        itmSair2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/browser.png"))); // NOI18N
        itmSair2.setText("Auditoria");
        itmSair2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSair2ActionPerformed(evt);
            }
        });
        mnuSistema.add(itmSair2);

        itmSair3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/document.png"))); // NOI18N
        itmSair3.setText("Licenciamento");
        itmSair3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSair3ActionPerformed(evt);
            }
        });
        mnuSistema.add(itmSair3);

        itmParametros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/parametros.png"))); // NOI18N
        itmParametros.setText("Parâmetros");
        itmParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmParametrosActionPerformed(evt);
            }
        });
        mnuSistema.add(itmParametros);

        itmPermissoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/locked.png"))); // NOI18N
        itmPermissoes.setText("Permissões");
        itmPermissoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmPermissoesActionPerformed(evt);
            }
        });
        mnuSistema.add(itmPermissoes);

        itmSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/about.png"))); // NOI18N
        itmSobre.setText("Sobre");
        itmSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSobreActionPerformed(evt);
            }
        });
        mnuSistema.add(itmSobre);

        itmSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/power.png"))); // NOI18N
        itmSair1.setText("Sair");
        itmSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmSair1ActionPerformed(evt);
            }
        });
        mnuSistema.add(itmSair1);

        barMenu.add(mnuSistema);

        setJMenuBar(barMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtualizarDash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOcultarDash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(dskArea, javax.swing.GroupLayout.DEFAULT_SIZE, 1458, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dskArea, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOcultarDash, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAtualizarDash, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itmCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmCadastroClienteActionPerformed
        cadastroCliente(0);
    }//GEN-LAST:event_itmCadastroClienteActionPerformed

    private void itmConsultaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmConsultaClienteActionPerformed
        cadastroCliente(1);
    }//GEN-LAST:event_itmConsultaClienteActionPerformed

    private void itmRelatorioClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmRelatorioClienteActionPerformed
        cadastroCliente(2);
    }//GEN-LAST:event_itmRelatorioClienteActionPerformed

    private void itmCadastroProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmCadastroProdutoActionPerformed
        cadastroProduto(0);
    }//GEN-LAST:event_itmCadastroProdutoActionPerformed

    private void itmConsultaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmConsultaProdutoActionPerformed
        cadastroProduto(1);
    }//GEN-LAST:event_itmConsultaProdutoActionPerformed

    private void itmRelatorioProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmRelatorioProdutoActionPerformed
        cadastroProduto(2);
    }//GEN-LAST:event_itmRelatorioProdutoActionPerformed

    private void itmUnidadeMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmUnidadeMedidaActionPerformed
        cadastroUnidadeMedida();
    }//GEN-LAST:event_itmUnidadeMedidaActionPerformed

    private void itmCadastroFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmCadastroFornecedorActionPerformed
        cadastroFornecedor(0);
    }//GEN-LAST:event_itmCadastroFornecedorActionPerformed

    private void itmConsultaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmConsultaFornecedorActionPerformed
        cadastroFornecedor(1);
    }//GEN-LAST:event_itmConsultaFornecedorActionPerformed

    private void itmRelatorioFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmRelatorioFornecedorActionPerformed
        cadastroFornecedor(2);
    }//GEN-LAST:event_itmRelatorioFornecedorActionPerformed

    private void itmCadastroColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmCadastroColaboradorActionPerformed
        cadastroColaborador(0);
    }//GEN-LAST:event_itmCadastroColaboradorActionPerformed

    private void itmConsultaColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmConsultaColaboradorActionPerformed
        cadastroColaborador(1);
    }//GEN-LAST:event_itmConsultaColaboradorActionPerformed

    private void itmRelatorioColaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmRelatorioColaboradorActionPerformed

    }//GEN-LAST:event_itmRelatorioColaboradorActionPerformed

    private void itmSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSairActionPerformed
        alterarSenha();
    }//GEN-LAST:event_itmSairActionPerformed

    private void itmFormasPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmFormasPagamentoActionPerformed
        cadastroFormaPagamento();
    }//GEN-LAST:event_itmFormasPagamentoActionPerformed

    private void itmCadastroCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmCadastroCompraActionPerformed
        cadastroCompra(0);
    }//GEN-LAST:event_itmCadastroCompraActionPerformed

    private void itmConsultaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmConsultaCompraActionPerformed
        cadastroCompra(1);
    }//GEN-LAST:event_itmConsultaCompraActionPerformed

    private void itmRelatorioCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmRelatorioCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itmRelatorioCompraActionPerformed

    private void itmCadastroVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmCadastroVendaActionPerformed
        cadastroVenda(0);
    }//GEN-LAST:event_itmCadastroVendaActionPerformed

    private void itmConsultaVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmConsultaVendaActionPerformed
        cadastroVenda(1);
    }//GEN-LAST:event_itmConsultaVendaActionPerformed

    private void itmRelatorioVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmRelatorioVendaActionPerformed
        cadastroVenda(2);
    }//GEN-LAST:event_itmRelatorioVendaActionPerformed

    private void itmSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSair1ActionPerformed
        try {
            c.stop();
            c.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }//GEN-LAST:event_itmSair1ActionPerformed

    private void parametros() {
        IfrmParametros ifrmPar = new IfrmParametros(parametros);
        dskArea.add(ifrmPar);
        ifrmPar.setVisible(true);
    }

    private void alterarSenha() {
        DlgAlterarSenha dlgFP = new DlgAlterarSenha(this, true, jfrLogin.getUsuarioLogado());
        dlgFP.setLocationRelativeTo(this);
        dlgFP.setVisible(true);
    }

    private void licenciamento() {
        DlgLicenca licen = new DlgLicenca(this, true);
        licen.setLocationRelativeTo(this);

        licen.getJlTitulobMensagem().setText("Status atual:");

        try {

            XStream xstream = new XStream(new DomDriver());

            Path arquivo = Paths.get("smartagro.licenca");

            byte[] textoCriptografado = Files.readAllBytes(arquivo);

            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream("private.key"));
            final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();

            final String textoPuro = decriptografa(textoCriptografado, chavePrivada);
            Licenca licenca = (Licenca) xstream.fromXML(textoPuro);
             
            licen.getJlbMensagem().setText("Sua licença expira em: " + Formatacao.DataDMA(licenca.getValidade()));

        } catch (Exception e) {
            e.printStackTrace();
        }

       

        licen.setVisible(true);
    }

    private void itmPermissoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmPermissoesActionPerformed
        cadastroPermissoes();
    }//GEN-LAST:event_itmPermissoesActionPerformed

    private void itmParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmParametrosActionPerformed
        parametros();
    }//GEN-LAST:event_itmParametrosActionPerformed

    private void itmSair2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSair2ActionPerformed
        auditoria(0);
    }//GEN-LAST:event_itmSair2ActionPerformed

    private void btnAtualizarDashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarDashActionPerformed

        // Mostra os paineis caso estavam invisible
        alteraStatusDash(true);

        //Atualiza graficos de vendasPorVendedor
        vendasPorVendedor.setPizza(this.grafdao.vendaPorColaborador(dchInicio.getDate(), dchFim.getDate()), "Inteiro");
        valorVendidoPorVendedor.setPizza(this.grafdao.valorVendidoPorColaborador(dchInicio.getDate(), dchFim.getDate()), "Decimal");
        vendasPorMes.setDadosBarra(this.grafdao.quantidadeDeVendasNoAnoPorMes(dchInicio.getDate(), dchFim.getDate()), "Inteiro");
        valorVendidoPorMes.setDadosLinha(this.grafdao.valorTotalVendidoNoAno(dchInicio.getDate(), dchFim.getDate()), "Decimal");

        //Indicador
        labelValorVendas.setText("R$ " + this.grafdao.valorTotalVendidoNoMes(dchInicio.getDate(), dchFim.getDate()) + "");
        labelQuantidadeVendas.setText(this.grafdao.quantidadeDeVendasrealizadas(dchInicio.getDate(), dchFim.getDate()) + "");
    }//GEN-LAST:event_btnAtualizarDashActionPerformed

    private void btnOcultarDashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarDashActionPerformed
        alteraStatusDash(false);

    }//GEN-LAST:event_btnOcultarDashActionPerformed

    private void JtnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JtnLimparActionPerformed
        jtaNotificacoes.setText("");
    }//GEN-LAST:event_JtnLimparActionPerformed

    private void itmSair3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSair3ActionPerformed
        licenciamento();
    }//GEN-LAST:event_itmSair3ActionPerformed
    private void itmSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmSobreActionPerformed
        exibeSobre(false);
    }//GEN-LAST:event_itmSobreActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        ReleaseDAO rDAO = new ReleaseDAO();

        if (rDAO.existeReleaseParaVisualizar(jfrLogin.getUsuarioLogado().getId())) {
            exibeSobre(true);
        }
    }//GEN-LAST:event_formWindowOpened

    private void cadastroVenda(int aba) {
        IfrmVenda janelaVenda = new IfrmVenda(aba);
        dskArea.add(janelaVenda);
        janelaVenda.setVisible(true);
        // Abre a tela de venda maximizada
        try {
            janelaVenda.setMaximum(true);
        } catch (Exception e) {
        }
    }

    private void alteraStatusDash(boolean status) {
        this.jplVendasPorVendedor.setVisible(status);
        this.jplValorVendidoPorMes.setVisible(status);
        this.jplValorVendidoPorVendedor.setVisible(status);
        this.jplBarra.setVisible(status);
        this.pnlPeriodo.setVisible(status);
        this.pnlQtdVendida.setVisible(status);
        this.pnlTotalVendido.setVisible(status);
    }

    private void auditoria(int aba) {
        IfrmAuditoria janelaAud = new IfrmAuditoria(aba);
        dskArea.add(janelaAud);
        janelaAud.setVisible(true);
    }

    private void cadastroColaborador(int aba) {
        IfrmColaborador janelaColab = new IfrmColaborador(aba);
        dskArea.add(janelaColab);
        janelaColab.setVisible(true);
    }

    private void cadastroCliente(int aba) {
        IfrmCliente janelaClientes = new IfrmCliente(aba);
        dskArea.add(janelaClientes);
        janelaClientes.setVisible(true);
    }

    private void cadastroProduto(int aba) {
        IfrmProduto janelaProdutos = new IfrmProduto(aba);
        dskArea.add(janelaProdutos);
        janelaProdutos.setVisible(true);
    }

    private void cadastroUnidadeMedida() {
        IfrmUnidadeMedida janelaUnidadesMedida = new IfrmUnidadeMedida();
        dskArea.add(janelaUnidadesMedida);
        janelaUnidadesMedida.setVisible(true);
    }

    private void cadastroFormaPagamento() {
        IfrmFormaPagamento janelaFormaPagamento = new IfrmFormaPagamento();
        dskArea.add(janelaFormaPagamento);
        janelaFormaPagamento.setVisible(true);
    }

    private void cadastroFornecedor(int aba) {
        IfrmFornecedor janelaFornecedores = new IfrmFornecedor(aba);
        dskArea.add(janelaFornecedores);
        janelaFornecedores.setVisible(true);
    }

    private void cadastroCompra(int aba) {
        IfrmCompra janelaCompra = new IfrmCompra(aba);
        dskArea.add(janelaCompra);
        janelaCompra.setVisible(true);
        // Abre a tela de venda maximizada
        try {
            janelaCompra.setMaximum(true);
        } catch (Exception e) {
        }
    }

    private void cadastroPermissoes() {
        if (jfrLogin.getUsuarioLogado().getTipousuario() != 'a') {
            Mensagem.mostraAletra("Permissão negada", "Você não tem permissão para acessar esta funcionalidade");
            return;
        }

        IfrmPermissoes janelaPermissoes = new IfrmPermissoes();
        dskArea.add(janelaPermissoes);
        janelaPermissoes.setVisible(true);
    }

    private void exibeSobre(boolean atualizarVisualizacao) {
        DlgSobre janelaSobre = new DlgSobre(this, true, atualizarVisualizacao);
        janelaSobre.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JtnLimpar;
    private javax.swing.JMenuBar barMenu;
    private javax.swing.JButton btnAtualizarDash;
    private javax.swing.JButton btnOcultarDash;
    private com.toedter.calendar.JDateChooser dchFim;
    private com.toedter.calendar.JDateChooser dchInicio;
    private javax.swing.JDesktopPane dskArea;
    private javax.swing.JMenuItem itmCadastroCliente;
    private javax.swing.JMenuItem itmCadastroColaborador;
    private javax.swing.JMenuItem itmCadastroCompra;
    private javax.swing.JMenuItem itmCadastroFornecedor;
    private javax.swing.JMenuItem itmCadastroProduto;
    private javax.swing.JMenuItem itmCadastroVenda;
    private javax.swing.JMenuItem itmConsultaCliente;
    private javax.swing.JMenuItem itmConsultaColaborador;
    private javax.swing.JMenuItem itmConsultaCompra;
    private javax.swing.JMenuItem itmConsultaFornecedor;
    private javax.swing.JMenuItem itmConsultaProduto;
    private javax.swing.JMenuItem itmConsultaVenda;
    private javax.swing.JMenuItem itmContasPagar;
    private javax.swing.JMenuItem itmContasReceber;
    private javax.swing.JMenuItem itmFormasPagamento;
    private javax.swing.JMenuItem itmParametros;
    private javax.swing.JMenuItem itmPermissoes;
    private javax.swing.JMenuItem itmRelatorioCliente;
    private javax.swing.JMenuItem itmRelatorioColaborador;
    private javax.swing.JMenuItem itmRelatorioCompra;
    private javax.swing.JMenuItem itmRelatorioFornecedor;
    private javax.swing.JMenuItem itmRelatorioProduto;
    private javax.swing.JMenuItem itmRelatorioVenda;
    private javax.swing.JMenuItem itmSair;
    private javax.swing.JMenuItem itmSair1;
    private javax.swing.JMenuItem itmSair2;
    private javax.swing.JMenuItem itmSair3;
    private javax.swing.JMenuItem itmSobre;
    private javax.swing.JMenuItem itmUnidadeMedida;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPanel jplBarra;
    private javax.swing.JPanel jplValorVendidoPorMes;
    private javax.swing.JPanel jplValorVendidoPorVendedor;
    private javax.swing.JPanel jplVendasPorVendedor;
    private javax.swing.JTextArea jtaNotificacoes;
    private java.awt.Label labelQuantidadeVendas;
    private java.awt.Label labelValorVendas;
    private java.awt.Label lbUsuario;
    private java.awt.Label lblNotific;
    private javax.swing.JMenu mnuCliente;
    private javax.swing.JMenu mnuColaborador;
    private javax.swing.JMenu mnuCompras;
    private javax.swing.JMenu mnuFinanceiro;
    private javax.swing.JMenu mnuFornecedor;
    private javax.swing.JMenu mnuProduto;
    private javax.swing.JMenu mnuSistema;
    private javax.swing.JMenu mnuVendas;
    private javax.swing.JPanel pnlPeriodo;
    private javax.swing.JPanel pnlQtdVendida;
    private javax.swing.JPanel pnlTotalVendido;
    // End of variables declaration//GEN-END:variables
}

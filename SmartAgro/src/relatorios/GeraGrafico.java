package relatorios;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class GeraGrafico {

    private String nome;
    private boolean legenda = true;
    private boolean tooltip = true;
    private boolean urls = false;
    private DefaultPieDataset dadosPizza;
    private JFreeChart grafico;
    private ChartPanel painel;

    public GeraGrafico(String nome) {
        this.nome = nome;
    }

    public void criarGraficoPizza(ArrayList<String> dados, String tipoValor) {
        this.dadosPizza = new DefaultPieDataset();

        if (tipoValor.equals(("Inteiro"))) {

            for (String d : dados) {

                String[] v = d.split(",");

                dadosPizza.setValue(v[0], Integer.parseInt(v[1]));

            }

        } else if (tipoValor.equals("Decimal")) {

            for (String d : dados) {

                String[] v = d.split(",");

                dadosPizza.setValue(v[0], Double.parseDouble(v[1]));

            }

        }
        this.grafico = ChartFactory.createPieChart(nome, dadosPizza, legenda, tooltip, urls);
        this.painel = new ChartPanel(grafico);
    }

    public ChartPanel getPainel() {
        return painel;
    }

    public void setPainel(ChartPanel painel) {
        this.painel = painel;
    }

    public JFreeChart getGrafico() {
        return grafico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isLegenda() {
        return legenda;
    }

    public void setLegenda(boolean legenda) {
        this.legenda = legenda;
    }

    public boolean isTooltip() {
        return tooltip;
    }

    public void setTooltip(boolean tooltip) {
        this.tooltip = tooltip;
    }

    public boolean isUrls() {
        return urls;
    }

    public void setUrls(boolean urls) {
        this.urls = urls;
    }

    public DefaultPieDataset getDadosPizza() {
        return dadosPizza;
    }

    public void setPizza(ArrayList<String> dados, String tipoValor) {
        if (tipoValor.equals(("Inteiro"))) {

            for (String d : dados) {

                String[] v = d.split(",");

                dadosPizza.setValue(v[0], Integer.parseInt(v[1]));
            }
        } else if (tipoValor.equals("Decimal")) {

            for (String d : dados) {

                String[] v = d.split(",");

                dadosPizza.setValue(v[0], Double.parseDouble(v[1]));

            }
        }

    }
}

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
    private JFreeChart grafico;

    public void setGrafico(JFreeChart grafico) {
        this.grafico = grafico;
    }
    private ChartPanel painel;

    public GeraGrafico(String nome) {
        this.nome = nome;
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

}

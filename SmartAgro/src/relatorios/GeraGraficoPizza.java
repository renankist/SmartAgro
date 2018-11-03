/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.awt.Color;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author renan
 */
public final class GeraGraficoPizza extends GeraGrafico {

    private DefaultPieDataset dadosPizza;

    public GeraGraficoPizza(String nome) {
        super(nome);
    }

    public void criarGraficoPizza(ArrayList<String> dados, String tipoValor) {

        this.dadosPizza = new DefaultPieDataset();

        if (tipoValor.equals(("Inteiro"))) {

            for (String d : dados) {

                String[] v = d.split(",");

                this.dadosPizza.setValue(v[0], Integer.parseInt(v[1]));

            }

        } else if (tipoValor.equals("Decimal")) {

            for (String d : dados) {

                String[] v = d.split(",");

                this.dadosPizza.setValue(v[0], Double.parseDouble(v[1]));

            }

        }
        this.setGrafico(ChartFactory.createPieChart(this.getNome(), this.dadosPizza, this.isLegenda(), this.isTooltip(), this.isUrls()));

        //Configurando as cores do gráfico
        this.getGrafico().getPlot().setBackgroundPaint(Color.WHITE);
        this.getGrafico().getPlot().setOutlinePaint(Color.WHITE);
       // this.getGrafico().getLegen

        this.setPainel(new ChartPanel(this.getGrafico()));

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

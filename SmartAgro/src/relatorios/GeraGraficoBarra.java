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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author renan
 */
public class GeraGraficoBarra extends GeraGrafico {

    private DefaultCategoryDataset dadosBarra;
    private PlotOrientation orientacao;
    private String descricaoX = ""; 
    private String descricaoY = ""; 
    
    
    public GeraGraficoBarra(String nome, int orientacao) {
        super(nome);
        if(orientacao != 1){
            this.orientacao = PlotOrientation.VERTICAL;
        }else{
            this.orientacao = PlotOrientation.HORIZONTAL;
        }
    }

    public String getDescricaoX() {
        return descricaoX;
    }

    public void setDescricaoX(String descricaoX) {
        this.descricaoX = descricaoX;
    }

    public String getDescricaoY() {
        return descricaoY;
    }

    public void setDescricaoY(String descricaoY) {
        this.descricaoY = descricaoY;
    }

    public void criarGraficoBarras(ArrayList<String> dados, String tipoValor) {

        this.dadosBarra = new DefaultCategoryDataset();

        if (tipoValor.equals(("Inteiro"))) {

            for (String d : dados) {

                String[] v = d.split(",");

                this.dadosBarra.setValue(Integer.parseInt(v[1]), v[0], "");

            }

        } else if (tipoValor.equals("Decimal")) {

            for (String d : dados) {

                String[] v = d.split(",");

                this.dadosBarra.setValue(Double.parseDouble(v[1]), v[0], "");

            }

        }
        this.setGrafico(ChartFactory.createBarChart(this.getNome(),this.descricaoX,this.descricaoY,this.dadosBarra,this.getOrientacao(), this.isLegenda(), this.isTooltip(), this.isUrls()));
        //Configurando as cores do gráfico
        this.getGrafico().getPlot().setBackgroundPaint(Color.WHITE);
        this.getGrafico().getPlot().setOutlinePaint(Color.WHITE);
          //this.getGrafico().getXYPlot().setOutlinePaint(Color.WHITE);
        
        this.setPainel(new ChartPanel(this.getGrafico()));

    }

    public DefaultCategoryDataset getDadosBarra() {
        return dadosBarra;
    }

    public PlotOrientation getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(PlotOrientation orientacao) {
        this.orientacao = orientacao;
    }

    public void setDadosBarra(ArrayList<String> dados, String tipoValor) {
        
        
        this.dadosBarra.clear();
        
        if (tipoValor.equals(("Inteiro"))) {

            for (String d : dados) {

                String[] v = d.split(",");
                System.out.println(Integer.parseInt(v[1])+" "+v[0]);
                this.dadosBarra.setValue(Integer.parseInt(v[1]), v[0], "");

            }

        } else if (tipoValor.equals("Decimal")) {

            for (String d : dados) {

                String[] v = d.split(",");

                this.dadosBarra.setValue(Double.parseDouble(v[1]), v[0], "");

            }

        }

    }

}

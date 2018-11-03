package relatorios;

import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GeraGraficoLinha extends GeraGrafico {

    private DefaultCategoryDataset dadosLinha;
    private String descricaoX = "";
    private String descricaoY = "";

    public GeraGraficoLinha(String nome) {
        super(nome);
    }

    public void criaGraficoLinha(ArrayList<String> dados, String tipoValor) {
        final String serie = "valor";
        this.dadosLinha = new DefaultCategoryDataset();

        if (tipoValor.equals(("Inteiro"))) {

            for (String d : dados) {

                String[] v = d.split(",");

                this.dadosLinha.setValue(Integer.parseInt(v[1]), serie, v[0]);

            }

        } else if (tipoValor.equals("Decimal")) {

            for (String d : dados) {

                String[] v = d.split(",");

                this.dadosLinha.setValue(Double.parseDouble(v[1]), serie, v[0]);

            }

        }
        this.setGrafico(ChartFactory.createLineChart(this.getNome(), this.descricaoX, this.descricaoY, dadosLinha, PlotOrientation.VERTICAL, this.isLegenda(), this.isTooltip(), this.isUrls()));
        //Configurando as cores do gráfico
        this.getGrafico().getPlot().setBackgroundPaint(Color.WHITE);
        this.getGrafico().getPlot().setOutlinePaint(Color.WHITE);
      
  
        this.setPainel(new ChartPanel(this.getGrafico()));
    }

    public void setDescricaoX(String descricaoX) {
        this.descricaoX = descricaoX;
    }

    public void setDescricaoY(String descricaoY) {
        this.descricaoY = descricaoY;
    }

    public DefaultCategoryDataset getDadosLinha() {
        return dadosLinha;
    }

    public void setDadosLinha(ArrayList<String> dados, String tipoValor) {
        if (tipoValor.equals(("Inteiro"))) {

            for (String d : dados) {

                String[] v = d.split(",");

                this.dadosLinha.setValue(Integer.parseInt(v[1]), v[0], "");

            }

        } else if (tipoValor.equals("Decimal")) {

            for (String d : dados) {

                String[] v = d.split(",");

                this.dadosLinha.setValue(Double.parseDouble(v[1]), v[0], "");

            }

        }

    }

}

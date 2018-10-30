package relatorios;

import java.awt.Dimension;
import java.util.ArrayList;
import org.hibernate.stat.internal.CategorizedStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoBarra {
    
    
    public CategoryDataset createDataSet(ArrayList<Pessoa> pessoas){
        
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        
        for(Pessoa pessoa : pessoas){
            dataSet.addValue(pessoa.getIdade(), pessoa.getNome(),"");
        }
        
        return dataSet;
    }
    
    public JFreeChart createBarChart(CategoryDataset dataSet){
        
        JFreeChart graficoBarras = ChartFactory.createBarChart("Pessoas e suas idades", "", "Idade", dataSet, PlotOrientation.VERTICAL, true, false, false);
        
        return graficoBarras;
        
    }
    
    public ChartPanel createChart(ArrayList<Pessoa> lista){
        
        CategoryDataset dataSet = this.createDataSet(lista);
        
        JFreeChart grafico = this.createBarChart(dataSet);
        
        ChartPanel painel = new ChartPanel(grafico);
        
        return painel;
        
    }
}

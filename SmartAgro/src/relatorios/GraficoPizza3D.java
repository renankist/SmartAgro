/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author renan
 */
public class GraficoPizza3D{

    public GraficoPizza3D(String chartTitle, PieDataset data){
        
        JFreeChart graficoPizza3D = createChart(data, chartTitle); 
        
        
    }
    
    private JFreeChart createChart(PieDataset pPieDataset, String pTituloGrafico){
        
        JFreeChart grafico = ChartFactory.createPieChart3D(pTituloGrafico, pPieDataset,true, true, false);
        
       // PiePlot3D plet = (PiePlot3D) grafico.getPlot();
        
        return null;
    }

    
}

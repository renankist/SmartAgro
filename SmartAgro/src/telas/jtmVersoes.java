/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Release;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author morgana.elis
 */
public class jtmVersoes extends AbstractTableModel {
    
    private ArrayList<Release> releases;
    private String[] colunas = {"Versões"};
    
    public jtmVersoes(ArrayList<Release> release) {
        this.releases = release;
    }

    public ArrayList<Release> getReleases() {
        return releases;
    }

    public void setReleases(ArrayList<Release> release) {
        this.releases = release;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Release forma) {
        this.releases.add(forma);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.releases.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return releases.get(linha).getVersao();
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Release get(int linha){
        return this.releases.get(linha);
    }
    
    public void removeRow(int linha){
        this.releases.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }
    
}

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
    
    private ArrayList<Release> release;
    private String[] colunas = {"Versão"};
    
    public jtmVersoes(ArrayList<Release> release) {
        this.release = release;
    }

    public ArrayList<Release> getReleases() {
        return release;
    }

    public void setReleases(ArrayList<Release> release) {
        this.release = release;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Release forma) {
        this.release.add(forma);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.release.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return release.get(linha).getVersao();
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Release get(int linha){
        return this.release.get(linha);
    }
    
    public void removeRow(int linha){
        this.release.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }
    
}

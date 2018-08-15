/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Cidade;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author morganabagatini
 */
public class jtmCidade extends AbstractTableModel {
    
    private ArrayList<Cidade> cidade;
    private String[] colunas = {"Nome", "UF"};

    public jtmCidade(ArrayList<Cidade> cidade) {
        this.cidade = cidade;
    }

    public ArrayList<Cidade> getCidades() {
        return cidade;
    }

    public void setCidades(ArrayList<Cidade> cidade) {
        this.cidade = cidade;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Cidade forma) {
        this.cidade.add(forma);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.cidade.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return cidade.get(linha).getNome();
            case 1:
                return cidade.get(linha).getEstado().getSigla();          
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Cidade get(int linha){
        return this.cidade.get(linha);
    }
    
    public void removeRow(int linha){
        this.cidade.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }
}

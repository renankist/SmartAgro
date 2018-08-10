/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Formapagamento;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rlkist
 */
public class jtmFormasPagamento extends AbstractTableModel {

    private ArrayList<Formapagamento> formas;
    private String[] colunas = {"Código", "Descrição"};

    public jtmFormasPagamento(ArrayList<Formapagamento> formas) {
        this.formas = formas;
    }

    public ArrayList<Formapagamento> getFormas() {
        return formas;
    }

    public void setFormas(ArrayList<Formapagamento> formas) {
        this.formas = formas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Formapagamento forma) {
        this.formas.add(forma);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.formas.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return formas.get(linha).getId();
            case 1:
                return formas.get(linha).getDescricao();          
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Formapagamento get(int linha){
        return this.formas.get(linha);
    }
    
    public void removeRow(int linha){
        this.formas.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}


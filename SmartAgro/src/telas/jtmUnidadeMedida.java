/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Unidademedida;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morgana
 */
public class jtmUnidadeMedida extends AbstractTableModel {
    private ArrayList<Unidademedida> unidades;
    private String[] colunas = {"Código", "Unidade", "Descrição"};

    public jtmUnidadeMedida(ArrayList<Unidademedida> unidades) {
        this.unidades = unidades;
    }

    public ArrayList<Unidademedida> getUnidades() {
        return unidades;
    }

    public void setUnidades(ArrayList<Unidademedida> unidades) {
        this.unidades = unidades;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Unidademedida unidade) {
        this.unidades.add(unidade);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.unidades.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return unidades.get(linha).getId();
            case 1:
                return unidades.get(linha).getUnidade(); 
            case 2:
                return unidades.get(linha).getDescricao();          
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Unidademedida get(int linha){
        return this.unidades.get(linha);
    }
    
    public void removeRow(int linha){
        this.unidades.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Produto;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morgana
 */
public class jtmProduto extends AbstractTableModel {
    private ArrayList<Produto> produtos;
    private String[] colunas = {"Código", "Referência", "Descrição", "Valor Compra", "Valor Venda", "Unidade de Medida", "Estoque"};

    public jtmProduto(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Produto> getUnidades() {
        return this.produtos;
    }

    public void setUnidades(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Produto produto) {
        this.produtos.add(produto);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.produtos.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return produtos.get(linha).getId();
            case 1:
                return produtos.get(linha).getCodigo();
            case 2:
                return produtos.get(linha).getDescricao(); 
            case 3:
                return produtos.get(linha).getValorcompra(); 
            case 4: 
                return produtos.get(linha).getValorvenda();
            case 5:
                return produtos.get(linha).getUnidademedida(); 
            case 6: 
                return produtos.get(linha).getQuantidadeestoque();
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Produto get(int linha){
        return this.produtos.get(linha);
    }
    
    public void removeRow(int linha){
        this.produtos.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Itemvenda;
import entidade.Produto;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morgana
 */
public class jtmItensVenda extends AbstractTableModel {
	
    private ArrayList<Itemvenda> itens;
    private String[] colunas = {"Código", "Referência", "Descrição", "Quantidade", "Preço", "Subtotal"};

    public jtmItensVenda(ArrayList<Itemvenda> itens) {
        this.itens = itens;
    }

    public ArrayList<Itemvenda> getItens() {
        return this.itens;
    }

    public void setItens(ArrayList<Itemvenda> itens) {
        this.itens = itens;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Itemvenda produto) {
        this.itens.add(produto);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.itens.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return itens.get(linha).getItemvendaPK().getProduto().getId();
            case 1:
                return itens.get(linha).getItemvendaPK().getProduto().getCodigo(); 
            case 2:
                return itens.get(linha).getItemvendaPK().getProduto().getValorvenda(); 
            case 3: 
                return itens.get(linha).getQuantidade();
            case 4:
                return itens.get(linha).getValor(); 
            case 5: 
                return itens.get(linha).getValortotal();
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Itemvenda get(int linha){
        return this.itens.get(linha);
    }
    
    public void removeRow(int linha){
        this.itens.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}
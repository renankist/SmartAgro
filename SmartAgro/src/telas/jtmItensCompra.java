/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Itemcompra;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morgana
 */
public class jtmItensCompra extends AbstractTableModel {
	
    private ArrayList<Itemcompra> itens;
    private String[] colunas = {"ID", "Código", "Descrição", "Quantidade", "Valor", "Desconto", "Subtotal"};

    public jtmItensCompra(ArrayList<Itemcompra> itens) {
        this.itens = itens;
    }

    public ArrayList<Itemcompra> getItens() {
        return this.itens;
    }

    public void setItens(ArrayList<Itemcompra> itens) {
        this.itens = itens;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Itemcompra produto) {
        this.itens.add(produto);
        this.fireTableDataChanged();
    }
    
    public void setRow(Itemcompra produto, int linha) {
        this.itens.set(linha, produto);
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
                return itens.get(linha).getItemcompraPK().getProduto();
            case 1:
                return itens.get(linha).getItemvendaPK().getProduto().getCodigo(); 
            case 2:
                return itens.get(linha).getItemvendaPK().getProduto().getDescricao(); 
            case 3: 
                return itens.get(linha).getQuantidade();
            case 4:
                return itens.get(linha).getValor(); 
            case 5:
                return itens.get(linha).getDesconto(); 
            case 6: 
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
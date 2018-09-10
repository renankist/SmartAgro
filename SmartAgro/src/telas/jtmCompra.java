/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Compra;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.Date;

public class jtmCompra extends AbstractTableModel {

    private ArrayList<Compra> compras;
    private String[] colunas = {"Compra", "Data emissão", "CPF/CNPJ", "Fornecedor", "Valor", "Desconto", "Valor total", "Status", "Paga", "Comprador"};
    final Class<?>[] columnClasses = {Integer.class, Date.class, String.class, String.class, BigDecimal.class, BigDecimal.class, BigDecimal.class, String.class, Boolean.class, String.class};

    public jtmCompra(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Compra compra) {
        this.compras.add(compra);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.compras.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return compras.get(linha).getId();
            case 1:
                return compras.get(linha).getData();
            case 2:
                String cpf_cnpj = "";
                if (compras.get(linha).getFornecedor().getCpf() != null) {
                    cpf_cnpj = compras.get(linha).getFornecedor().getCnpj();
                } else {
                    cpf_cnpj = compras.get(linha).getFornecedor().getCnpj();
                }
                return cpf_cnpj;
            case 3:
                return compras.get(linha).getFornecedor().getNome();
            case 4:
                return compras.get(linha).getValor();
            case 5:
                return compras.get(linha).getDesconto();
            case 6:
                return compras.get(linha).getValortotal();
            case 7:
                return Compra.getDescricaoStatus(compras.get(linha).getStatus());
            case 8:
                return (compras.get(linha).getPago() ? true : false);
            case 9:
                return compras.get(linha).getColaborador().getNomecompleto();
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }

    public Compra get(int linha) {
        return this.compras.get(linha);
    }

    public void removeRow(int linha) {
        this.compras.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
}

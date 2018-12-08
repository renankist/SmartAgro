/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Contapagar;
import entidade.Fornecedor;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Morgana
 */
public class jtmContasPagar extends AbstractTableModel {

    private ArrayList<Contapagar> contas;
    private String[] colunas = {"Código", "Compra", "Fornecedor", "Parcela", "Status", "Valor parcela", "Data vencimento", "Valor pago", "Data pagamento"};
    final Class<?>[] columnClasses = {Integer.class, Integer.class, String.class, Integer.class, String.class, BigDecimal.class, Date.class, BigDecimal.class, Date.class};

    public jtmContasPagar(ArrayList<Contapagar> contas) {
        this.contas = contas;
    }

    public ArrayList<Contapagar> getContapagars() {
        return contas;
    }

    public void setContapagars(ArrayList<Contapagar> contas) {
        this.contas = contas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Contapagar venda) {
        this.contas.add(venda);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.contas.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return contas.get(linha).getId();
            case 1:
                return contas.get(linha).getCompra().getId();
            case 2:
                return Fornecedor.getFornecedorToString(contas.get(linha).getCompra().getFornecedor());
            case 3:
                return contas.get(linha).getParcela();
            case 4:
                return Contapagar.getDescricaoStatus(contas.get(linha).getStatus());
            case 5:
                return contas.get(linha).getValordevido().setScale(2);
            case 6:
                return contas.get(linha).getVencimento();
            case 7:
                return contas.get(linha).getValorpago().setScale(2);
            case 8:
                return contas.get(linha).getDatapagamento();
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }

    public Contapagar get(int linha) {
        return this.contas.get(linha);
    }

    public void removeRow(int linha) {
        this.contas.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
}

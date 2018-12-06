/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Cliente;
import entidade.Contareceber;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Morgana
 */
public class jtmContasReceber extends AbstractTableModel {

    private ArrayList<Contareceber> contas;
    private String[] colunas = {"Código", "Venda", "Cliente", "Parcela", "Status", "Valor parcela", "Data vencimento", "Valor pago", "Data pagamento"};
    final Class<?>[] columnClasses = {Integer.class, Integer.class, String.class, Integer.class, String.class, BigDecimal.class, Date.class, BigDecimal.class, Date.class};

    public jtmContasReceber(ArrayList<Contareceber> contas) {
        this.contas = contas;
    }

    public ArrayList<Contareceber> getContarecebers() {
        return contas;
    }

    public void setContarecebers(ArrayList<Contareceber> contas) {
        this.contas = contas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Contareceber venda) {
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
                return contas.get(linha).getVenda().getId();
            case 2:
                return Cliente.getClienteToString(contas.get(linha).getVenda().getCliente());
            case 3:
                return contas.get(linha).getParcela();
            case 4:
                return Contareceber.getStatusToString(contas.get(linha).getStatus());
            case 5:
                return contas.get(linha).getValordevido();
            case 6:
                return contas.get(linha).getVencimento();
            case 7:
                return contas.get(linha).getValorpago();
            case 8:
                return contas.get(linha).getDatapagamento();
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }

    public Contareceber get(int linha) {
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

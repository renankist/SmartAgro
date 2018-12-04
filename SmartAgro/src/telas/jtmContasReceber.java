/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

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
    private String[] colunas = {"Código", "Venda", "Parcela", "Status", "Valor parcela", "Data vencimento", "Valor pago", "Data pagamento"};
    final Class<?>[] columnClasses = {Integer.class, Integer.class, Integer.class, String.class, BigDecimal.class, Date.class, BigDecimal.class, Date.class};

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
                return contas.get(linha).getDia();
            case 2:
                String cpf_cnpj = "";
                if (contas.get(linha).getCliente().getCpf() != null) {
                    cpf_cnpj = contas.get(linha).getCliente().getCpf();
                } else {
                    cpf_cnpj = contas.get(linha).getCliente().getCnpj();
                }
                return cpf_cnpj;
            case 3:
                return contas.get(linha).getCliente().getNome();
            case 4:
                return contas.get(linha).getValor();
            case 5:
                return contas.get(linha).getDesconto();
            case 6:
                return contas.get(linha).getValortotal();
            case 7:
                return Contareceber.getDescricaoStatus(contas.get(linha).getStatus());
            case 8:
                return (contas.get(linha).getPago() ? true : false);
            case 9:
                return contas.get(linha).getVendedor().getNomecompleto();
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

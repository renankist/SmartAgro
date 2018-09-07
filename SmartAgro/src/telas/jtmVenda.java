/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.Formatacao;
import entidade.Venda;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morgana
 */
public class jtmVenda extends AbstractTableModel {
    private ArrayList<Venda> vendas;
    private String[] colunas = {"Venda", "Data emissão", "CPF/CNPJ", "Cliente", "Valor", "Desconto", "Valor total", "Status", "Paga", "Vendedor"};

    public jtmVenda(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Venda venda) {
        this.vendas.add(venda);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.vendas.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return vendas.get(linha).getId();
            case 1:
                return Formatacao.ajustaDataDMA(vendas.get(linha).getDia().toString());
            case 2:
                String cpf_cnpj = "";
                if (vendas.get(linha).getCliente().getCpf() != null) {
                    cpf_cnpj = vendas.get(linha).getCliente().getCpf();
                } else {
                    cpf_cnpj = vendas.get(linha).getCliente().getCnpj();
                }
                return cpf_cnpj; 
            case 3:
                return vendas.get(linha).getCliente().getNome();  
            case 4:
                return vendas.get(linha).getValor();
            case 5:
                return vendas.get(linha).getDesconto();
            case 6:
                return vendas.get(linha).getValortotal();
            case 7:
                return Venda.getDescricaoStatus(vendas.get(linha).getStatus()); 
            case 8:
                return (vendas.get(linha).getPago() ? "Sim" : "Não"); 
            case 9:
                return vendas.get(linha).getVendedor().getNomecompleto();  
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Venda get(int linha){
        return this.vendas.get(linha);
    }
    
    public void removeRow(int linha){
        this.vendas.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}
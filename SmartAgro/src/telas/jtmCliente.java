/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Cliente;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morgana
 */
public class jtmCliente extends AbstractTableModel {
    private ArrayList<Cliente> clientes;
    private String[] colunas = {"Código", "CPF/CNPJ", "Nome", "Fone", "E-mail"};

    public jtmCliente(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Cliente cli) {
        this.clientes.add(cli);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.clientes.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return clientes.get(linha).getId();
            case 1:
                String cpf_cnpj = "";
                if (clientes.get(linha).getCpf() != null) {
                    cpf_cnpj = clientes.get(linha).getCpf();
                } else {
                    cpf_cnpj = clientes.get(linha).getCnpj();
                }
                return cpf_cnpj;
            case 2:
                return clientes.get(linha).getNome();  
            case 3:
                return clientes.get(linha).getCelular();  
            case 4:
                return clientes.get(linha).getEmail();  
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Cliente get(int linha){
        return this.clientes.get(linha);
    }
    
    public void removeRow(int linha){
        this.clientes.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}


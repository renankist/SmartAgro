/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Fornecedor;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morgana
 */
public class jtmFornecedor extends AbstractTableModel {
    private ArrayList<Fornecedor> fornecedores;
    private String[] colunas = {"Código", "CPF/CNPJ", "Nome"};

    public jtmFornecedor(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public ArrayList<Fornecedor> getUnidades() {
        return fornecedores;
    }

    public void setUnidades(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Fornecedor unidade) {
        this.fornecedores.add(unidade);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.fornecedores.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return fornecedores.get(linha).getId();
            case 1:
                String cpf_cnpj = "";
                if (fornecedores.get(linha).getCpf() != null) {
                    cpf_cnpj = fornecedores.get(linha).getCpf();
                } else {
                    cpf_cnpj = fornecedores.get(linha).getCnpj();
                }
                return cpf_cnpj;
            case 2:
                return fornecedores.get(linha).getNome();          
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Fornecedor get(int linha){
        return this.fornecedores.get(linha);
    }
    
    public void removeRow(int linha){
        this.fornecedores.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}


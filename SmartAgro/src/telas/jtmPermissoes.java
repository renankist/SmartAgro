/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Permissaoacesso;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morgana
 */
public class jtmPermissoes extends AbstractTableModel {

    private ArrayList<Permissaoacesso> permissoes;
    private String[] colunas = {"Usuário", "Módulo", "Operação", "Acesso"};

    public jtmPermissoes(ArrayList<Permissaoacesso> permissoes) {
        this.permissoes = permissoes;
    }

    public ArrayList<Permissaoacesso> getItens() {
        return this.permissoes;
    }

    public void setItens(ArrayList<Permissaoacesso> permissoes) {
        this.permissoes = permissoes;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Permissaoacesso permissao) {
        this.permissoes.add(permissao);
        this.fireTableDataChanged();
    }

    public void setRow(Permissaoacesso permissao, int linha) {
        this.permissoes.set(linha, permissao);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.permissoes.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return permissoes.get(linha).getPermissaoacessoPK().getUsuario().getNomecompleto();
            case 1:
                return permissoes.get(linha).getPermissaoacessoPK().getOperacao().getModulo().getDescricao();
            case 2:
                return permissoes.get(linha).getPermissaoacessoPK().getOperacao().getOperacao().getDescricao();
            case 3:
                return (permissoes.get(linha).getAcesso() ? "Sim" : "Não");
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }

    public Permissaoacesso get(int linha) {
        return this.permissoes.get(linha);
    }

    public void removeRow(int linha) {
        this.permissoes.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}
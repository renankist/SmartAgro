/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.Formatacao;
import auditoria.FormapagamentoAud;
import dao.AuditoriaDAO;
import entidade.Formapagamento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morgana
 */
public class jtmFormaPagamentoAud extends AbstractTableModel {
    private ArrayList<FormapagamentoAud> formas;
    private AuditoriaDAO<FormapagamentoAud> dao;
    private ArrayList<FormapagamentoAud> deletada;
    
    
    private String[] colunas = {"Código", "Data", "Usuário","IP","Ação","Forma de Pagamento"};

    public jtmFormaPagamentoAud(ArrayList<FormapagamentoAud> fornecedores) {
        this.formas = fornecedores;
    }

    public ArrayList<FormapagamentoAud> getFormas() {
        return formas;
    }

    public void setFormas(ArrayList<FormapagamentoAud> formas) {
        this.formas = formas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(FormapagamentoAud forma) {
        this.formas.add(forma);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.formas.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return formas.get(linha).getCustomRevInfo().getId();
            case 1:
                
                return  formas.get(linha).getCustomRevInfo().getHora();
                
            case 2:
                return formas.get(linha).getCustomRevInfo().getUsername();
            case 3:
                 return formas.get(linha).getCustomRevInfo().getIp();
            case 4:
                
                if(formas.get(linha).getRevtype() == 0){
                    return "Inseriu";
                }else if(formas.get(linha).getRevtype() == 1){
                     return "Alterou";       
                }else if(formas.get(linha).getRevtype() == 2){
                    return "Removeu";
                    
                 }
            case 5: 
                  if(formas.get(linha).getRevtype() == 2){
                      dao = new AuditoriaDAO<>();
                      deletada = new ArrayList(); 
                      deletada = dao.consultarPorIdVarios("FormapagamentoAud",formas.get(linha).getId());
                      
                      return deletada.get(deletada.size()-2).getDescricao();
                    
                  }else{
                    return formas.get(linha).getDescricao(); 
                  }
                
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public FormapagamentoAud get(int linha){
        return this.formas.get(linha);
    }
    
    public void removeRow(int linha){
        this.formas.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}


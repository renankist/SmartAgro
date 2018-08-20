
package telas;

import entidade.Colaborador;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class jtmColaborador extends AbstractTableModel {
    private ArrayList<Colaborador> colabs;
    private String[] colunas = {"Código", "Nome", "Celular", "E-mail"," Função", "Usuário", "Tipo Usuário", "Cidade/Estado"};

    public jtmColaborador(ArrayList<Colaborador> colaboradores) {
        this.colabs = colaboradores;
    }

    public ArrayList<Colaborador> getUnidades() {
        return colabs;
    }

    public void setUnidades(ArrayList<Colaborador> colaboradores) {
        this.colabs = colaboradores;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public void addRow(Colaborador unidade) {
        this.colabs.add(unidade);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.colabs.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return colabs.get(linha).getId();
            case 1:
                return colabs.get(linha).getNomecompleto();
            case 2:
                return colabs.get(linha).getCelular();
            case 3:
                return colabs.get(linha).getEmail();
            case 4: 
                return colabs.get(linha).getFuncao();
            case 5: 
                return colabs.get(linha).getUsuario();
            case 6: 
                String tmp = colabs.get(linha).getTipousuario()+"";
                
                if(tmp.equals("a")){
                    return "Administrador";
                }else{
                    return "Operador";
                }
            case 7: 
                return colabs.get(linha).getEndereco().getCidade().getNome()+" / "+colabs.get(linha).getEndereco().getCidade().getEstado().getSigla();
               
        }

        return null;
    }

    public String getColumnName(int columnIndex) {
        return this.colunas[columnIndex];
    }
    
    public Colaborador get(int linha){
        return this.colabs.get(linha);
    }
    
    public void removeRow(int linha){
        this.colabs.remove(linha);
        this.fireTableRowsUpdated(linha, linha);
    }

}


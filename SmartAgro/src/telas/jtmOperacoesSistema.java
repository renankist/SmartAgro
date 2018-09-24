/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import apoio.Mensagem;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.JXTreeTable;
import entidade.Permissaoacesso;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 *
 * @author morgana.elis
 */
public class jtmOperacoesSistema extends DefaultTreeTableModel {

    private final String[] columnNames = new String[]{
        "Módulo / Funcionalidades", "Acesso", "Teste"
    };

    private final Class[] columnClass = new Class[]{
        String.class, Boolean.class, Boolean.class
    };

//    public jtmOperacoesSistema(TreeTableNode root) {
//        super(root);
//    }
    public int getColumnCount() {
        return columnNames.length;
    }

    public Class<?> getColumnClass(int column) {
        return columnClass[column];
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (((DefaultMutableTreeTableNode) node).getUserObject() instanceof Permissaoacesso) {

            Permissaoacesso permissao = (Permissaoacesso) ((DefaultMutableTreeTableNode) node).getUserObject();

            if (permissao.getPermissaoacessoPK() == null) {
                return null;
            }

            switch (column) {
                case 0:
                    if (permissao.getOperacoesmodulo().getOperacao() == null) {
                        return permissao.getOperacoesmodulo().getModulo().getDescricao();
                    } else {
                        return permissao.getOperacoesmodulo().getOperacao().getDescricao();
                    }

                case 1:
                    return permissao.getAcesso();

                case 2:
                    return super.getValueAt(node, column);
            }
        }

        if (column == 0) {
            return super.getValueAt(node, column);
        }

        return null;
    }

    @Override
    public void setValueAt(Object value, Object node, int column) {

        Permissaoacesso permissao = (Permissaoacesso) ((DefaultMutableTreeTableNode) node).getUserObject();

        switch (column) {
            case 0:
                break;
            case 1:
                permissao.setAcesso((Boolean) value);
                break;
        }
        
        String  la = "Param: "+ String.valueOf(value) + " objeto: " + String.valueOf(permissao.getAcesso());
        Mensagem.mostraInformacao("oi, sou eu mesmo", la);

//        Employee row = employeeList.get(rowIndex);
//        if (0 == columnIndex) {
//            row.setId((Integer) aValue);
//        } else if (1 == columnIndex) {
//            row.setName((String) aValue);
//        } else if (2 == columnIndex) {
//            row.setHourlyRate((Double) aValue);
//        } else if (3 == columnIndex) {
//            row.setPartTime((Boolean) aValue);
//        }
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return (column == 1 || column == 2);
    }

    public static void criaTabela(JXTreeTable table, Object[][] data) {

        // Model
        DefaultTreeTableModel model = new jtmOperacoesSistema();
        table.setTreeTableModel(model);

        // Root node
        DefaultMutableTreeTableNode root = new DefaultMutableTreeTableNode("root");
        model.setRoot(root);

        // New nodes/rows
        for (int i = 0; i < data.length; i++) {

            if (data[i][0] == null) {
                break;
            }

            Permissaoacesso modulo = (Permissaoacesso) data[i][0];
            DefaultMutableTreeTableNode nodeModulo = new DefaultMutableTreeTableNode(modulo);
            model.insertNodeInto(nodeModulo, root, root.getChildCount());

            // Sub rows
            for (int j = 1; j < data[i].length; j++) {

                if (data[i][j] == null) {
                    break;
                }

                Permissaoacesso permissao = (Permissaoacesso) data[i][j];
                DefaultMutableTreeTableNode nodeOperacao = new DefaultMutableTreeTableNode(permissao);
                model.insertNodeInto(nodeOperacao, nodeModulo, nodeModulo.getChildCount());
            }
        }

        // Setting the cell editor
        DefaultCellEditor cellEditorCheck = new DefaultCellEditor(new JCheckBox());
        table.getColumn(2).setCellEditor(cellEditorCheck);
    }
}

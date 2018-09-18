/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.jdesktop.swingx.JXTreeTable;
import entidade.Permissaoacesso;

/**
 *
 * @author morgana.elis
 */
public class jtmOperacoesSistema extends DefaultTreeTableModel {

    public jtmOperacoesSistema(TreeTableNode root) {
        super(root);
    }

    public int getColumnCount() {
        return 2;
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return Boolean.class;
            default:
                return super.getColumnClass(column);
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Módulo / Funcionalidades";
            case 1:
                return "Acesso";
            default:
                return super.getColumnName(column);
        }
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
            }
        }

        if (column == 0) {
            return super.getValueAt(node, column);
        }
        return null;
    }
    
    public static void criaTabela(JXTreeTable table, Object[][] data) {
        DefaultMutableTreeTableNode nodoTabela = new DefaultMutableTreeTableNode("Sistema");
        DefaultTreeTableModel model = new jtmOperacoesSistema(nodoTabela);

        for (int i = 0; i < data.length; i++) {

            if (data[i][0] == null) {
                break;
            }

            Permissaoacesso modulo = (Permissaoacesso) data[i][0];
            DefaultMutableTreeTableNode part = new DefaultMutableTreeTableNode(modulo);
            model.insertNodeInto(part, nodoTabela, nodoTabela.getChildCount());

            for (int j = 1; j < data[i].length; j++) {

                if (data[i][j] == null) {
                    break;
                }

                Permissaoacesso permissao = (Permissaoacesso) data[i][j];
                DefaultMutableTreeTableNode nodoPermissao = new DefaultMutableTreeTableNode(permissao);
                model.insertNodeInto(nodoPermissao, part, part.getChildCount());
            }
        }

        table.setTreeTableModel(model);
    }
}
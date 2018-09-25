/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.JXTreeTable;
import entidade.Permissaoacesso;
import java.util.ArrayList;
import org.jdesktop.swingx.treetable.TreeTableNode;

/**
 *
 * @author morgana.elis
 */
public class jtmEstruturaPermissoes {

    DefaultTreeTableModel model;
    
    public void criaTabela(JXTreeTable table, Object[][] data) {

        // Model
        model = new ModelPermissoes();

        // Table
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
    }

    public ArrayList<Permissaoacesso> getPermissoes() {
        
        ArrayList<Permissaoacesso> permissoes = new ArrayList();

        // Get the root of the tree:
        TreeTableNode rootNode = model.getRoot();
        
        // then get the number of children of this root node:
        int cont = model.getChildCount(rootNode);

        // then go from 0 to the number of children and call to get the children of the root node.
        for (int i = 0; i < cont; i++) {
            
            DefaultMutableTreeTableNode node = (DefaultMutableTreeTableNode) model.getChild(rootNode, i);
            permissoes.addAll(retornaFilhos(node));

        }

        return permissoes;
    }
    
    private ArrayList<Permissaoacesso> retornaFilhos(DefaultMutableTreeTableNode node){
        
        ArrayList<Permissaoacesso> p = new ArrayList();
        
        int cont = model.getChildCount(node);
        
        for (int i = 0; i < cont; i++) {
            DefaultMutableTreeTableNode nodeFilho = (DefaultMutableTreeTableNode) model.getChild(node, i);

            if (nodeFilho.getUserObject() instanceof Permissaoacesso) {
                Permissaoacesso permissao = (Permissaoacesso) nodeFilho.getUserObject();
                p.add(permissao);
            }
            
             if (node.getChildCount() > 0) {
                 p.addAll(retornaFilhos(nodeFilho));
             }

        }
        
        return p;
    }

    /*
    *   TRABLE MODEL DA TREE TABLE
     */
    private static class ModelPermissoes extends DefaultTreeTableModel {

        private final String[] columnNames = new String[]{
            "Módulo / Funcionalidades", "Acesso"
        };

        private final Class[] columnClass = new Class[]{
            String.class, Boolean.class
        };

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
                case 1:
                    permissao.setAcesso((Boolean) value);
                    break;
            }

            String param = "Param: " + String.valueOf(value) + " - Objeto: " + String.valueOf(permissao.getAcesso());
            System.out.println(param);

        }

        @Override
        public boolean isCellEditable(Object node, int column) {
            return (column == 1);
        }
    }
}

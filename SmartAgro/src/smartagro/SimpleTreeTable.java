package smartagro;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.EventObject;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

public class SimpleTreeTable extends JPanel {

    private JTree tree;
    private DefaultMutableTreeNode root;
    private DefaultTreeCellEditor editor;

    public SimpleTreeTable() {
        super.setLayout(new GridLayout());
        root = new DefaultMutableTreeNode("Nodes");
        root.add(new MyResourceNode(new Resource("one", "first")));
        root.add(new MyResourceNode(new Resource("two", "first")));
        tree = new JTree(root);
        tree.setEditable(true);
        editor = new MyTreeCellEditor(tree, (DefaultTreeCellRenderer) tree.getCellRenderer());
        tree.setCellEditor(editor);
        this.add(new JScrollPane(tree));
    }

    private static class MyTreeCellEditor extends DefaultTreeCellEditor {

        public MyTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
            super(tree, renderer);
        }

        @Override
        public Component getTreeCellEditorComponent(JTree tree, Object value,
                boolean isSelected, boolean expanded, boolean leaf, int row) {
            if (value instanceof MyResourceNode) {
                value = ((MyResourceNode) value).getName();
            }
            return super.getTreeCellEditorComponent(tree, value, isSelected, expanded,
                    leaf, row);
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return super.isCellEditable(e)
                    && ((TreeNode) lastPath.getLastPathComponent()).isLeaf();
        }
    }

    public static class MyResourceNode extends DefaultMutableTreeNode {

        /**
         * @param resource
         */
        public MyResourceNode(Resource resource) {
            super(resource);
        }

        @Override
        public void setUserObject(Object userObject) {
            if (userObject instanceof String) {
                setName((String) userObject);
            } else if (userObject instanceof Resource) {
                super.setUserObject(userObject);
            }
        }

        public void setName(String name) {
            if (getUserObject() != null) {
                getUserObject().setName(name);
            }
        }

        public String getName() {
            if (getUserObject() != null) {
                return getUserObject().getName();
            }
            return null;
        }

        @Override
        public Resource getUserObject() {
            return (Resource) super.getUserObject();
        }

    }

    private static class Resource {

        String name;
        private String category;
        private boolean teste;

        public Resource(String name, String category) {
            this.name = name;
            this.category = category;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean isTeste() {
            return teste;
        }

        public void setTeste(boolean teste) {
            this.teste = teste;
        }
        
        @Override
        public String toString() {
            // BEWARE: don't do this in production code!
            return name + " (" + category + ")";
        }
    }

    private void display() {
        JFrame f = new JFrame("TreeEditorDemo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new SimpleTreeTable().display();
            }
        });
    }
}

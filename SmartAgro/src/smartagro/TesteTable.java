/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartagro;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import de.javasoft.swing.JYTable;
import de.javasoft.swing.JYTableHeader;
import de.javasoft.swing.JYTableScrollPane;
import de.javasoft.swing.jytable.renderer.CellLayoutHint;

/**
 * Demonstrates how to use a JYTable.
 */
@SuppressWarnings("serial")
public class TesteTable extends JFrame {

    public TesteTable() {

        super("Simple JYTable");
        createAndAddComponents(getContentPane());

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Create components and add them to the container.
     */
    private void createAndAddComponents(Container container) {
        String[] columnNames = {"First Name", "Last Name", "Sport", "# of Years", "Vegetarian"};
        Object[][] data = {
            {"Kathy", "Smith", "Snowboarding", 5, false},
            {"John", "Doe", "Rowing", 3, true},
            {"Sue", "Black", "Knitting", 2, false},
            {"Jane", "White", "Speed reading", 20, true},
            {"Joe", "Brown", "Pool", 10, false}
        };
        final Class<?>[] columnClasses = {String.class, String.class, String.class, Integer.class, Boolean.class};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClasses[columnIndex];
            }
        };

        JYTable table = new JYTable(model);
        JYTableHeader header = (JYTableHeader) table.getTableHeader();
        CellLayoutHint hint = header.getCellLayoutHint();
        //center header text
        header.setCellLayoutHint(new CellLayoutHint(hint.sortMarkerPosition, SwingConstants.CENTER, hint.verticalAlignment));
        //use a JYTableScrollPane for the filter row
        JYTableScrollPane scrollPane = new JYTableScrollPane(table);
        container.add(scrollPane);
    }

    /**
     * Static main method for application startup.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    javax.swing.UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel");
                    new TesteTable();
                } catch (Exception ex) {
                    System.out.println("fodeo");
                }
            }
        });
    }

}

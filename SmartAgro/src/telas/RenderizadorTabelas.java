/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author renan
 */
public class RenderizadorTabelas implements TableCellRenderer {

    private final JLabel componenteRenderizador;

    public RenderizadorTabelas() {

        componenteRenderizador = new JLabel();
        componenteRenderizador.setOpaque(true);

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object conteudo, boolean selecionada, boolean focada, int lin, int col) {
      
      
        componenteRenderizador.setHorizontalAlignment(getAlinhamento(col));

        return componenteRenderizador;
    }

    // escolhe o alinhamento a partir da coluna
    private int getAlinhamento(int coluna) {
        switch (coluna) {
            case 0:
                return SwingConstants.CENTER;

            case 1:
                return SwingConstants.CENTER;
            case 2:

            default:
                return SwingConstants.CENTER;
        }

    }

}

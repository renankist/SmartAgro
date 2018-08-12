/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Morgana
 */
public class DocumentoUpperCaseLimitado extends PlainDocument {

    private int tamanhoMax;

   
    public void setTamanho(int tamanhoMax){
        this.tamanhoMax = tamanhoMax;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {

        if (str == null) {
            return;
        }

        String stringAntiga = getText(0, getLength());
        int tamanhoNovo = stringAntiga.length() + str.length();

        // tamanhoMax == 0 : campo sem tamanho definido
        if (tamanhoNovo <= tamanhoMax || tamanhoMax == 0) {
            super.insertString(offset, str.toUpperCase(), attr);
        } else {
            super.insertString(offset, "", attr);
        }
    }
}

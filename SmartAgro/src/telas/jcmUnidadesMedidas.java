/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Unidademedida;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Renan Luis Kist
 */
public class jcmUnidadesMedidas extends AbstractListModel<Object> implements ComboBoxModel<Object> {
    
    private ArrayList<Unidademedida> medidas;
    private Unidademedida selectMedida;
    private final static int FIRSTINDEX = 0;
 
      public jcmUnidadesMedidas() {
        this.medidas = new ArrayList<Unidademedida>();
    }
    
    
    public jcmUnidadesMedidas(ArrayList<Unidademedida> unidades) {
        this();
         //Cria uma unidade medida padrão para ir no "Selecione"
        Unidademedida u = new Unidademedida(); 
        
        u.setDescricao("");
        u.setUnidade("Selecione");
        this.medidas.add(u); 
        
        this.medidas.addAll(unidades);
        
        if (getSize() > 0) {
            setSelectedItem(this.medidas.get(FIRSTINDEX));
        }
        
    }

   
    
 
     public void addUnidade(Unidademedida m) {
        medidas.add(m);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
        setSelectedItem(medidas.get(getSize() - 1));
    }
    
     public void addListUnidadeMedida(ArrayList<Unidademedida> u) {
        int primeiraLinha = getSize();
        medidas.addAll(u);
        fireIntervalAdded(this, primeiraLinha, primeiraLinha  + medidas.size());
        setSelectedItem(medidas.get(getSize() - 1));
    }
    
    
     public void removeUnidadeMedida() {
        this.medidas.remove(getSelectedItem());
        fireIntervalRemoved(this, FIRSTINDEX, getSize() - 1);
        setSelectedItem(this.medidas.get(FIRSTINDEX));
    }
    
     
     public void clear() {
        this.medidas.clear();
        fireContentsChanged(this, FIRSTINDEX, getSize() - 1);
    }
     
    @Override
    public int getSize() {
       return this.medidas.size();
    }

    @Override
    public Object getElementAt(int index) {
        return medidas.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
         selectMedida = (Unidademedida) anItem;
    }

    @Override
    public Object getSelectedItem() {
         return selectMedida;
    }
    
}

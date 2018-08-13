/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import entidade.Estado;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author morgana.elis
 */
public class EstadoComboModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private ArrayList<Estado> ufs;
    private Estado uf;
    private final static int FIRSTINDEX = 0;

    public EstadoComboModel() {
        this.ufs = new ArrayList<Estado>();
    }

    public EstadoComboModel(ArrayList<Estado> ufs) {
        this();
        
        //Cria um registro padrão para ir no "Selecione"
        Estado u = new Estado();

        u.setId(0);
        u.setSigla("");
        u.setNome("");
        this.ufs.add(u);

        this.ufs.addAll(ufs);

        if (getSize() > 0) {
            setSelectedItem(this.ufs.get(FIRSTINDEX));
        }

    }

    public void addEstado(Estado m) {
        ufs.add(m);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
        setSelectedItem(ufs.get(getSize() - 1));
    }

    public void addListEstado(ArrayList<Estado> u) {
        int primeiraLinha = getSize();
        ufs.addAll(u);
        fireIntervalAdded(this, primeiraLinha, primeiraLinha + ufs.size());
        setSelectedItem(ufs.get(getSize() - 1));
    }

    public void removeEstado() {
        this.ufs.remove(getSelectedItem());
        fireIntervalRemoved(this, FIRSTINDEX, getSize() - 1);
        setSelectedItem(this.ufs.get(FIRSTINDEX));
    }

    public void clear() {
        this.ufs.clear();
        fireContentsChanged(this, FIRSTINDEX, getSize() - 1);
    }

    @Override
    public int getSize() {
        return this.ufs.size();
    }

    @Override
    public Object getElementAt(int index) {
        return ufs.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        uf = (Estado) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return uf;
    }

}

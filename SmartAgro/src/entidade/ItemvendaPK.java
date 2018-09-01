/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Morgana
 */
@Embeddable
public class ItemvendaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "produto")
    private int produto;
    @Basic(optional = false)
    @Column(name = "venda")
    private int venda;

    public ItemvendaPK() {
    }

    public ItemvendaPK(int produto, int venda) {
        this.produto = produto;
        this.venda = venda;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public int getVenda() {
        return venda;
    }

    public void setVenda(int venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) produto;
        hash += (int) venda;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemvendaPK)) {
            return false;
        }
        ItemvendaPK other = (ItemvendaPK) object;
        if (this.produto != other.produto) {
            return false;
        }
        if (this.venda != other.venda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ItemvendaPK[ produto=" + produto + ", venda=" + venda + " ]";
    }
    
}

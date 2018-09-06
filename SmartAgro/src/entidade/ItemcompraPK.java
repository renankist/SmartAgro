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
 * @author rlkist
 */
@Embeddable
public class ItemcompraPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "produto")
    private int produto;
    @Basic(optional = false)
    @Column(name = "compra")
    private int compra;

    public ItemcompraPK() {
    }

    public ItemcompraPK(int produto, int compra) {
        this.produto = produto;
        this.compra = compra;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) produto;
        hash += (int) compra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemcompraPK)) {
            return false;
        }
        ItemcompraPK other = (ItemcompraPK) object;
        if (this.produto != other.produto) {
            return false;
        }
        if (this.compra != other.compra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ItemcompraPK[ produto=" + produto + ", compra=" + compra + " ]";
    }
    
}

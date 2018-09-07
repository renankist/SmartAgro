/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Morgana
 */
@Embeddable
public class ItemcompraPK implements Serializable {

    @JoinColumn(name = "produto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Produto produto;
    
    @JoinColumn(name = "venda", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Compra compra;

    public ItemcompraPK() {
    }

    public ItemcompraPK(Produto produto, Compra compra) {
        this.produto = produto;
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
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
        return "entidade.ItemvendaPK[ produto=" + produto.getDescricao() + ", venda=" + compra.getId() + " ]";
    }
    
}

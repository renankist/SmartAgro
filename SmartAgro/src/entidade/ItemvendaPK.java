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
import javax.persistence.CascadeType;

/**
 *
 * @author Morgana
 */
@Embeddable
public class ItemvendaPK implements Serializable {

    @JoinColumn(name = "produto", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Produto produto;
    
    @JoinColumn(name = "venda", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Venda venda;

    public ItemvendaPK() {
    }

    public ItemvendaPK(Produto produto, Venda venda) {
        this.produto = produto;
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
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
        return "entidade.ItemvendaPK[ produto=" + produto.getDescricao() + ", venda=" + venda.getId() + " ]";
    }
    
}

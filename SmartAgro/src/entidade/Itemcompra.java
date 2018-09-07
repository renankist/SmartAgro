/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author renan
 */
@Entity
@Table(name = "itemcompra")
@XmlRootElement
public class Itemcompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemcompraPK itemcompraPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "desconto")
    private BigDecimal desconto;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private BigDecimal quantidade;
    @Basic(optional = false)
    @Column(name = "valortotal")
    private BigDecimal valortotal;

    public Itemcompra() {
    }

    public Itemcompra(ItemcompraPK itemcompraPK) {
        this.itemcompraPK = itemcompraPK;
    }

    public Itemcompra(ItemcompraPK itemcompraPK, BigDecimal valor, BigDecimal quantidade, BigDecimal valortotal) {
        this.itemcompraPK = itemcompraPK;
        this.valor = valor;
        this.quantidade = quantidade;
        this.valortotal = valortotal;
    }

    public Itemcompra(Produto produto, Compra compra) {
        this.itemcompraPK = new ItemcompraPK(produto, compra);
    }

    public ItemcompraPK getItemcompraPK() {
        return itemcompraPK;
    }

    public void setItemcompraPK(ItemcompraPK itemcompraPK) {
        this.itemcompraPK = itemcompraPK;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValortotal() {
        return valortotal;
    }

    public void setValortotal(BigDecimal valortotal) {
        this.valortotal = valortotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemcompraPK != null ? itemcompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemcompra)) {
            return false;
        }
        Itemcompra other = (Itemcompra) object;
        if ((this.itemcompraPK == null && other.itemcompraPK != null) || (this.itemcompraPK != null && !this.itemcompraPK.equals(other.itemcompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Itemcompra[ itemcompraPK=" + itemcompraPK + " ]";
    }
    
}

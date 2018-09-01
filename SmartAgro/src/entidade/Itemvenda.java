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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Morgana
 */
@Entity
@Table(name = "itemvenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemvenda.findAll", query = "SELECT i FROM Itemvenda i")
    , @NamedQuery(name = "Itemvenda.findByProduto", query = "SELECT i FROM Itemvenda i WHERE i.itemvendaPK.produto = :produto")
    , @NamedQuery(name = "Itemvenda.findByVenda", query = "SELECT i FROM Itemvenda i WHERE i.itemvendaPK.venda = :venda")
    , @NamedQuery(name = "Itemvenda.findByValor", query = "SELECT i FROM Itemvenda i WHERE i.valor = :valor")
    , @NamedQuery(name = "Itemvenda.findByDesconto", query = "SELECT i FROM Itemvenda i WHERE i.desconto = :desconto")
    , @NamedQuery(name = "Itemvenda.findByQuantidade", query = "SELECT i FROM Itemvenda i WHERE i.quantidade = :quantidade")
    , @NamedQuery(name = "Itemvenda.findByValortotal", query = "SELECT i FROM Itemvenda i WHERE i.valortotal = :valortotal")})
public class Itemvenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemvendaPK itemvendaPK;
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
    @JoinColumn(name = "venda", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venda venda1;

    public Itemvenda() {
    }

    public Itemvenda(ItemvendaPK itemvendaPK) {
        this.itemvendaPK = itemvendaPK;
    }

    public Itemvenda(ItemvendaPK itemvendaPK, BigDecimal valor, BigDecimal quantidade, BigDecimal valortotal) {
        this.itemvendaPK = itemvendaPK;
        this.valor = valor;
        this.quantidade = quantidade;
        this.valortotal = valortotal;
    }

    public Itemvenda(int produto, int venda) {
        this.itemvendaPK = new ItemvendaPK(produto, venda);
    }

    public ItemvendaPK getItemvendaPK() {
        return itemvendaPK;
    }

    public void setItemvendaPK(ItemvendaPK itemvendaPK) {
        this.itemvendaPK = itemvendaPK;
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

    public Venda getVenda1() {
        return venda1;
    }

    public void setVenda1(Venda venda1) {
        this.venda1 = venda1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemvendaPK != null ? itemvendaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemvenda)) {
            return false;
        }
        Itemvenda other = (Itemvenda) object;
        if ((this.itemvendaPK == null && other.itemvendaPK != null) || (this.itemvendaPK != null && !this.itemvendaPK.equals(other.itemvendaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Itemvenda[ itemvendaPK=" + itemvendaPK + " ]";
    }
    
}

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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author Renan Luis Kist
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
     @Column(name = "codigo")
    private String codigo;
    @Column(name = "codigobarras")
    private String codigobarras;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valorcompra")
    private BigDecimal valorcompra;
    @Basic(optional = false)
    @Column(name = "valorvenda")
    private BigDecimal valorvenda;
    @Basic(optional = false)
    @Column(name = "quantidadeestoque")
    private BigDecimal quantidadeestoque;
    @Basic(optional = false)
    @JoinColumn(name = "unidademedida", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Unidademedida unidademedida;
    
    @Column(name = "imagem")
    private byte[] imagem;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, String descricao, BigDecimal valorcompra, BigDecimal valorvenda, BigDecimal quantidadeestoque) {
        this.id = id;
        this.descricao = descricao;
        this.valorcompra = valorcompra;
        this.valorvenda = valorvenda;
        this.quantidadeestoque = quantidadeestoque;
      
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigobarras() {
        return codigobarras;
    }

    public void setCodigobarras(String codigobarras) {
        this.codigobarras = codigobarras;
    }

    public BigDecimal getValorcompra() {
        return valorcompra;
    }

    public void setValorcompra(BigDecimal valorcompra) {
        this.valorcompra = valorcompra;
    }

    public BigDecimal getValorvenda() {
        return valorvenda;
    }

    public void setValorvenda(BigDecimal valorvenda) {
        this.valorvenda = valorvenda;
    }

    public BigDecimal getQuantidadeestoque() {
        return quantidadeestoque;
    }

    public void setQuantidadeestoque(BigDecimal quantidadeestoque) {
        this.quantidadeestoque = quantidadeestoque;
    }
    public Unidademedida getUnidademedida() {
        return unidademedida;
    }

    public void setUnidademedida(Unidademedida unidademedida) {
        this.unidademedida = unidademedida;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Produto[ id=" + id + " ]";
    }
    
}

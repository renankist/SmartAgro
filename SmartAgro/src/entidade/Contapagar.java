/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author morganabagatini
 */
@Entity
@Table(name = "contapagar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contapagar.findAll", query = "SELECT c FROM Contapagar c")
    , @NamedQuery(name = "Contapagar.findById", query = "SELECT c FROM Contapagar c WHERE c.id = :id")
    , @NamedQuery(name = "Contapagar.findByVencimento", query = "SELECT c FROM Contapagar c WHERE c.vencimento = :vencimento")
    , @NamedQuery(name = "Contapagar.findByValordevido", query = "SELECT c FROM Contapagar c WHERE c.valordevido = :valordevido")
    , @NamedQuery(name = "Contapagar.findByValorpago", query = "SELECT c FROM Contapagar c WHERE c.valorpago = :valorpago")
    , @NamedQuery(name = "Contapagar.findByDatapagamento", query = "SELECT c FROM Contapagar c WHERE c.datapagamento = :datapagamento")
    , @NamedQuery(name = "Contapagar.findByParcela", query = "SELECT c FROM Contapagar c WHERE c.parcela = :parcela")
    , @NamedQuery(name = "Contapagar.findByStatus", query = "SELECT c FROM Contapagar c WHERE c.status = :status")})
public class Contapagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "vencimento")
    @Temporal(TemporalType.DATE)
    private Date vencimento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valordevido")
    private BigDecimal valordevido;
    @Basic(optional = false)
    @Column(name = "valorpago")
    private BigDecimal valorpago;
    @Column(name = "datapagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datapagamento;
    @Basic(optional = false)
    @Column(name = "parcela")
    private int parcela;
    @Basic(optional = false)
    @Column(name = "status")
    private Character status;
    
    @JoinColumn(name = "compra", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Compra compra;
    
    // Status
    public static final char   STATUS_PENDENTE = 'A';
    public static final String STATUS_PENDENTE_DESCRICAO = "Pendente";
    public static final char   STATUS_PAGA = 'P';
    public static final String STATUS_PAGA_DESCRICAO = "Paga";

    public Contapagar() {
    }

    public Contapagar(Integer id) {
        this.id = id;
    }

    public Contapagar(Integer id, Date vencimento, BigDecimal valordevido, BigDecimal valorpago, int parcela, Character status) {
        this.id = id;
        this.vencimento = vencimento;
        this.valordevido = valordevido;
        this.valorpago = valorpago;
        this.parcela = parcela;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public BigDecimal getValordevido() {
        return valordevido;
    }

    public void setValordevido(BigDecimal valordevido) {
        this.valordevido = valordevido;
    }

    public BigDecimal getValorpago() {
        return valorpago;
    }

    public void setValorpago(BigDecimal valorpago) {
        this.valorpago = valorpago;
    }

    public Date getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contapagar)) {
            return false;
        }
        Contapagar other = (Contapagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Contapagar[ id=" + id + " ]";
    }
    
}

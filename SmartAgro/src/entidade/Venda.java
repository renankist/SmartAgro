/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

/**
 *
 * @author Morgana
 */
@Entity
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v")
    , @NamedQuery(name = "Venda.findById", query = "SELECT v FROM Venda v WHERE v.id = :id")
    , @NamedQuery(name = "Venda.findByDia", query = "SELECT v FROM Venda v WHERE v.dia = :dia")
    , @NamedQuery(name = "Venda.findByHora", query = "SELECT v FROM Venda v WHERE v.hora = :hora")
    , @NamedQuery(name = "Venda.findByValor", query = "SELECT v FROM Venda v WHERE v.valor = :valor")
    , @NamedQuery(name = "Venda.findByDesconto", query = "SELECT v FROM Venda v WHERE v.desconto = :desconto")
    , @NamedQuery(name = "Venda.findByDescricaodesconto", query = "SELECT v FROM Venda v WHERE v.descricaodesconto = :descricaodesconto")
    , @NamedQuery(name = "Venda.findByValortotal", query = "SELECT v FROM Venda v WHERE v.valortotal = :valortotal")
    , @NamedQuery(name = "Venda.findByObservacao", query = "SELECT v FROM Venda v WHERE v.observacao = :observacao")
    , @NamedQuery(name = "Venda.findByStatus", query = "SELECT v FROM Venda v WHERE v.status = :status")
    , @NamedQuery(name = "Venda.findByPago", query = "SELECT v FROM Venda v WHERE v.pago = :pago")})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dia")
    @Temporal(TemporalType.DATE)
    private Date dia;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "desconto")
    private BigDecimal desconto;
    @Column(name = "descricaodesconto")
    private String descricaodesconto;
    @Basic(optional = false)
    @Column(name = "valortotal")
    private BigDecimal valortotal;
    @Column(name = "observacao")
    private BigInteger observacao;
    @Basic(optional = false)
    @Column(name = "status")
    private Character status;
    @Basic(optional = false)
    @Column(name = "pago")
    private boolean pago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private Collection<Itemvenda> itemvendaCollection;
    
    // Status de uma venda
    public static final char STATUS_ORCAMENTO  = 'O'; 
    public static final char STATUS_CANCELADA  = 'C'; 
    public static final char STATUS_FINALIZADA = 'F'; 
    public static final char STATUS_PENDENTE   = 'P'; 
    

    public Venda() {
    }

    public Venda(Integer id) {
        this.id = id;
    }

    public Venda(Integer id, Date dia, Date hora, BigDecimal valor, BigDecimal valortotal, Character status, boolean pago) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.valor = valor;
        this.valortotal = valortotal;
        this.status = status;
        this.pago = pago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
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

    public String getDescricaodesconto() {
        return descricaodesconto;
    }

    public void setDescricaodesconto(String descricaodesconto) {
        this.descricaodesconto = descricaodesconto;
    }

    public BigDecimal getValortotal() {
        return valortotal;
    }

    public void setValortotal(BigDecimal valortotal) {
        this.valortotal = valortotal;
    }

    public BigInteger getObservacao() {
        return observacao;
    }

    public void setObservacao(BigInteger observacao) {
        this.observacao = observacao;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @XmlTransient
    public Collection<Itemvenda> getItemvendaCollection() {
        return itemvendaCollection;
    }

    public void setItemvendaCollection(Collection<Itemvenda> itemvendaCollection) {
        this.itemvendaCollection = itemvendaCollection;
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Venda[ id=" + id + " ]";
    }
    
    public String getDescricaoStatus(char status){
        String descr = "";
        
        switch (status){
            case STATUS_CANCELADA:
                descr = "Cancelada";
                break;
                
            case STATUS_FINALIZADA:
                descr = "Finalizada";
                break;
                
            case STATUS_PENDENTE:
                descr = "Pendente";
                break;
             
            case STATUS_ORCAMENTO:
                descr = "Orçamento";
                break;
        }
        
        return descr;
    }
    
    public ArrayList getTodosStatus(){
        ArrayList status = new ArrayList();
        
        status.add(getDescricaoStatus(STATUS_ORCAMENTO));
        status.add(getDescricaoStatus(STATUS_PENDENTE));
        status.add(getDescricaoStatus(STATUS_CANCELADA));
        status.add(getDescricaoStatus(STATUS_FINALIZADA));
        
        return status;
    }
    
}

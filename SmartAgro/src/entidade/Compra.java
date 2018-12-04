package entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "compra")
@XmlRootElement

public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
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
    private String observacao;
    @Basic(optional = false)
    @Column(name = "status")
    private Character status;
    @Basic(optional = false)
    @Column(name = "pago")
    private boolean pago;

    @JoinColumn(name = "fornecedor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;

    @JoinColumn(name = "colaborador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Colaborador colaborador;

    @JoinColumn(name = "formapagamento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Formapagamento formapagamento;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<Itemcompra> itemcompraCollection = new ArrayList<Itemcompra>();

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<Contapagar> contasCollection = new ArrayList<Contapagar>();

    // Status de uma compra
    public static final char STATUS_ORCAMENTO = 'O';
    public static final String STATUS_ORCAMENTO_DESCRICAO = "Orçamento";
    public static final char STATUS_CANCELADA = 'C';
    public static final String STATUS_CANCELADA_DESCRICAO = "Cancelada";
    public static final char STATUS_FINALIZADA = 'F';
    public static final String STATUS_FINALIZADA_DESCRICAO = "Finalizada";
    public static final char STATUS_PENDENTE = 'P';
    public static final String STATUS_PENDENTE_DESCRICAO = "Pendente";

    public Compra() {
    }

    public Compra(Integer id) {
        this.id = id;
    }

    public Compra(Integer id, Date data, Date hora, BigDecimal valor, BigDecimal valortotal, Character status, boolean pago) {
        this.id = id;
        this.data = data;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador Colaborador) {
        this.colaborador = Colaborador;
    }

    public Formapagamento getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(Formapagamento formapagamento) {
        this.formapagamento = formapagamento;
    }

    @XmlTransient
    public Collection<Itemcompra> getItemcompraCollection() {
        return itemcompraCollection;
    }

    public void setItemcompraCollection(Collection<Itemcompra> itemcompraCollection) {
        this.itemcompraCollection = itemcompraCollection;
    }

    public void addItemcompra(Itemcompra item) {
        item.setCompra(this);
        this.itemcompraCollection.add(item);
    }

    public void removeAllItemcompra() {
        this.itemcompraCollection.clear();
    }

    public Collection<Contapagar> getContasCollection() {
        return contasCollection;
    }

    public void setContasCollection(Collection<Contapagar> contasCollection) {
        this.contasCollection = contasCollection;
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
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Venda[ id=" + id + " ]";
    }

    public static String getDescricaoStatus(char status) {
        String descr = "";

        switch (status) {
            case STATUS_CANCELADA:
                descr = STATUS_CANCELADA_DESCRICAO;
                break;

            case STATUS_FINALIZADA:
                descr = STATUS_FINALIZADA_DESCRICAO;
                break;

            case STATUS_PENDENTE:
                descr = STATUS_PENDENTE_DESCRICAO;
                break;

            case STATUS_ORCAMENTO:
                descr = STATUS_ORCAMENTO_DESCRICAO;
                break;
        }

        return descr;
    }

    public static char getStatusPelaDescricao(String descr) {
        char status = STATUS_PENDENTE;

        switch (descr) {
            case STATUS_CANCELADA_DESCRICAO:
                status = STATUS_CANCELADA;
                break;

            case STATUS_FINALIZADA_DESCRICAO:
                status = STATUS_FINALIZADA;
                break;

            case STATUS_PENDENTE_DESCRICAO:
                status = STATUS_PENDENTE;
                break;

            case STATUS_ORCAMENTO_DESCRICAO:
                status = STATUS_ORCAMENTO;
                break;
        }

        return status;
    }

    public static ArrayList getTodosStatus() {
        ArrayList status = new ArrayList();

        status.add(getDescricaoStatus(STATUS_ORCAMENTO));
        status.add(getDescricaoStatus(STATUS_PENDENTE));
        status.add(getDescricaoStatus(STATUS_CANCELADA));
        status.add(getDescricaoStatus(STATUS_FINALIZADA));

        return status;
    }

}

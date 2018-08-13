/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Morgana
 */
@Entity
@Table(name = "endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e")
    , @NamedQuery(name = "Endereco.findById", query = "SELECT e FROM Endereco e WHERE e.id = :id")
    , @NamedQuery(name = "Endereco.findByRua", query = "SELECT e FROM Endereco e WHERE e.rua = :rua")
    , @NamedQuery(name = "Endereco.findByNumero", query = "SELECT e FROM Endereco e WHERE e.numero = :numero")
    , @NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro = :bairro")
    , @NamedQuery(name = "Endereco.findByCep", query = "SELECT e FROM Endereco e WHERE e.cep = :cep")
    , @NamedQuery(name = "Endereco.findByComplemento", query = "SELECT e FROM Endereco e WHERE e.complemento = :complemento")
    , @NamedQuery(name = "Endereco.findByAtivo", query = "SELECT e FROM Endereco e WHERE e.ativo = :ativo")})
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "rua")
    private String rua;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @Column(name = "cep")
    private String cep;
    @Column(name = "complemento")
    private String complemento;
    @Basic(optional = false)
    @Column(name = "ativo")
    private boolean ativo;
    @JoinColumn(name = "cidade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cidade cidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "endereco")
    private Collection<Fornecedor> fornecedorCollection;

    public Endereco() {
    }

    public Endereco(Integer id) {
        this.id = id;
    }

    public Endereco(Integer id, String rua, String numero, String bairro, String cep, boolean ativo) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @XmlTransient
    public Collection<Fornecedor> getFornecedorCollection() {
        return fornecedorCollection;
    }

    public void setFornecedorCollection(Collection<Fornecedor> fornecedorCollection) {
        this.fornecedorCollection = fornecedorCollection;
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
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Endereco[ id=" + id + " ]";
    }
    
}

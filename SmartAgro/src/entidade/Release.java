/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

/**
 *
 * @author morgana.elis
 */
@Entity
@Table(name = "release")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Release.findAll", query = "SELECT r FROM Release r")
    , @NamedQuery(name = "Release.findById", query = "SELECT r FROM Release r WHERE r.id = :id")
    , @NamedQuery(name = "Release.findByVersao", query = "SELECT r FROM Release r WHERE r.versao = :versao")
    , @NamedQuery(name = "Release.findByData", query = "SELECT r FROM Release r WHERE r.data = :data")
    , @NamedQuery(name = "Release.findByDescricao", query = "SELECT r FROM Release r WHERE r.descricao = :descricao")})
public class Release implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "versao")
    private String versao;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "descricao")
    private String descricao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "release", fetch = FetchType.EAGER)
    private Collection<Visualizacaorelease> visualizacaoreleaseCollection = new ArrayList<Visualizacaorelease>();

    public Release() {
    }

    public Release(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Visualizacaorelease> getVisualizacaoreleaseCollection() {
        return visualizacaoreleaseCollection;
    }

    public void setVisualizacaoreleaseCollection(Collection<Visualizacaorelease> visualizacaoreleaseCollection) {
        this.visualizacaoreleaseCollection = visualizacaoreleaseCollection;
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
        if (!(object instanceof Release)) {
            return false;
        }
        Release other = (Release) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Release[ id=" + id + " ]";
    }
    
}

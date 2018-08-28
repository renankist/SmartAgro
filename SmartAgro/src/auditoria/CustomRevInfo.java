/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Renan Luis Kist
 */
@Entity
@Table(name = "custom_rev_info")
@NamedQueries({
    @NamedQuery(name = "CustomRevInfo.findAll", query = "SELECT c FROM CustomRevInfo c")})
public class CustomRevInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "timestamp")
    private long timestamp;
    @Column(name = "username")
    private String username;
    @Column(name = "hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Column(name = "ip")
    private String ip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customRevInfo")
    private List<FormapagamentoAud> formapagamentoAudCollection;

    public CustomRevInfo() {
    }

    public CustomRevInfo(Integer id) {
        this.id = id;
    }

    public CustomRevInfo(Integer id, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<FormapagamentoAud> getFormapagamentoAudCollection() {
        return formapagamentoAudCollection;
    }

    public void setFormapagamentoAudCollection(List<FormapagamentoAud> formapagamentoAudCollection) {
        this.formapagamentoAudCollection = formapagamentoAudCollection;
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
        if (!(object instanceof CustomRevInfo)) {
            return false;
        }
        CustomRevInfo other = (CustomRevInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "auditoria.CustomRevInfo[ id=" + id + " ]";
    }
    
}

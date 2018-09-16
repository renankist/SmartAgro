/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author morganabagatini
 */
@Entity
@Table(name = "operacoesmodulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operacoesmodulo.findAll", query = "SELECT o FROM Operacoesmodulo o")
    , @NamedQuery(name = "Operacoesmodulo.findById", query = "SELECT o FROM Operacoesmodulo o WHERE o.id = :id")})
public class Operacoesmodulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @JoinColumn(name = "modulo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modulo modulo;
    
    @JoinColumn(name = "operacao", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Operacao operacao;
    
    public Operacoesmodulo() {
    }

    public Operacoesmodulo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
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
        if (!(object instanceof Operacoesmodulo)) {
            return false;
        }
        Operacoesmodulo other = (Operacoesmodulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Operacoesmodulo[ id=" + id + " ]";
    }
    
}

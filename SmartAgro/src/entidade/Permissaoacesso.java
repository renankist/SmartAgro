/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
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
 * @author morganabagatini
 */
@Entity
@Table(name = "permissaoacesso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissaoacesso.findAll", query = "SELECT p FROM Permissaoacesso p")
    , @NamedQuery(name = "Permissaoacesso.findByUsuario", query = "SELECT p FROM Permissaoacesso p WHERE p.permissaoacessoPK.usuario = :usuario")
    , @NamedQuery(name = "Permissaoacesso.findByOperacao", query = "SELECT p FROM Permissaoacesso p WHERE p.permissaoacessoPK.operacao = :operacao")
    , @NamedQuery(name = "Permissaoacesso.findByAcesso", query = "SELECT p FROM Permissaoacesso p WHERE p.acesso = :acesso")})
public class Permissaoacesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PermissaoacessoPK permissaoacessoPK;
    
    @Basic(optional = false)
    @Column(name = "acesso")
    private boolean acesso;
    
    @JoinColumn(name = "operacao", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Operacoesmodulo operacao;

    public Permissaoacesso() {
    }

    public Permissaoacesso(PermissaoacessoPK permissaoacessoPK) {
        this.permissaoacessoPK = permissaoacessoPK;
    }

    public Permissaoacesso(PermissaoacessoPK permissaoacessoPK, boolean acesso) {
        this.permissaoacessoPK = permissaoacessoPK;
        this.acesso = acesso;
    }

    public Permissaoacesso(Colaborador usuario, Operacoesmodulo operacao) {
        this.permissaoacessoPK = new PermissaoacessoPK(usuario, operacao);
    }

    public PermissaoacessoPK getPermissaoacessoPK() {
        return permissaoacessoPK;
    }

    public void setPermissaoacessoPK(PermissaoacessoPK permissaoacessoPK) {
        this.permissaoacessoPK = permissaoacessoPK;
    }

    public boolean getAcesso() {
        return acesso;
    }

    public void setAcesso(boolean acesso) {
        this.acesso = acesso;
    }

    public Operacoesmodulo getOperacoesmodulo() {
        return operacao;
    }

    public void setOperacoesmodulo(Operacoesmodulo operacao) {
        this.operacao = operacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissaoacessoPK != null ? permissaoacessoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissaoacesso)) {
            return false;
        }
        Permissaoacesso other = (Permissaoacesso) object;
        if ((this.permissaoacessoPK == null && other.permissaoacessoPK != null) || (this.permissaoacessoPK != null && !this.permissaoacessoPK.equals(other.permissaoacessoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Permissaoacesso[ permissaoacessoPK=" + permissaoacessoPK + " ]";
    }
    
}

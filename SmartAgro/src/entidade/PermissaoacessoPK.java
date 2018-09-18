/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author morganabagatini
 */
@Embeddable
public class PermissaoacessoPK implements Serializable {

    @JoinColumn(name = "usuario", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Colaborador usuario;
    
    @JoinColumn(name = "operacao", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Operacoesmodulo operacao;

    public PermissaoacessoPK() {
    }

    public PermissaoacessoPK(Colaborador usuario, Operacoesmodulo operacao) {
        this.usuario = usuario;
        this.operacao = operacao;
    }

    public Colaborador getUsuario() {
        return usuario;
    }

    public void setUsuario(Colaborador usuario) {
        this.usuario = usuario;
    }

    public Operacoesmodulo getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacoesmodulo operacao) {
        this.operacao = operacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissaoacessoPK)) {
            return false;
        }
        PermissaoacessoPK other = (PermissaoacessoPK) object;
        if (this.usuario != other.usuario) {
            return false;
        }
        if (this.operacao != other.operacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.PermissaoacessoPK[ usuario=" + usuario + ", operacao=" + operacao + " ]";
    }
    
}

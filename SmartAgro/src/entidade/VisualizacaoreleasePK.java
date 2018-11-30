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
 * @author morgana.elis
 */
@Embeddable
public class VisualizacaoreleasePK implements Serializable {
    
    @JoinColumn(name = "release", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Release release;
    
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Colaborador usuario;

    public VisualizacaoreleasePK() {
    }

    public VisualizacaoreleasePK(Release release, Colaborador usuario) {
        this.release = release;
        this.usuario = usuario;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Colaborador getUsuario() {
        return usuario;
    }

    public void setUsuario(Colaborador usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VisualizacaoreleasePK)) {
            return false;
        }
        VisualizacaoreleasePK other = (VisualizacaoreleasePK) object;
        if (this.release != other.release) {
            return false;
        }
        if (this.usuario != other.usuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.VisualizacaoreleasePK[ release=" + release + ", usuario=" + usuario + " ]";
    }
    
}

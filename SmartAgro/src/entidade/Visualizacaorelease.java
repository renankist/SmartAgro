/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
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
 * @author morgana.elis
 */
@Entity
@Table(name = "visualizacaorelease")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visualizacaorelease.findAll", query = "SELECT v FROM Visualizacaorelease v")
    , @NamedQuery(name = "Visualizacaorelease.findByRelease", query = "SELECT v FROM Visualizacaorelease v WHERE v.visualizacaoreleasePK.release = :release")
    , @NamedQuery(name = "Visualizacaorelease.findByUsuario", query = "SELECT v FROM Visualizacaorelease v WHERE v.visualizacaoreleasePK.usuario = :usuario")
    , @NamedQuery(name = "Visualizacaorelease.findByVisto", query = "SELECT v FROM Visualizacaorelease v WHERE v.visto = :visto")})
public class Visualizacaorelease implements Serializable {

    @EmbeddedId
    protected VisualizacaoreleasePK visualizacaoreleasePK;
    @Column(name = "visto")
    private Boolean visto;
    
    @JoinColumn(name = "release", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Release release;

    public Visualizacaorelease() {
    }

    public Visualizacaorelease(VisualizacaoreleasePK visualizacaoreleasePK) {
        this.visualizacaoreleasePK = visualizacaoreleasePK;
    }

    public Visualizacaorelease(Release release, Colaborador usuario) {
        this.visualizacaoreleasePK = new VisualizacaoreleasePK(release, usuario);
    }

    public VisualizacaoreleasePK getVisualizacaoreleasePK() {
        return visualizacaoreleasePK;
    }

    public void setVisualizacaoreleasePK(VisualizacaoreleasePK visualizacaoreleasePK) {
        this.visualizacaoreleasePK = visualizacaoreleasePK;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (visualizacaoreleasePK != null ? visualizacaoreleasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visualizacaorelease)) {
            return false;
        }
        Visualizacaorelease other = (Visualizacaorelease) object;
        if ((this.visualizacaoreleasePK == null && other.visualizacaoreleasePK != null) || (this.visualizacaoreleasePK != null && !this.visualizacaoreleasePK.equals(other.visualizacaoreleasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Visualizacaorelease[ visualizacaoreleasePK=" + visualizacaoreleasePK + " ]";
    }
    
}

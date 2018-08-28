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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;

/**
 *
 * @author Morgana
 */
@Entity
@Audited
@Table(name = "unidademedida")
@XmlRootElement
public class Unidademedida implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidademedida")
   

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "unidade")
    private String unidade;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    
    public Unidademedida() {
    }

    public Unidademedida(Integer id) {
        this.id = id;
    }

    public Unidademedida(Integer id, String unidade, String descricao) {
        this.id = id;
        this.unidade = unidade;
        this.descricao = descricao;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        if (!(object instanceof Unidademedida)) {
            return false;
        }
        Unidademedida other = (Unidademedida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(this.unidade.equals("Selecione")){
            return this.unidade;
        }else{
             return this.unidade+" - "+this.descricao;
        }
       
    }

   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Renan Luis Kist
 */
@Entity
@Table(name = "formapagamento_aud")
@NamedQueries({
    @NamedQuery(name = "FormapagamentoAud.findAll", query = "SELECT f FROM FormapagamentoAud f")})
public class FormapagamentoAud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }
    
    @Id
    private int rev; 
    
    @Column(name = "revtype")
    private Short revtype;
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "rev", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustomRevInfo customRevInfo;

    public FormapagamentoAud() {
    }

   

    public Short getRevtype() {
        return revtype;
    }

    public void setRevtype(Short revtype) {
        this.revtype = revtype;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CustomRevInfo getCustomRevInfo() {
        return customRevInfo;
    }

    public void setCustomRevInfo(CustomRevInfo customRevInfo) {
        this.customRevInfo = customRevInfo;
    }

    @Override
    public String toString() {
        return "auditoria.FormapagamentoAud[ formapagamentoAudPK=" + id + " ]";
    }
    
}

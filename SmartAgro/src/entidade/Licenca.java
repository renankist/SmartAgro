/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.Date;

/**
 *
 * @author renan
 */
public class Licenca {

    private String agropecuaria;
    private Date validade;
    private String tipo;

    public String getAgropecuaria() {
        return agropecuaria;
    }

    public void setAgropecuaria(String agropecuaria) {
        this.agropecuaria = agropecuaria;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}

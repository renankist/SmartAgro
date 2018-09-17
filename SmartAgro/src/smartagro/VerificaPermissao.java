/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartagro;

import apoio.HabilitaCampos;
import apoio.Mensagem;
import dao.PermissoesDAO;
import entidade.Colaborador;
import entidade.Permissaoacesso;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import telas.jfrLogin;

/**
 *
 * @author Morgana
 */
public class VerificaPermissao {
    
    String modulo;
    Container container;
    

    public VerificaPermissao(String modulo, Container container) {
        
        this.modulo = modulo;
        this.container = container;

        ajustaInterfacePermissao();

    }

    public void ajustaInterfacePermissao() {

        Colaborador usuario = jfrLogin.getUsuarioLogado();

        ArrayList<Permissaoacesso> permissoes = new PermissoesDAO().consultarPermissoesUsuarioModulo(usuario, modulo);

        if (permissoes.size() == 0) {
            Mensagem.mostraAletra("Permissões", "Usuário não possui nenhuma permissão cadastrada");
            return;
        }

        List<Component> componentes = getAllComponents(container);

        for (Component c : componentes) {
            for (Permissaoacesso p : permissoes) {
                if (p.getOperacoesmodulo().getOperacao().getNome().equals(c.getName())) {
                    HabilitaCampos.habilitaCampo(c, p.getAcesso());
                }
            }
        }

    }

    public static List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<Component>();

        for (Component comp : comps) {

            if (comp instanceof JButton) {
                compList.add(comp);
            }

            if (comp instanceof Container) {
                compList.addAll(getAllComponents((Container) comp));
            }
        }

        return compList;
    }

}

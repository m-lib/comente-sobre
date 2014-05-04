/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.sessao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.comentesobre.modelo.Topico;
import java.io.Serializable;

/**
 *
 * @author matheus
 */
@Component
@SessionScoped
public class Sessao implements Serializable {
    
    private Topico topico;

    public Sessao() {
        
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }
    
}
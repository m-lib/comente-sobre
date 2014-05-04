/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.interceptor;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.comentesobre.controller.TopicoController;
import br.com.comentesobre.sessao.Sessao;

/**
 *
 * @author matheus
 */
@Intercepts
public class TopicoInterceptor implements Interceptor {
    
    private Result resultado;
    private Sessao sessao;

    public TopicoInterceptor(Result resultado, Sessao sessao) {
        this.resultado = resultado;
        this.sessao = sessao;
    }

    public void intercept(InterceptorStack stack, ResourceMethod method, Object object) throws InterceptionException {
        resultado.redirectTo(TopicoController.class).index();
    }

    public boolean accepts(ResourceMethod method) {
        return sessao.getTopico() == null && method.containsAnnotation(Restrict.class);
    }
    
}
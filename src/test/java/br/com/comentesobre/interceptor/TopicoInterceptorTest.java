/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.interceptor;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.comentesobre.controller.TopicoController;
import br.com.comentesobre.modelo.Topico;
import br.com.comentesobre.sessao.Sessao;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

/**
 *
 * @author matheus
 */
public class TopicoInterceptorTest {

    @Mock private Topico mockTopico;
    @Mock private Sessao mockSessao;
    @Mock private Result mockResultado;
    @Mock private ResourceMethod mockMetodo;
    @Mock private TopicoController mockTopicoController;
    
    private TopicoInterceptor topicoInterceptor;

    public TopicoInterceptorTest() {
        initMocks(TopicoInterceptorTest.this);
        topicoInterceptor = new TopicoInterceptor(mockResultado, mockSessao);
    }

    @Test
    public void deveInterceptarARequisicaoERedirecionarParaAPaginaCerta() {
        when(mockResultado.redirectTo(TopicoController.class)).thenReturn(mockTopicoController);
        when(mockSessao.getTopico()).thenReturn(null);
        when(mockMetodo.containsAnnotation(Restrict.class)).thenReturn(true);
        
        boolean verdade = topicoInterceptor.accepts(mockMetodo);

        topicoInterceptor.intercept(null, null, null);

        assertTrue(verdade);
        verify(mockResultado).redirectTo(TopicoController.class);
    }

    @Test
    public void naoDeveInterceptarAChamada() {
        when(mockMetodo.containsAnnotation(Restrict.class)).thenReturn(true);
        when(mockSessao.getTopico()).thenReturn(mockTopico);

        boolean mentira = topicoInterceptor.accepts(mockMetodo);

        assertFalse(mentira);
    }
    
}
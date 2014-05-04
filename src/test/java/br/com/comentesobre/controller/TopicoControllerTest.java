/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.controller;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.comentesobre.dao.ComentarioDao;
import br.com.comentesobre.dao.TopicoDao;
import br.com.comentesobre.dao.UsuarioDao;
import br.com.comentesobre.modelo.Comentario;
import br.com.comentesobre.modelo.Topico;
import br.com.comentesobre.modelo.Usuario;
import br.com.comentesobre.sessao.Sessao;
import javax.persistence.NoResultException;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

/**
 *
 * @author matheus
 */
public class TopicoControllerTest {
    
    @Mock private Sessao mockSessao;
    @Mock private Result mockResultado;
    @Mock private Validator mockValidator;
    @Mock private TopicoDao mockTopicoDao;
    @Mock private UsuarioDao mockUsuarioDao;
    @Mock private ComentarioDao mockComentarioDao;
    
    private TopicoController topicoController;
    private Topico topico;
    
    public TopicoControllerTest() {
        initMocks(TopicoControllerTest.this);
        
        topico = new Topico();
        topico.setUri("topico-de-teste");
        topico.setTitulo("tópico de teste");

        topicoController = new TopicoController(mockResultado, mockValidator,
                mockSessao, mockUsuarioDao, mockComentarioDao, mockTopicoDao);
        
        when(mockSessao.getTopico()).thenReturn(topico);
        when(mockResultado.redirectTo(topicoController)).thenReturn(topicoController);
        when(mockValidator.onErrorRedirectTo(topicoController)).thenReturn(topicoController);
    }
    
    @Test
    public void deveRedirecionarParaPaginaTopico() {
        topicoController.novoTopico(topico);
        verify(mockResultado).redirectTo(topicoController);
    }
    
    @Test
    public void deveValidarTituloTopicoVazio() {
        topicoController.novoTopico(new Topico());
        verify(mockValidator).onErrorRedirectTo(topicoController);
    }
    
    @Test
    public void deveRedirecionarParaPaginaNaoEncontrada() {
        topico = new Topico();
        topico.setUri("topico-de-teste");
        
        when(mockTopicoDao.buscarTopicoPorURI(topico.getUri())).thenThrow(new NoResultException());
        
        topicoController.topico(topico);
        verify(mockResultado).notFound();
    }
    
    @Test
    public void deveAdicionarTopicoNaSessao() {
        when(mockTopicoDao.buscarTopicoPorURI(topico.getUri())).thenReturn(topico);
        
        topicoController.topico(topico);
        verify(mockSessao).setTopico(topico);
    }
    
    @Test
    public void deveValidarComentarioSemAutorSemMensagem() {
        Comentario comentario = new Comentario();
        Usuario usuario = new Usuario();
        
        comentario.setAutor(usuario);
        comentario.setTopico(topico);
        
        topicoController.comentar(comentario);
        verify(mockValidator).onErrorRedirectTo(topicoController);
    }
    
    @Test
    public void deveRedirecionarAposInserirNovoComentario() {
        Comentario comentario = new Comentario();
        Usuario usuario = new Usuario();
        comentario.setMensagem("teste");
        usuario.setEmail("me@test.com");
        
        comentario.setAutor(usuario);
        comentario.setTopico(topico);
        
        topicoController.comentar(comentario);
        verify(mockResultado).redirectTo(topicoController);
    }
    
}
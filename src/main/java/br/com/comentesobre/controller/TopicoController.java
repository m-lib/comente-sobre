/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.comentesobre.dao.ComentarioDao;
import br.com.comentesobre.dao.TopicoDao;
import br.com.comentesobre.dao.UsuarioDao;
import br.com.comentesobre.interceptor.Restrict;
import br.com.comentesobre.modelo.Comentario;
import br.com.comentesobre.modelo.Topico;
import br.com.comentesobre.sessao.Sessao;
import javax.persistence.NoResultException;

/**
 *
 * @author matheus
 */
@Resource
public class TopicoController {
    
    private Sessao sessao;

    private Result resultado;
    private Validator validator;
    
    private TopicoDao topicoDao;
    private UsuarioDao usuarioDao;
    private ComentarioDao comentarioDao;

    public TopicoController(Result resultado, Validator validator, Sessao sessao,
            UsuarioDao usuarioDao, ComentarioDao comentarioDao, TopicoDao topicoDao) {
        
        this.sessao = sessao;
        
        this.resultado = resultado;
        this.validator = validator;
        
        this.topicoDao = topicoDao;
        this.usuarioDao = usuarioDao;
        this.comentarioDao = comentarioDao;
    }

    @Path("/")
    public void index() {
        resultado.include("topicos", topicoDao.buscarCincoUltimosTopicos());
    }

    @Post
    public void novoTopico(Topico topico) {
        if (topico.isTituloVazio()) {
            validator.add(new ValidationMessage("Para come&ccedil;ar, nos diga sobre o que voc&ecirc; gostaria de comentar", "erro"));
            validator.onErrorRedirectTo(this).index();
        } else {
            topico.setUri(topico.getTitulo());
            resultado.redirectTo(this).topico(topico);
        }
    }

    @Path("/{topico.uri}")
    public Topico topico(Topico topico) {
        try {
            topico = topicoDao.buscarTopicoPorURI(topico.getUri());
        } catch (NoResultException exception) {
            if (topico.getTitulo() == null) {
                resultado.notFound();
            }
        }

        sessao.setTopico(topico);
        return topico;
    }

    @Post
    @Restrict
    @Path("/{topico.uri}/comentar")
    public void comentar(Comentario comentario) {
        if (isComentarioValido(comentario)) {
            try {
                String email = comentario.getEmailAutor();
                comentario.setAutor(usuarioDao.buscarUsuarioPorEmail(email));
            } catch (NoResultException exception) { }

            Topico topico = sessao.getTopico();
            comentario.setTopico(topico);

            comentarioDao.persistir(comentario);
            resultado.redirectTo(this).topico(topico);
        } else {
            validator.onErrorRedirectTo(this).topico(sessao.getTopico());
        }
    }
    
    private Boolean isComentarioValido(Comentario comentario) {
        boolean isComentarioValido = true;
        
        if (!comentario.isEmailAutorValido()) {
            validator.add(new ValidationMessage("Preencha o campo <a HREF=#email>e-mail</a>", "erro"));
            isComentarioValido = false;
        }
        
        if (!comentario.isMensagemValida()) {
            validator.add(new ValidationMessage("Preencha o campo <a HREF=#comentario>coment&aacute;rio</a>", "erro"));
            isComentarioValido = false;
        }
        
        return isComentarioValido;
    }
    
}
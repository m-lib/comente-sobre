/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.dao;

import br.com.comentesobre.modelo.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author matheus
 */
public class UsuarioDaoTest {
    
    private EntityManager entityManager;
    private UsuarioDao usuarioDao;
    
    public UsuarioDaoTest() {
        entityManager = Persistence.createEntityManagerFactory("default").createEntityManager();
        usuarioDao = new UsuarioDao(entityManager);
    }
    
    @Before
    public void inicializar() {
        entityManager.getTransaction().begin();
    }
    
    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("pessoa@gmail.com");
        
        usuarioDao.persistir(usuario);
        
        Usuario usuarioEncontrado = usuarioDao.buscarUsuarioPorEmail("pessoa@gmail.com");
        assertEquals("pessoa@gmail.com", usuarioEncontrado.getEmail());
    }
    
    @After
    public void finalizar() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }
    
}
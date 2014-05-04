/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.modelo.Usuario;
import javax.persistence.EntityManager;

/**
 *
 * @author matheus
 */
@Component
public class UsuarioDao extends GenericDao<Usuario> {

    public UsuarioDao(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Usuario buscarUsuarioPorEmail(String email) {
        return (Usuario) criarQuery("SELECT u FROM Usuario u WHERE u.email = ?", email).getSingleResult();
    }
    
}
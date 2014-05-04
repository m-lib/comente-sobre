/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.modelo.Comentario;
import javax.persistence.EntityManager;

/**
 *
 * @author matheus
 */
@Component
public class ComentarioDao extends GenericDao<Comentario> {

    public ComentarioDao(EntityManager entityManager) {
        super(entityManager);
    }
    
}
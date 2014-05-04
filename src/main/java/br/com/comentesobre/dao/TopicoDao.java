/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.modelo.Topico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
@Component
public class TopicoDao extends GenericDao<Topico> {

    public TopicoDao(EntityManager entityManager) {
        super(entityManager);
    }
    
    public Topico buscarTopicoPorURI(String uri) throws NoResultException {
        Query query = criarQuery("SELECT t FROM Topico t WHERE t.uri = :uri");
        query.setParameter("uri", uri);
        
        return (Topico) query.getSingleResult();
    }
    
    public List<Topico> buscarCincoUltimosTopicos() {
        Query query = criarQuery("SELECT t FROM Topico t ORDER BY t.codigo DESC");
        query.setMaxResults(5);
        
        return query.getResultList();
    }
    
}
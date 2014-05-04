/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author matheus
 */
public class GenericDao<T> implements Dao<T> {
    
    private EntityManager entityManager;

    public GenericDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T persistir(T entidade) {
        return this.entityManager.merge(entidade);
    }
    
    public Query criarQuery(String SQL) {
        return this.entityManager.createQuery(SQL);
    }
    
    public Query criarQuery(String SQL, Object... parametro) {
        Query query = entityManager.createQuery(SQL);
        for (int i = 0; i < parametro.length; i++) {
            query.setParameter((i + 1), parametro[i]);
        } return query;
    }
    
}
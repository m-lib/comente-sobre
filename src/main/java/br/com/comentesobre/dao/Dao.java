/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.dao;

/**
 *
 * @author matheus
 */
public interface Dao<T> {
    
    public T persistir(T entidade);
    
}
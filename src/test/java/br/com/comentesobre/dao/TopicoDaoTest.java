/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.dao;

import br.com.comentesobre.modelo.Topico;
import java.util.ArrayList;
import java.util.List;
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
public class TopicoDaoTest {
    
    private TopicoDao topicoDao;
    private EntityManager entityManager;
    
    private Topico topico;
    private List<Topico> topicos;
    
    public TopicoDaoTest() {
        entityManager = Persistence.createEntityManagerFactory("default").createEntityManager();
        topicoDao = new TopicoDao(entityManager);
        
        topico = new Topico();
        topico.setUri("topico-de-teste");
        topico.setTitulo("tópico de teste");
    }
    
    @Before
    public void inicializar() {
        entityManager.getTransaction().begin();
    }
    
    @Test
    public void deveBuscarUmTopicoPorURI() {
        topicoDao.persistir(topico);
        
        try {
            topico = topicoDao.buscarTopicoPorURI(topico.getUri());
        } catch (Exception exception) {
            topico = new Topico();
        }
        
        assertEquals("topico-de-teste", topico.getUri());
        
    }
    
    @Test
    public void deveBuscarCincoUltimosTopicos() {
        Topico topicoDois = new Topico();
        topicoDois.setUri("topico-dois");
        topicoDois.setTitulo("tópico dois");
        
        Topico topicoTres = new Topico();
        topicoTres.setUri("topico-tres");
        topicoTres.setTitulo("tópico três");
        
        Topico topicoQuatro = new Topico();
        topicoQuatro.setUri("topico-quatro");
        topicoQuatro.setTitulo("tópico quatro");
        
        Topico topicoCinco = new Topico();
        topicoCinco.setUri("topico-cinco");
        topicoCinco.setTitulo("tópico cinco");
        
        topicoDao.persistir(topico);
        topicoDao.persistir(topicoDois);
        topicoDao.persistir(topicoTres);
        topicoDao.persistir(topicoCinco);
        topicoDao.persistir(topicoQuatro);
        
        try {
            topicos = topicoDao.buscarCincoUltimosTopicos();
        } catch (Exception exception) {
            topicos = new ArrayList<Topico>();
        }
        
        assertEquals(5, topicos.size());
    }
    
    @After
    public void finalizar() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }
    
}
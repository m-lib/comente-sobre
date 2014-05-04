/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.comentesobre.modelo;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author matheus
 */
public class TopicoTest {
    
    private Topico topico;
    
    public TopicoTest() {
        topico = new Topico();
    }
    
    @Test
    public void deveRemoverEspacosAdjacentesDoTitulo() {
        String tituloEsperado = "teste de título com multiplos espaços";
        topico.setTitulo("   teste   de   título   com   multiplos espaços ");
        assertEquals(tituloEsperado, topico.getTitulo());
    }
    
    @Test
    public void deveFormatarURI() {
        String uriEsperada = "teste-de-uri-com-multiplos-espacos-e-acentos-graficos";
        topico.setUri("   Téstê   Dè   ÜRI   com   multiplõs espacos   e   acentos   gráficos ");
        assertEquals(uriEsperada, topico.getUri());
    }
    
    @Test
    public void deveRetornarTituloVazio() {
        assertTrue(topico.isTituloVazio());
        topico.setTitulo(null);
        assertTrue(topico.isTituloVazio());
    }
    
    @Test
    public void deveRetornarURIVazia() {
        assertTrue(topico.isURIVazia());
        topico.setUri(null);
        assertTrue(topico.isURIVazia());
    }

}
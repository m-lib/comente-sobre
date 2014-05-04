package br.com.comentesobre.modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comentario extends Entidade implements Serializable {

    private String mensagem;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario autor;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Topico topico;

    public Comentario() {
    }

    public Comentario(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }
    
    public String getEmailAutor() {
        return autor.getEmail();
    }
    
    public Boolean isEmailAutorValido() {
        return autor.isEmailValido();
    }
    
    public Boolean isMensagemValida() {
        return mensagem != null && !mensagem.isEmpty();
    }
    
}
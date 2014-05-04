package br.com.comentesobre.modelo;

import java.io.Serializable;
import java.text.Normalizer.Form;
import static java.text.Normalizer.normalize;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Topico extends Entidade implements Serializable {

    @Column(unique = true)
    private String uri;
    
    @Column(unique = true)
    private String titulo;
    
    @OneToMany(mappedBy = "topico")
    private List<Comentario> comentarios;

    public Topico() {
        
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        if (uri != null) {
            this.uri = normalize(uri.trim().toLowerCase(), Form.NFD)
                       .replaceAll("[^\\p{ASCII}]", "")
                       .replaceAll("\\s+", "-");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null) {
            this.titulo = titulo.trim().replaceAll("\\s+", " ");
        }
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    public Boolean isTituloVazio() {
        return titulo == null || titulo.isEmpty();
    }
    
    public Boolean isURIVazia() {
        return uri == null || uri.isEmpty();
    }
    
}
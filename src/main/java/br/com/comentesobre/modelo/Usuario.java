package br.com.comentesobre.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends Entidade implements Serializable {

    @Column(unique = true)
    private String email;

    public Usuario() {
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Boolean isEmailValido() {
        return email != null && !email.isEmpty();
    }
    
}
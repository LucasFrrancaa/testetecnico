package model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "Usuarios")
@Table(name = "usuarios")
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome não pode estar vazio")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull(message = "Email é obrigatório")
    @NotEmpty(message = "Email não pode estar vazio")
    @Email(message = "Email deve ter formato válido")
    @Column(nullable = false, length = 150, unique = true)
    private String email;

    public Usuarios(Long id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuarios(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

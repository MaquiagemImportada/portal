package br.com.maquiagemimportada.portal.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "perfil")
    private List<PerfilUsuario> usuarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PerfilUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<PerfilUsuario> usuarios) {
        this.usuarios = usuarios;
    }
}
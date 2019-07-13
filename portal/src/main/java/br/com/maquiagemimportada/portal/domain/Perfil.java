package br.com.maquiagemimportada.portal.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    
    private String descricao;
    
    private Boolean ativo;
    
    private Calendar dataCriacao;
    
    private Calendar dataModificacao;

    @ManyToMany
    @JoinTable(name="perfil_usuario", joinColumns=@JoinColumn(name="perfil_id"),inverseJoinColumns = @JoinColumn(name="usuario_id"))
    private List<Usuario> usuarios;
    
    @ManyToMany
    @JoinTable(name="perfil_permissao", joinColumns=@JoinColumn(name="perfil_id"),inverseJoinColumns = @JoinColumn(name="permissao_id"))
    private List<Permissao> permissoes;
    
    public Perfil() {
    	Calendar date = Calendar.getInstance();
    	
    	setAtivo(true);
    	setDataCriacao(date);
    	setDataModificacao(date);
    }

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

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Calendar getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Calendar dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
}
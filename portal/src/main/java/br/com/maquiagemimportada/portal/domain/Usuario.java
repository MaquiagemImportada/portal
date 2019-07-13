package br.com.maquiagemimportada.portal.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message="Username é obrigatório")
    @Column(unique=true)
    private String username;
    
    @NotBlank(message="Password é obrigatório")
    private String password;
    
    @NotBlank(message="Email é obrigatório")
    @Email(message="Email inválido")
    private String email;
    
    private Calendar dataCriacao;
    private Calendar dataModificacao;
    private Boolean ativo;
    private Boolean deletado;
    private String hash;
    private Boolean confirmado;

    @OneToOne(optional=false, mappedBy="usuario")
    private PessoaFisica pessoaFisica;

    @ManyToMany
    @JoinTable(name="perfil_usuario", joinColumns=@JoinColumn(name="usuario_id"),inverseJoinColumns = @JoinColumn(name="perfil_id"))
    private List<Perfil> perfis;

    public Usuario() {
    	Calendar date = Calendar.getInstance();
    	setDataCriacao(date);
    	setDataModificacao(date);
    	setAtivo(true);
    	setDeletado(false);
    	setConfirmado(false);
    	setHash();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	this.password = bCryptPasswordEncoder.encode(password);
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getDeletado() {
		return deletado;
	}

	public void setDeletado(Boolean deletado) {
		this.deletado = deletado;
	}

	public String getHash() {
		return hash;
	}
	
	public void setHash() {
		setHash(UUID.randomUUID().toString());
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}
}
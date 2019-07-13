package br.com.maquiagemimportada.portal.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.maquiagemimportada.portal.domain.Usuario;

public class CadastroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message="O email é obrigatório")
	@Email
	private String email;
	
	@NotBlank(message="A senha é obrigatória")
	@Size(min=8, message="A senha deve ter pelo menos 8 caracteres")
	private String senha;
	
	@NotBlank(message="O CPF é obrigatório")
	@Size(min=11, message="CPF inválido. Possui menos de 11 caracteres.")
	@Size(max=11, message="CPF inválido. Possui mais de 11 caracteres.")
	private String cpf;
	
	@NotBlank(message="O nome é obrigatório")
	@Size(min=4, message="O nome deve ter pelo menos 4 caracteres")
	private String nome;
	
	@NotBlank(message="A data de nascimento é obrigatória")
	private String dataNascimento;
	
	@NotBlank(message="O telefone é obrigatório")
	@Size(min=4, message="O telefone deve ter pelo menos 8 caracteres")
	private String telefone;
	
	private Boolean receberOfertas = true;
	
	private Usuario usuario;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Boolean getReceberOfertas() {
		return receberOfertas;
	}
	public void setReceberOfertas(Boolean receberOfertas) {
		this.receberOfertas = receberOfertas;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

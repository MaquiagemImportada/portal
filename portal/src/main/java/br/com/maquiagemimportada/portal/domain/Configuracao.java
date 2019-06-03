package br.com.maquiagemimportada.portal.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Configuracao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Boolean ativo;
	
	private Boolean deletado;
	
	@NotBlank(message="A Chave é obrigatória")
    @Column(unique=true)
	private String chave;
	
	private String valor;
	
	private String descricao;
	
	private Calendar dataCriacao;
	
	private Calendar dataModificacao;
	
	public Configuracao() {
		setDataCriacao(Calendar.getInstance());
		setDataModificacao(Calendar.getInstance());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getAtivo() {
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
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
}

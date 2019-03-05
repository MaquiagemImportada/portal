package br.com.maquiagemimportada.portal.domain;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ImagemProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String caminho;
	
	@ManyToOne
	private Produto produto;
	
	private Calendar dataCriacao;
	
	private Calendar dataModificacao;
	
	private Boolean destaque;

	@ManyToOne
	private ValorAtributo valorAtributo;
	
	public ImagemProduto() {
		setDataCriacao(Calendar.getInstance());
		setDataModificacao(Calendar.getInstance());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public Boolean getDestaque() {
		return destaque;
	}

	public void setDestaque(Boolean destaque) {
		this.destaque = destaque;
	}

	public ValorAtributo getValorAtributo() {
		return valorAtributo;
	}

	public void setValorAtributo(ValorAtributo valorAtributo) {
		this.valorAtributo = valorAtributo;
	}
	
	public String getNomeArquivo() {
		if(getCaminho() != null) {
			return new File(getCaminho()).getName();
		}else {
			return "";
		}
	}
}
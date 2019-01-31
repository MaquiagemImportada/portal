package br.com.maquiagemimportada.portal.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotBlank;

@Entity
public class ValorAtributo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Valor é obrigatório")
    @Size(max=150, message="O Valor do Atributo não pode ter mais que 150 caracteres")
	private String valor;
	
	@Min(value=0, message="A Quantidade não pode ser menor que zero")
	private Long quantidade;
	
	@ManyToOne
	private AtributoProduto atributo;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "valorAtributo")
	private List<ImagemProduto> imagens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public AtributoProduto getAtributo() {
		return atributo;
	}

	public void setAtributo(AtributoProduto atributo) {
		this.atributo = atributo;
	}

	public List<ImagemProduto> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemProduto> imagens) {
		this.imagens = imagens;
	}
}
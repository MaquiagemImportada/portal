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
import javax.validation.constraints.Size;

import javax.validation.constraints.NotBlank;

@Entity
public class AtributoProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="O Nome do Atributo é obrigatório")
	@Size(max=150, message="O Nome do Atributo deve ter no máximo 150 caracteres")
	private String nome;
	
	@ManyToOne
	private Produto produto;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "atributo")
	private List<ValorAtributo> valores;

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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<ValorAtributo> getValores() {
		return valores;
	}

	public void setValores(List<ValorAtributo> valores) {
		this.valores = valores;
	}
}
package br.com.maquiagemimportada.portal.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message="SKU é obrigatório")
    @Size(max=20, message="O SKU deve ter no máximo 20 caracteres")
    @Column(unique=true)
    private String sku;

    @NotBlank(message="Nome é obrigatório")
    @Size(max=150, message="O Nome deve ter no máximo 150 caracteres")
    private String nome;
    
    @NotBlank(message="Descrição é obrigatória")
    @Size(max=4000,message="A Descrição deve ter no máximo 4000 caracteres")
    private String descricao;

    @NotNull(message="Valor é obrigatório")
    @Min(value=0, message="O Valor não pode ser menor que zero")
    private BigDecimal valor;
    
    @Min(value=0, message="O Valor Promocional não pode ser menor que zero")
    private BigDecimal valorPromocional;
    
    @ManyToOne
    private CategoriaProduto categoria;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "produto")
    private List<ItemCarrinho> itensCarrinho;
    
    /**
     * Peso em gramas(g).
     */
    @NotNull(message="Peso é obrigatório")
    @Min(value=0, message="O Peso não pode ser menor que zero")
    private BigDecimal peso;
    
    /**
     * Altura em centímetros(cm)
     */
    @NotNull(message="Altura é obrigatório")
    @Min(value=0, message="A Altura não pode ser menor que zero")
    private BigDecimal altura;
    
    /**
     * Largura em centímetros(cm)
     */
    @NotNull(message="Largura é obrigatório")
    @Min(value=0, message="A Largura não pode ser menor que zero")
    private BigDecimal largura;
    
    /**
     * Profundidade em centímetros(cm)
     */
    @NotNull(message="Profundidade é obrigatório")
    @Min(value=0, message="A Profundidade não pode ser menor que zero")
    private BigDecimal profundidade;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<ImagemProduto> imagens;
    
    @Min(value=0, message="A Quantidade em Estoque não pode ser menor que zero")
    private Long quantidadeEstoque;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<AtributoProduto> atributos;
    
    private Boolean ativo;
    
    private Calendar dataCriacao;
    
    private Calendar dataModificacao;
    
    private Usuario autor;
    
    private Boolean deletado;
    
    @Transient
    private String imagensTemporarias;
    
    public Produto() {
    	setAtivo(true);
    	setDeletado(false);
    	setDataCriacao(Calendar.getInstance());
    	setDataModificacao(Calendar.getInstance());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorPromocional() {
		return valorPromocional;
	}

	public void setValorPromocional(BigDecimal valorPromocional) {
		this.valorPromocional = valorPromocional;
	}

	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	public BigDecimal getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(BigDecimal profundidade) {
		this.profundidade = profundidade;
	}

	public List<ImagemProduto> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemProduto> imagens) {
		this.imagens = imagens;
	}

	public Long getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Long quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public List<ItemCarrinho> getItensCarrinho() {
		return itensCarrinho;
	}

	public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}

	public List<AtributoProduto> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<AtributoProduto> atributos) {
		this.atributos = atributos;
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

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Boolean getDeletado() {
		return deletado;
	}

	public void setDeletado(Boolean deletado) {
		this.deletado = deletado;
	}

	public String getImagensTemporarias() {
		if(imagensTemporarias == null || "".equals(imagensTemporarias)) {
			if(getImagens() != null && getImagens().size() > 0) {
				StringBuilder sb = new StringBuilder("");
				
				for(ImagemProduto imagemProduto : getImagens()) {
					if(imagemProduto != null) {
						sb.append(imagemProduto.getNomeArquivo()+",");
					}
				}
				
				setImagensTemporarias(sb.toString());
			}
		}
		
		return imagensTemporarias;
	}

	public void setImagensTemporarias(String imagensTemporarias) {
		this.imagensTemporarias = imagensTemporarias;
	}
	
	public BigDecimal getPreco() {
		BigDecimal retorno;
		
		if(valorPromocional != null && valorPromocional.doubleValue() > 0D) {
			retorno = valorPromocional;
		}else {
			retorno = valor;
		}
		
		return retorno;
	}
}
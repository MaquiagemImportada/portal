package br.com.maquiagemimportada.portal.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

//@Entity
public class CupomDesconto implements Serializable {

	private static final long serialVersionUID = 1L;
	
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private String codigo;
    private Calendar dataCriacao;
    private Calendar dataModificacao;
    private Calendar inicioValidade;
    private Calendar fimValidade;
    private String tipoDesconto;
    private BigDecimal valor;
    private BigDecimal valorAPartirDe;
    private BigDecimal valorAte;
    private Long quantidadeAPartiDe;
    private Long quantidadeAte;
    private Long quantidadeUso;
    
    private Cliente cliente;
    
    private List<Produto> produtos;
    private List<CategoriaProduto> categorias;
    
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public Calendar getInicioValidade() {
		return inicioValidade;
	}
	public void setInicioValidade(Calendar inicioValidade) {
		this.inicioValidade = inicioValidade;
	}
	public Calendar getFimValidade() {
		return fimValidade;
	}
	public void setFimValidade(Calendar fimValidade) {
		this.fimValidade = fimValidade;
	}
	public String getTipoDesconto() {
		return tipoDesconto;
	}
	public void setTipoDesconto(String tipoDesconto) {
		this.tipoDesconto = tipoDesconto;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getValorAPartirDe() {
		return valorAPartirDe;
	}
	public void setValorAPartirDe(BigDecimal valorAPartirDe) {
		this.valorAPartirDe = valorAPartirDe;
	}
	public BigDecimal getValorAte() {
		return valorAte;
	}
	public void setValorAte(BigDecimal valorAte) {
		this.valorAte = valorAte;
	}
	public Long getQuantidadeAPartiDe() {
		return quantidadeAPartiDe;
	}
	public void setQuantidadeAPartiDe(Long quantidadeAPartiDe) {
		this.quantidadeAPartiDe = quantidadeAPartiDe;
	}
	public Long getQuantidadeAte() {
		return quantidadeAte;
	}
	public void setQuantidadeAte(Long quantidadeAte) {
		this.quantidadeAte = quantidadeAte;
	}
	public Long getQuantidadeUso() {
		return quantidadeUso;
	}
	public void setQuantidadeUso(Long quantidadeUso) {
		this.quantidadeUso = quantidadeUso;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<CategoriaProduto> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<CategoriaProduto> categorias) {
		this.categorias = categorias;
	}
}
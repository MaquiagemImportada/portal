package br.com.maquiagemimportada.portal.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class CategoriaProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message="Nome é obrigatório")
    private String nome;
    
    private String descricao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private List<Produto> produtos;
    
    private Calendar dataCriacao;
    
    private Calendar dataModificacao;
    
    public CategoriaProduto() {
    	setDataCriacao(Calendar.getInstance());
    	setDataModificacao(Calendar.getInstance());
    }
    
    public CategoriaProduto(String nome, String descricao) {
    	setNome(nome);
    	setDescricao(descricao);
    	setDataCriacao(Calendar.getInstance());
    	setDataModificacao(Calendar.getInstance());
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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
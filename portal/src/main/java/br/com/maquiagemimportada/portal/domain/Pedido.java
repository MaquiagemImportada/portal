package br.com.maquiagemimportada.portal.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cliente cliente;

    private Calendar dataCriacao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Endereco enderecoEntrega;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private FormaPagamento formaPagamento;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private FormaEntrega formaEntrega;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Carrinho carrinho;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    private CupomDesconto cupomDesconto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public FormaEntrega getFormaEntrega() {
        return formaEntrega;
    }

    public void setFormaEntrega(FormaEntrega formaEntrega) {
        this.formaEntrega = formaEntrega;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

//    public CupomDesconto getCupomDesconto() {
//        return cupomDesconto;
//    }

//    public void setCupomDesconto(CupomDesconto cupomDesconto) {
//        this.cupomDesconto = cupomDesconto;
//    }
}
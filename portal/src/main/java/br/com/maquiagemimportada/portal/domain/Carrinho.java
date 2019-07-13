package br.com.maquiagemimportada.portal.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "carrinho")
    private List<ItemCarrinho> itens;
    
    private Calendar dataCriacao;
    
    private Pedido pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public String getNomeCliente() {
    	String nomeCliente = "";
    	
    	if(pedido != null && pedido.getCliente() != null) {
			if(pedido.getCliente().getPessoaJuridica() != null) {
				if(pedido.getCliente().getPessoaJuridica().getNomeFantasia() != null && !"".equals(pedido.getCliente().getPessoaJuridica().getNomeFantasia())) {
					nomeCliente = pedido.getCliente().getPessoaJuridica().getNomeFantasia();
				}else {
					nomeCliente = pedido.getCliente().getPessoaJuridica().getRazaoSocial();
				}
			}else if(pedido.getCliente().getPessoaFisica() != null) {
				nomeCliente = pedido.getCliente().getPessoaFisica().getNome();
			}
    	}
    	
    	return nomeCliente;
    }
    
    public BigDecimal getValor() {
    	BigDecimal valor = new BigDecimal(0);
    	
    	for(ItemCarrinho item : itens) {
    		if(item != null) {
    			if(item.getProduto().getValorPromocional() != null) {
    				valor.add(item.getProduto().getValorPromocional().multiply(new BigDecimal(item.getQuantidade())));
    			}else {
    				valor.add(item.getProduto().getValor().multiply(new BigDecimal(item.getQuantidade())));
    			}
    		}
    	}
    	
    	return valor;
    }
}
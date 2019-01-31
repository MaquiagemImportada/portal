package br.com.maquiagemimportada.portal.event;

import br.com.maquiagemimportada.portal.domain.Produto;

public class ProdutoImagemSalvarEvent {

	private Produto produto;
	
	public ProdutoImagemSalvarEvent(Produto produto) {
		setProduto(produto);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
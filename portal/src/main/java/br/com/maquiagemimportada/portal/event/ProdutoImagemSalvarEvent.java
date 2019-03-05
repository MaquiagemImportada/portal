package br.com.maquiagemimportada.portal.event;

import br.com.maquiagemimportada.portal.domain.Produto;
import br.com.maquiagemimportada.portal.repository.ImagemProdutoRepository;

public class ProdutoImagemSalvarEvent {

	private ImagemProdutoRepository imagemProdutoRepository;
	
	private Produto produto;
	
	public ProdutoImagemSalvarEvent(Produto produto) {
		setProduto(produto);
	}
	
	public ProdutoImagemSalvarEvent(Produto produto, ImagemProdutoRepository imagemProdutoRepository) {
		setProduto(produto);
		setImagemProdutoRepository(imagemProdutoRepository);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ImagemProdutoRepository getImagemProdutoRepository() {
		return imagemProdutoRepository;
	}

	public void setImagemProdutoRepository(ImagemProdutoRepository imagemProdutoRepository) {
		this.imagemProdutoRepository = imagemProdutoRepository;
	}
}
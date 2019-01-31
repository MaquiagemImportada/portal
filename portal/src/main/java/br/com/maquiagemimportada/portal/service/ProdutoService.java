package br.com.maquiagemimportada.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maquiagemimportada.portal.domain.ImagemProduto;
import br.com.maquiagemimportada.portal.domain.Produto;
import br.com.maquiagemimportada.portal.event.ProdutoImagemSalvarEvent;
import br.com.maquiagemimportada.portal.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Produto produto) {
		produtoRepository.save(produto);
		
		publisher.publishEvent(new ProdutoImagemSalvarEvent(produto));
	}
	
	public List<ImagemProduto> buscarImagensProduto(String ids){
		List<ImagemProduto> retorno = new ArrayList<ImagemProduto>();
		
		//retorno.addAll(produtoRepository.)
		
		return retorno;
	}

	public ProdutoRepository getProdutoRepository() {
		return produtoRepository;
	}

	public void setProdutoRepository(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
}
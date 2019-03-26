package br.com.maquiagemimportada.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maquiagemimportada.portal.domain.ImagemProduto;
import br.com.maquiagemimportada.portal.domain.Produto;
import br.com.maquiagemimportada.portal.event.ProdutoImagemSalvarEvent;
import br.com.maquiagemimportada.portal.repository.ImagemProdutoRepository;
import br.com.maquiagemimportada.portal.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Transactional
	public void salvar(Produto produto) {
		if(produto != null && produto.getImagensTemporarias() != null && !"".equals(produto.getImagensTemporarias())) {
			if(produto.getImagensTemporarias().contains(",")) {
				String[] imgsTmps = produto.getImagensTemporarias().split(",");
				
				if(imgsTmps != null && imgsTmps.length > 0) {
					for(String imgTmp : imgsTmps) {
						ImagemProduto imgPrd = imagemProdutoRepository.findByCaminhoContaining(imgTmp);
						if(imgPrd != null) {
							try {
								if(produto.getImagens() == null) {
									produto.setImagens(new ArrayList<ImagemProduto>());
								}
								produto.getImagens().add(imgPrd);
							}catch(Exception e) {
								logger.error(e.getMessage());
							}
						}else {
							logger.error("Não foi possível encontrar a imagem de produto com o nome de arquivo: "+imgTmp);
						}
					}
				}
			}
		}
		
		publisher.publishEvent(new ProdutoImagemSalvarEvent(produtoRepository.save(produto), imagemProdutoRepository));
	}
	
	public List<ImagemProduto> buscarImagensProduto(String ids){
		List<ImagemProduto> retorno = new ArrayList<ImagemProduto>();
		
		//retorno.addAll(produtoRepository.)
		
		return retorno;
	}
	
	public List<Produto> listarAtivos(){
		List<Produto> lista = produtoRepository.findAllByAtivoAndDeletado(true,false);
		
		return lista;
	}
	
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
	
	public void apagar(Long id) {
		Produto produto = produtoRepository.getOne(id);
		produto.setDeletado(true);
		produtoRepository.save(produto);
	}

	public ProdutoRepository getProdutoRepository() {
		return produtoRepository;
	}

	public void setProdutoRepository(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public ImagemProdutoRepository getImagemProdutoRepository() {
		return imagemProdutoRepository;
	}

	public void setImagemProdutoRepository(ImagemProdutoRepository imagemProdutoRepository) {
		this.imagemProdutoRepository = imagemProdutoRepository;
	}
}
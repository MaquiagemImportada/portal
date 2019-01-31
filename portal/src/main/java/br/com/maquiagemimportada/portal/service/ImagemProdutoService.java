package br.com.maquiagemimportada.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maquiagemimportada.portal.domain.ImagemProduto;
import br.com.maquiagemimportada.portal.repository.ImagemProdutoRepository;

@Service
public class ImagemProdutoService {

	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;
	
	@Transactional
	public void salvar(ImagemProduto imagemProduto) {
		imagemProdutoRepository.save(imagemProduto);
	}

	public ImagemProdutoRepository getImagemProdutoRepository() {
		return imagemProdutoRepository;
	}

	public void setImagemProdutoRepository(ImagemProdutoRepository imagemProdutoRepository) {
		this.imagemProdutoRepository = imagemProdutoRepository;
	}
}
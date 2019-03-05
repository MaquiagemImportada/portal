package br.com.maquiagemimportada.portal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maquiagemimportada.portal.domain.CategoriaProduto;
import br.com.maquiagemimportada.portal.repository.CategoriaProdutoRepository;

@Service
public class CategoriaProdutoService {

	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;
	
	@Transactional
	public CategoriaProduto salvar(CategoriaProduto categoria) {
		return categoriaProdutoRepository.save(categoria);
	}
	
	public List<CategoriaProduto> listar(){
		return categoriaProdutoRepository.findAll();
	}

	public CategoriaProdutoRepository getCategoriaProdutoRepository() {
		return categoriaProdutoRepository;
	}

	public void setCategoriaProdutoRepository(CategoriaProdutoRepository categoriaProdutoRepository) {
		this.categoriaProdutoRepository = categoriaProdutoRepository;
	}
}

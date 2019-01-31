package br.com.maquiagemimportada.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.maquiagemimportada.portal.repository.ProdutoRepository;

@Controller
@RequestMapping("/vitrine")
public class VitrineController {

private static final Logger logger = LoggerFactory.getLogger(VitrineController.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RequestMapping
	public String listar() {
		logger.info("Listando Produto");
//		ModelAndView mv = new ModelAndView("index");
//		mv.addObject("produtos", produtoRepository.findAll());
		
		return "index";
	}

	public ProdutoRepository getProdutoRepository() {
		return produtoRepository;
	}

	public void setProdutoRepository(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
}

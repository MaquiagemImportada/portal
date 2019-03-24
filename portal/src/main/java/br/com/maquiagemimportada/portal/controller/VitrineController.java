package br.com.maquiagemimportada.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.maquiagemimportada.portal.service.ProdutoService;

@Controller
@RequestMapping("/")
public class VitrineController {

private static final Logger logger = LoggerFactory.getLogger(VitrineController.class);
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping
	public ModelAndView listar() {
		logger.info("Listando Produto");
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("produtos", produtoService.listarAtivos());
		
		return mv;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
}

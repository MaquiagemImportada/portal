package br.com.maquiagemimportada.portal.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.maquiagemimportada.portal.domain.Produto;
import br.com.maquiagemimportada.portal.repository.CategoriaProdutoRepository;
import br.com.maquiagemimportada.portal.repository.ProdutoRepository;
import br.com.maquiagemimportada.portal.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("produto/listar");
		mv.addObject("produtos", produtoRepository.findAll());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("======> Usuário logado: "+auth.getName());
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/cadastro");
		
		return mv;
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.POST)
	public ModelAndView novo(@Valid Produto produto, BindingResult result, Model model, RedirectAttributes attributes) {
		try {
			if(result.hasErrors()) {
				model.addAttribute("categorias", categoriaProdutoRepository.findAll());
				for(ObjectError error : result.getAllErrors()) {
					logger.info(error.getDefaultMessage());
				}
				
				return novo(produto);
			}
	
			produtoService.salvar(produto);
			attributes.addFlashAttribute("mensagem","Produto salvo com sucesso!");
			
		}catch(Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("mensagem","Ocorreu um erro ao tentar salvar o produto: "+e.getMessage());
		}
		return new ModelAndView("produto/listar");
	}

	public ProdutoRepository getProdutoRepository() {
		return produtoRepository;
	}

	public void setProdutoRepository(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public CategoriaProdutoRepository getCategoriaProdutoRepository() {
		return categoriaProdutoRepository;
	}

	public void setCategoriaProdutoRepository(CategoriaProdutoRepository categoriaProdutoRepository) {
		this.categoriaProdutoRepository = categoriaProdutoRepository;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
}
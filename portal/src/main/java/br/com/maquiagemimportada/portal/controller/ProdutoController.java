package br.com.maquiagemimportada.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.maquiagemimportada.portal.domain.CategoriaProduto;
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
		List<Produto> produtos = produtoService.listar();
		List<CategoriaProduto> categorias = categoriaProdutoRepository.findAll();
		if(produtos != null) {
			if(produtos.size() > 0) {
				logger.info("A lista de produtos veio com "+produtos.size()+" registros!!!!");
			}else {
				logger.info("A lista de produtos veio vazia!!!!");
			}
		}else {
			logger.info("A lista de produtos veio nula!!!!");
		}
		mv.addObject("produtos", produtos);
		mv.addObject("categorias", categorias);
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/cadastro");
		mv.addObject("categorias", categoriaProdutoRepository.findAll());
		
		return mv;
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.POST)
	public String novo(@Valid Produto produto, BindingResult result, Model model, RedirectAttributes attributes) {
		try {
			if(result.hasErrors()) {
				model.addAttribute("categorias", categoriaProdutoRepository.findAll());
				StringBuilder erros = new StringBuilder("");
				for(ObjectError error : result.getAllErrors()) {
					logger.info(error.getDefaultMessage());
					erros.append(error.getDefaultMessage()+"<br/>");
				}
				
				attributes.addAttribute("mensagem",erros.toString());
				return "produto/cadastro";
			}
	
			produtoService.salvar(produto);
			attributes.addFlashAttribute("mensagem","Produto salvo com sucesso!");
		
		}catch(Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("mensagem","Ocorreu um erro ao tentar salvar o produto: "+e.getMessage());
		}
		return "redirect:/produto";
	}
	
	@GetMapping("/apagar/{id}")
	public void apagar(@PathVariable Long id, HttpServletResponse response) {
		try {
			produtoService.apagar(id);
			response.setStatus(200);
		}catch(Exception e) {
			response.setStatus(500);
		}
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Produto produto = produtoRepository.getOne(id);
		ModelAndView mv = new ModelAndView("produto/cadastro");
		mv.addObject("produto",produto);
		mv.addObject("categorias", categoriaProdutoRepository.findAll());
		
		return mv;
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
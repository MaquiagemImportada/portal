package br.com.maquiagemimportada.portal.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.maquiagemimportada.portal.domain.CategoriaProduto;
import br.com.maquiagemimportada.portal.service.CategoriaProdutoService;

@Controller
@RequestMapping("/categoria")
public class CategoriaProdutoController {

	private static final Logger logger = LoggerFactory.getLogger(CategoriaProdutoController.class);
	
	@Autowired
	private CategoriaProdutoService categoriaProdutoService;
	
	@RequestMapping(value="/salvarModal", method=RequestMethod.POST)
	public ModelAndView salvarModal(@RequestParam("nome") String nome, @RequestParam("descricao") String descricao, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("produto/selectCategoria");
		
		try {
			categoriaProdutoService.salvar(new CategoriaProduto(nome, descricao));
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		mv.addObject("categorias", categoriaProdutoService.listar());
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(CategoriaProduto categoria) {
		ModelAndView mv = new ModelAndView("categoria/cadastro");
		mv.addObject("categoria",categoria);
		
		return mv;
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.POST)
	public String novo(@Valid CategoriaProduto categoria, BindingResult result, Model model, RedirectAttributes attributes) {
		try {
			if(result.hasErrors()) {
				StringBuilder erros = new StringBuilder("");
				for(ObjectError error : result.getAllErrors()) {
					logger.info(error.getDefaultMessage());
					erros.append(error.getDefaultMessage()+"<br/>");
				}
				
				attributes.addAttribute("mensagem",erros.toString());
				return "categoria/cadastro";
			}
	
			categoriaProdutoService.salvar(categoria);
			attributes.addFlashAttribute("mensagem","Categoria salva com sucesso!");
			
		}catch(Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("mensagem","Ocorreu um erro ao tentar salvar a categoria: "+e.getMessage());
		}
		
		return "redirect:/produto";
	}

	public CategoriaProdutoService getCategoriaProdutoService() {
		return categoriaProdutoService;
	}

	public void setCategoriaProdutoService(CategoriaProdutoService categoriaProdutoService) {
		this.categoriaProdutoService = categoriaProdutoService;
	}
	
}

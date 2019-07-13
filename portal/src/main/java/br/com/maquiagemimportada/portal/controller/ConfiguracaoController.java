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

import br.com.maquiagemimportada.portal.domain.Configuracao;
import br.com.maquiagemimportada.portal.service.ConfiguracaoService;

@Controller
@RequestMapping("/configuracao")
public class ConfiguracaoController {

	private static final Logger logger = LoggerFactory.getLogger(ConfiguracaoController.class);
	
	@Autowired
	private ConfiguracaoService configuracaoService;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv;
		
		try {
			mv = new ModelAndView("configuracao/listar");
			List<Configuracao> configuracoes = configuracaoService.listarNaoDeletados();
			mv.addObject("configuracoes", configuracoes);
		}catch(Exception e) {
			logger.error(e.getMessage());
			mv = new ModelAndView("index");
			mv.addObject("exception",e);
		}
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Configuracao configuracao) {
		ModelAndView mv = new ModelAndView("configuracao/cadastro");
		
		return mv;
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.POST)
	public String novo(@Valid Configuracao configuracao, BindingResult result, Model model, RedirectAttributes attributes) {
		try {
			if(result.hasErrors()) {
				StringBuilder erros = new StringBuilder("");
				for(ObjectError error : result.getAllErrors()) {
					logger.info(error.getDefaultMessage());
					erros.append(error.getDefaultMessage()+"<br/>");
				}
				
				attributes.addAttribute("mensagem",erros.toString());
				return "configuracao/cadastro";
			}
	
			configuracaoService.salvar(configuracao);
			attributes.addFlashAttribute("mensagem","Configuração salva com sucesso!");
		
		}catch(Exception e) {
			logger.error(e.getMessage());
			attributes.addFlashAttribute("mensagem","Ocorreu um erro ao tentar salvar a configuração: "+e.getMessage());
		}
		return "redirect:/configuracao";
	}
	
	@GetMapping("/apagar/{id}")
	public void apagar(@PathVariable Long id, HttpServletResponse response) {
		try {
			configuracaoService.apagar(id);
			response.setStatus(200);
		}catch(Exception e) {
			logger.error(e.getMessage());
			response.setStatus(500);
		}
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Configuracao configuracao = configuracaoService.getOne(id);
		ModelAndView mv = new ModelAndView("configuracao/cadastro");
		mv.addObject("configuracao",configuracao);
		
		return mv;
	}

	public ConfiguracaoService getConfiguracaoService() {
		return configuracaoService;
	}

	public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
		this.configuracaoService = configuracaoService;
	}
	
}

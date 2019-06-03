package br.com.maquiagemimportada.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
			List<Configuracao> configuracoes = configuracaoService.listarAtivos();
			mv.addObject("usuarios", configuracoes);
		}catch(Exception e) {
			logger.error(e.getMessage());
			mv = new ModelAndView("index");
			mv.addObject("exception",e);
		}
		
		return mv;
	}

	public ConfiguracaoService getConfiguracaoService() {
		return configuracaoService;
	}

	public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
		this.configuracaoService = configuracaoService;
	}
	
}

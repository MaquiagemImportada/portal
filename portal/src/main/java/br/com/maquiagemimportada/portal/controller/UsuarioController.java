package br.com.maquiagemimportada.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.maquiagemimportada.portal.domain.Usuario;
import br.com.maquiagemimportada.portal.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv;
		
		try {
			mv = new ModelAndView("usuario/listar");
			List<Usuario> usuarios = usuarioService.listarAtivos();
			mv.addObject("usuarios", usuarios);
		}catch(Exception e) {
			logger.error(e.getMessage());
			mv = new ModelAndView("index");
			mv.addObject("exception",e);
		}
		
		return mv;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
}

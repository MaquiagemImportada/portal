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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.maquiagemimportada.portal.domain.Usuario;
import br.com.maquiagemimportada.portal.service.PerfilService;
import br.com.maquiagemimportada.portal.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv;
		
		try {
			mv = new ModelAndView("usuario/listar");
			
			mv.addObject("usuarios", usuarioService.listar());
			mv.addObject("perfis",perfilService.listar());
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			mv = new ModelAndView("index");
			mv.addObject("exception",e);
		}
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/cadastro");
		mv.addObject("perfis", perfilService.listar());
		
		return mv;
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.POST)
	public String novo(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes) {
		try {
			if(result.hasErrors()) {
				model.addAttribute("permissoes", perfilService.listar());
				StringBuilder erros = new StringBuilder("");
				for(ObjectError error : result.getAllErrors()) {
					logger.info(error.getDefaultMessage());
					erros.append(error.getDefaultMessage()+"<br/>");
				}
				
				attributes.addAttribute("mensagem",erros.toString());
				return "usuario/cadastro";
			}
	
			usuarioService.salvar(usuario);
			attributes.addFlashAttribute("mensagem","Usuario salvo com sucesso!");
		
		}catch(Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("mensagem","Ocorreu um erro ao tentar salvar o usuário: "+e.getMessage());
		}
		return "redirect:/usuario";
	}
	
	@GetMapping("/apagar/{id}")
	public void apagar(@PathVariable Long id, HttpServletResponse response) {
		try {
			usuarioService.apagar(id);
			response.setStatus(200);
		}catch(Exception e) {
			logger.error(e.getMessage());
			response.setStatus(500);
		}
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("usuario/cadastro");
		mv.addObject("usuario",usuarioService.obter(id));
		mv.addObject("perfis", perfilService.listar());
		
		return mv;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public PerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}
}

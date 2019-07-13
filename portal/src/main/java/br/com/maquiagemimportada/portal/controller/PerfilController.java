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

import br.com.maquiagemimportada.portal.domain.Perfil;
import br.com.maquiagemimportada.portal.service.PerfilService;
import br.com.maquiagemimportada.portal.service.PermissaoService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	private static final Logger logger = LoggerFactory.getLogger(PerfilController.class);
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Perfil perfil) {
		ModelAndView mv = new ModelAndView("perfil/cadastro");
		mv.addObject("permissoes", permissaoService.listar());
		
		return mv;
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.POST)
	public String novo(@Valid Perfil perfil, BindingResult result, Model model, RedirectAttributes attributes) {
		try {
			if(result.hasErrors()) {
				model.addAttribute("permissoes", permissaoService.listar());
				StringBuilder erros = new StringBuilder("");
				for(ObjectError error : result.getAllErrors()) {
					logger.info(error.getDefaultMessage());
					erros.append(error.getDefaultMessage()+"<br/>");
				}
				
				attributes.addAttribute("mensagem",erros.toString());
				return "perfil/cadastro";
			}
	
			perfilService.salvar(perfil);
			attributes.addFlashAttribute("mensagem","Perfil salvo com sucesso!");
		
		}catch(Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("mensagem","Ocorreu um erro ao tentar salvar o perfil: "+e.getMessage());
		}
		return "redirect:/usuario";
	}
	
	@GetMapping("/apagar/{id}")
	public void apagar(@PathVariable Long id, HttpServletResponse response) {
		try {
			perfilService.apagar(id);
			response.setStatus(200);
		}catch(Exception e) {
			response.setStatus(500);
		}
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("perfil/cadastro");
		mv.addObject("perfil",perfilService.obter(id));
		mv.addObject("permissoes", permissaoService.listar());
		
		return mv;
	}

	public PerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	public PermissaoService getPermissaoService() {
		return permissaoService;
	}

	public void setPermissaoService(PermissaoService permissaoService) {
		this.permissaoService = permissaoService;
	}
	
}

package br.com.maquiagemimportada.portal.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;

import br.com.maquiagemimportada.portal.domain.Usuario;
import br.com.maquiagemimportada.portal.dto.CadastroDTO;
import br.com.maquiagemimportada.portal.service.CadastroService;
import br.com.maquiagemimportada.portal.util.MailUtil;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

	private static final Logger logger = LoggerFactory.getLogger(CadastroController.class);
	
	@Autowired
	private CadastroService cadastroService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@GetMapping
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("cadastro/form");
		mv.addObject("dto",new CadastroDTO());
		
		return mv;
	}
	
	@PostMapping
	public String cadastrar(@Valid CadastroDTO dto, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			StringBuilder erros = new StringBuilder("");
			for(ObjectError error : result.getAllErrors()) {
				logger.info(error.getDefaultMessage());
				erros.append(error.getDefaultMessage()+"<br/>");
			}
			
			attributes.addAttribute("mensagem",erros.toString());
			return "cadastro";
		}
		
		cadastroService.salvar(dto);
		
		enviarEmailConfirmacao(dto.getUsuario());
		
		return "cadastro/sucesso";
	}
	
	private void enviarEmailConfirmacao(Usuario usuario) {
		try {
			MailUtil mu = new MailUtil();
			mu.enviarConfirmacaoEmail(usuario,templateEngine);
		}catch(Exception e) {
			e.printStackTrace();
			//logger.error(e.getMessage());
		}
	}
	
	@RequestMapping("/testeEmail")
	public String testarEmail() {
		try {
			MailUtil mu = new MailUtil();
			mu.enviar(templateEngine);
		}catch(Exception e) {
			e.printStackTrace();
			//logger.error(e.getMessage());
		}
		
		return "redirect:/cadastro";
	}
	
	@GetMapping("/confirmar/{hash}")
	public ModelAndView confirmar(@PathVariable String hash) {
		ModelAndView mv;
		
		try {
			cadastroService.confirmarEmail(hash);
			mv = new ModelAndView("cadastro/confirmado");
		}catch(UsernameNotFoundException e) {
			mv = new ModelAndView("cadastro/falhaConfirmacao");
		}
		
		return mv;
	}
	
	@PostMapping("/reenviarConfirmacao")
	public void reenviarConfirmacao(@RequestParam("email") String email, HttpServletResponse response) {
		Usuario usuario = cadastroService.obterUsuarioReenvioConfirmacao(email);
		if(usuario != null) {
			enviarEmailConfirmacao(usuario);
		}
	}

	public CadastroService getCadastroService() {
		return cadastroService;
	}

	public void setCadastroService(CadastroService cadastroService) {
		this.cadastroService = cadastroService;
	}

	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
	
}

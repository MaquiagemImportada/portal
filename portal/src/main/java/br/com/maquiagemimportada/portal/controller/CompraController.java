package br.com.maquiagemimportada.portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.maquiagemimportada.portal.domain.Carrinho;
import br.com.maquiagemimportada.portal.service.CarrinhoService;

@Controller
@RequestMapping("/compra")
public class CompraController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CompraController.class);
	
	@Autowired
	private CarrinhoService carrinhoService;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("compra/listar");
		List<Carrinho> carrinhos = carrinhoService.listar();
		mv.addObject("carrinhos", carrinhos);
		
		return mv;
	}

	public CarrinhoService getCarrinhoService() {
		return carrinhoService;
	}

	public void setCarrinhoService(CarrinhoService carrinhoService) {
		this.carrinhoService = carrinhoService;
	}
	
}

package br.com.maquiagemimportada.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.maquiagemimportada.portal.storage.ImagemStorageLocal;

@Controller
@RequestMapping("/imagem")
public class ImagemController {
	
	@GetMapping("/exibir/{nome}")
	public byte[] exibir(@PathVariable String nome) {
		ImagemStorageLocal storage = new ImagemStorageLocal();
		return storage.exibirTemporaria(nome);
	}
	
}

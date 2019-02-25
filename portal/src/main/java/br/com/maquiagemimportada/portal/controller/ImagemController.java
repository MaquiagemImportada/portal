package br.com.maquiagemimportada.portal.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.maquiagemimportada.portal.storage.ImagemStorageLocal;

@Controller
@RequestMapping("/imagem")
public class ImagemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImagemController.class);
	
	@GetMapping("/exibir/{nome}")
	public void exibir(@PathVariable String nome, HttpServletResponse response) {
		byte[] retorno;
		ImagemStorageLocal storage = new ImagemStorageLocal();
		try {
			retorno = storage.exibirTemporaria(nome);
			if(retorno != null) {
				logger.debug("O retorno retornou com o tamanho de "+retorno.length);
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			retorno = new byte[] {};
		}
		
		InputStream in = new ByteArrayInputStream(retorno);
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    try {
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@GetMapping("/exibir/thumb/{nome}")
	public void exibirThumb(@PathVariable String nome, HttpServletResponse response) {
		byte[] retorno;
		ImagemStorageLocal storage = new ImagemStorageLocal();
		try {
			retorno = storage.exibirThumbTemporario(nome, "G");
			if(retorno != null) {
				logger.debug("O retorno retornou com o tamanho de "+retorno.length);
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			retorno = new byte[] {};
		}
		
		InputStream in = new ByteArrayInputStream(retorno);
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    try {
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
}

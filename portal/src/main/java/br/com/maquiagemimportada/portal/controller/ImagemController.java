package br.com.maquiagemimportada.portal.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.maquiagemimportada.portal.service.ImagemProdutoService;
import br.com.maquiagemimportada.portal.storage.ImagemStorageLocal;

@Controller
@RequestMapping("/imagem")
public class ImagemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImagemController.class);
	
	@Autowired
	private ImagemProdutoService imagemProdutoService;
	
	/**
	 * "/exibir/{nome:.*}"
	 * @param nome
	 * @param response
	 */
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
	
	@GetMapping("/exibir/thumb/{tamanho}/{nome}")
	public void exibirThumb(@PathVariable String tamanho, @PathVariable String nome, HttpServletResponse response) {
		byte[] retorno;
		ImagemStorageLocal storage = new ImagemStorageLocal();
		try {
			retorno = storage.exibirThumb(nome, tamanho);
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
	
	@GetMapping("/exibir/thumb/temporario/{nome}")
	public void exibirThumbTemporario(@PathVariable String nome, HttpServletResponse response) {
		byte[] retorno;
		ImagemStorageLocal storage = new ImagemStorageLocal();
		try {
			retorno = storage.exibirThumbTemporario(nome, "M");
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
	
	@GetMapping("/apagarTemporaria/{nome}")
	public void apagarTemporaria(@PathVariable String nome, HttpServletResponse response) {
		try {
			ImagemStorageLocal storage = new ImagemStorageLocal();
						
			logger.info("Vai apagar a imgem no banco");
			imagemProdutoService.apagar(storage.getPastaImagensTemporarias()+nome);
			logger.info("Apagou a imagem do banco");
			
			storage.apagarTemporaria(nome);
			
			response.setStatus(200);
		}catch(Exception e) {
			logger.error(e.getMessage());
			response.setStatus(500);
		}
	}

	public ImagemProdutoService getImagemProdutoService() {
		return imagemProdutoService;
	}

	public void setImagemProdutoService(ImagemProdutoService imagemProdutoService) {
		this.imagemProdutoService = imagemProdutoService;
	}
	
}

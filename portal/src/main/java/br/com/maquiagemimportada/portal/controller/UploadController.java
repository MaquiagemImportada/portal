package br.com.maquiagemimportada.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.maquiagemimportada.portal.dto.ImagemProdutoDTO;
import br.com.maquiagemimportada.portal.service.ImagemProdutoService;
import br.com.maquiagemimportada.portal.storage.ImagemStorage;
import br.com.maquiagemimportada.portal.storage.ImagemStorageRunnable;

@RestController
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	private ImagemStorage imagemStorage;
	
	@Autowired
	private ImagemProdutoService imagemProdutoService;
	
	@PostMapping("/imagemProduto")
	public DeferredResult<ImagemProdutoDTO> imagemProduto(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<ImagemProdutoDTO> resultado = new DeferredResult<ImagemProdutoDTO>();
		
		Thread thread = new Thread(new ImagemStorageRunnable(files, resultado, imagemStorage, imagemProdutoService));
		thread.start();
		
		return resultado;
	}

	public ImagemStorage getImagemStorage() {
		return imagemStorage;
	}

	public void setImagemStorage(ImagemStorage imagemStorage) {
		this.imagemStorage = imagemStorage;
	}

	/**
	 * @return the imagemProdutoRepository
	 */
	public ImagemProdutoService getImagemProdutoService() {
		return imagemProdutoService;
	}

	/**
	 * @param imagemProdutoRepository the imagemProdutoRepository to set
	 */
	public void setImagemProdutoService(ImagemProdutoService imagemProdutoService) {
		this.imagemProdutoService = imagemProdutoService;
	}
}
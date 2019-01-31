package br.com.maquiagemimportada.portal.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.maquiagemimportada.portal.domain.ImagemProduto;
import br.com.maquiagemimportada.portal.service.ImagemProdutoService;

public class ImagemStorageRunnable implements Runnable{

	private MultipartFile[] files;
	private DeferredResult<String> resultado;
	private ImagemStorage imagemStorage;
	private ImagemProdutoService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ImagemStorageRunnable.class);
	
	public ImagemStorageRunnable(MultipartFile[] files, DeferredResult<String> resultado, ImagemStorage imagemStorage, ImagemProdutoService imagemProdutoService) {
		this.files = files;
		this.resultado = resultado;
		this.imagemStorage = imagemStorage;
		this.service = imagemProdutoService;
	}
	
	@Override
	@Transactional
	public void run() {
		String retorno = "";
		
		try {
			if(files != null && files.length > 0) {
				
				retorno = imagemStorage.salvarImagensTemporarias(files);
				
				for(MultipartFile file : files) {
					if(file != null) {
						ImagemProduto imagem = new ImagemProduto();
						imagem.setCaminho(file.getOriginalFilename());
						imagem.setDestaque(false);
						
						try {
							service.salvar(imagem);
						}catch(Exception e) {
							logger.error("Erro ao tentar salvar imagem de produto: "+e.getMessage());
						}
					}
				}
			}
			resultado.setResult(retorno);
		}catch(Exception e) {
			resultado.setErrorResult(e);
		}
	}

	public ImagemProdutoService getService() {
		return service;
	}

	public void setService(ImagemProdutoService service) {
		this.service = service;
	}

}
package br.com.maquiagemimportada.portal.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.maquiagemimportada.portal.domain.ImagemProduto;
import br.com.maquiagemimportada.portal.dto.ImagemProdutoDTO;
import br.com.maquiagemimportada.portal.service.ImagemProdutoService;

public class ImagemStorageRunnable implements Runnable{

	private MultipartFile[] files;
	private DeferredResult<ImagemProdutoDTO> resultado;
	private ImagemStorage imagemStorage;
	private ImagemProdutoService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ImagemStorageRunnable.class);
	
	public ImagemStorageRunnable(MultipartFile[] files, DeferredResult<ImagemProdutoDTO> resultado, ImagemStorage imagemStorage, ImagemProdutoService imagemProdutoService) {
		this.files = files;
		this.resultado = resultado;
		this.imagemStorage = imagemStorage;
		this.service = imagemProdutoService;
	}
	
	@Override
	@Transactional
	public void run() {
		ImagemProdutoDTO img = null;
		
		try {
			if(files != null && files.length > 0) {
				if(files[0] != null) {
					ImagemProduto imagem = new ImagemProduto();
					imagem.setCaminho(imagemStorage.salvarImagensTemporarias(files));
					imagem.setDestaque(false);
					
					try {
						service.salvar(imagem);
						img = new ImagemProdutoDTO(imagem.getId(), imagem.getCaminho());
					}catch(Exception e) {
						logger.error("Erro ao tentar salvar imagem de produto: "+e.getMessage());
					}
				}				
			}
			resultado.setResult(img);
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
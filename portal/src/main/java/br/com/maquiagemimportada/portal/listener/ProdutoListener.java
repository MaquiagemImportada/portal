package br.com.maquiagemimportada.portal.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.maquiagemimportada.portal.event.ProdutoImagemSalvarEvent;
import br.com.maquiagemimportada.portal.storage.ImagemStorageRunnable;

@Component
public class ProdutoListener {

	//@Autowired
	private ImagemStorageRunnable imagemStorage;
	
	@EventListener
	public void redimensionarImagensProduto(ProdutoImagemSalvarEvent evento) {
		//System.out.println("Novo produto Salvo!!!");
		//imagemStorage.
	}

	public ImagemStorageRunnable getImagemStorage() {
		return imagemStorage;
	}

	public void setImagemStorage(ImagemStorageRunnable imagemStorage) {
		this.imagemStorage = imagemStorage;
	}
}
package br.com.maquiagemimportada.portal.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.maquiagemimportada.portal.event.ProdutoImagemSalvarEvent;
import br.com.maquiagemimportada.portal.storage.ImagemStorageLocal;

@Component
public class ProdutoListener {

	@EventListener
	public void moverImagensProduto(ProdutoImagemSalvarEvent evento) {
		ImagemStorageLocal isl = new ImagemStorageLocal();
		isl.moverImagensTemporarias(evento.getProduto());
	}
}
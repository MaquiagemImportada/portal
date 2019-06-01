package br.com.maquiagemimportada.portal.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.maquiagemimportada.portal.domain.ImagemProduto;
import br.com.maquiagemimportada.portal.event.ProdutoImagemSalvarEvent;
import br.com.maquiagemimportada.portal.storage.ImagemStorageLocal;

@Component
public class ProdutoListener {

	@EventListener
	public void moverImagensProduto(ProdutoImagemSalvarEvent evento) {
		ImagemStorageLocal isl = new ImagemStorageLocal();
		isl.gerarThumbnails(evento.getProduto());
		if(evento != null && evento.getProduto() != null && evento.getProduto().getImagens() != null && evento.getImagemProdutoRepository() != null) {
			for( ImagemProduto ip : evento.getProduto().getImagens()) {
				ip.setProduto(evento.getProduto());
				evento.getImagemProdutoRepository().save(ip);
			}
		}
	}
}
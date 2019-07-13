package br.com.maquiagemimportada.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maquiagemimportada.portal.domain.Pedido;
import br.com.maquiagemimportada.portal.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> listar(){
		return pedidoRepository.findAll();
	}

	public PedidoRepository getPedidoRepository() {
		return pedidoRepository;
	}

	public void setPedidoRepository(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
}

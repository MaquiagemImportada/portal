package br.com.maquiagemimportada.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maquiagemimportada.portal.domain.Carrinho;
import br.com.maquiagemimportada.portal.domain.Cliente;
import br.com.maquiagemimportada.portal.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	public List<Pedido> findAllByCarrinho(Carrinho carrinho);
	public List<Pedido> findAllByCliente(Cliente cliente);
	
}

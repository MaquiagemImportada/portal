package br.com.maquiagemimportada.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maquiagemimportada.portal.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public Optional<Produto> findBySku(String sku);
	public List<Produto> findAllByAtivo(boolean ativo);
	public List<Produto> findAllByAtivoAndDeletado(boolean ativo, boolean deletado);
}
package br.com.maquiagemimportada.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maquiagemimportada.portal.domain.Configuracao;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long>{

	public List<Configuracao> findAllByAtivoAndDeletado(Boolean ativo, Boolean deletado);
	public List<Configuracao> findAllByDeletado(Boolean deletado);
	public List<Configuracao> findByChave(String chave);
	
}

package br.com.maquiagemimportada.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maquiagemimportada.portal.domain.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
	
}

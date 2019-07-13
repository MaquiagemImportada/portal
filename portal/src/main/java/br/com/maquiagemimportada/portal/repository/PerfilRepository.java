package br.com.maquiagemimportada.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maquiagemimportada.portal.domain.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	List<Perfil> findAllByAtivo(Boolean ativo);
	
}

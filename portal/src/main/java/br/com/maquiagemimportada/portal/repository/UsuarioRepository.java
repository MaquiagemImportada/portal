package br.com.maquiagemimportada.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maquiagemimportada.portal.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public List<Usuario> findByUsername(String username);
	public Optional<Usuario> findByUsernameAndAtivoAndDeletado(String username, Boolean ativo, Boolean deletado);
	public List<Usuario> findAllByAtivoAndDeletado(Boolean ativo,Boolean deletado);

}

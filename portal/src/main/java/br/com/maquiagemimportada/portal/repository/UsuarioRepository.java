package br.com.maquiagemimportada.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.maquiagemimportada.portal.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(
			  value = "SELECT * FROM USUARIO u WHERE u.id = ?", 
			  nativeQuery = true)
	public Usuario obter(Long id);
	public List<Usuario> findByUsername(String username);
	@Query(value="select u from Usuario u where (u.username = ?1 or u.email = ?1) and u.ativo = ?2 and u.deletado = ?3 and u.confirmado = ?4")
	public Optional<Usuario> findByUsernameAndAtivoAndDeletadoAndConfirmado(String username, Boolean ativo, Boolean deletado, Boolean confirmado);
	public List<Usuario> findAllByAtivoAndDeletado(Boolean ativo,Boolean deletado);
	@Query(value="select pm.nome from permissao pm, perfil pf, perfil_permissao pp, perfil_usuario pu, usuario us where pm.id = pp.permissao_id and pf.id = pp.perfil_id and pf.id = pu.perfil_id and pu.usuario_id = us.id and us.ativo = 1 and us.deletado = 0 and us.id = ?",nativeQuery=true)
	public List<String> listarPermissoesUsuario(Long idUsuario);
	public Optional<Usuario> findByHashAndConfirmado(String hash, Boolean confirmado);
	public Optional<Usuario> findByEmailAndConfirmado(String email, Boolean confirmardo);

}

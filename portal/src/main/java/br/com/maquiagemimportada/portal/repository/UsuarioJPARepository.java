package br.com.maquiagemimportada.portal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.maquiagemimportada.portal.domain.Usuario;

@Repository
public class UsuarioJPARepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<String> listarPermissoes(Usuario usuario){
		return entityManager
				.createQuery("select p.nome from Usuario u inner join u.perfis pf inner join pf.perfil.permissoes p where u = :usuario", String.class)
				.setParameter("usuario",usuario)
				.getResultList();
	}
	
	public Usuario obter(Long id) {
		return entityManager
				.createQuery("select u from Usuario u where u.id = :id",Usuario.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}

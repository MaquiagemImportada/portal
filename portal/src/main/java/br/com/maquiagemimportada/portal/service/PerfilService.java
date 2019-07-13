package br.com.maquiagemimportada.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maquiagemimportada.portal.domain.Perfil;
import br.com.maquiagemimportada.portal.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	public Perfil obter(Long id) {
		return perfilRepository.getOne(id);
	}
	
	public List<Perfil> listarAtivos(){
		List<Perfil> retorno = new ArrayList<Perfil>();
		
		retorno.addAll(perfilRepository.findAllByAtivo(true));
		
		return retorno;
	}
	
	public List<Perfil> listar(){
		List<Perfil> retorno = new ArrayList<Perfil>();
		
		retorno.addAll(perfilRepository.findAll());
		
		return retorno;
	}
	
	@Transactional
	public Perfil salvar(Perfil perfil) {
		return perfilRepository.save(perfil);
	}
	
	@Transactional
	public void apagar(Long id) {
		perfilRepository.deleteById(id);
	}
	
	@Transactional
	public void apagar(Perfil perfil) {
		perfilRepository.delete(perfil);
	}

	public PerfilRepository getPerfilRepository() {
		return perfilRepository;
	}

	public void setPerfilRepository(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}
}

package br.com.maquiagemimportada.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maquiagemimportada.portal.domain.Usuario;
import br.com.maquiagemimportada.portal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario obter(Long id) {
		return usuarioRepository.obter(id);
	}
	
	public List<Usuario> listar(){
		List<Usuario> retorno;
		
		try {
			retorno = usuarioRepository.findAll();
		}catch(Exception e) {
			logger.error(e.getMessage());
			retorno = new ArrayList<Usuario>();
		}
		
		return retorno;
	}
	
	public List<Usuario> listarAtivos(){
		List<Usuario> retorno;
		
		try {
			retorno = usuarioRepository.findAllByAtivoAndDeletado(true,false);
		}catch(Exception e) {
			logger.error(e.getMessage());
			retorno = new ArrayList<Usuario>();
		}
		
		return retorno;
	}
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void apagar(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	@Transactional
	public void apagar(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
}

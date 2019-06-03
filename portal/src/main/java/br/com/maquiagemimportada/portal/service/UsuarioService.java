package br.com.maquiagemimportada.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maquiagemimportada.portal.domain.Usuario;
import br.com.maquiagemimportada.portal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
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

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
}

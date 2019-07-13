package br.com.maquiagemimportada.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.maquiagemimportada.portal.domain.Permissao;
import br.com.maquiagemimportada.portal.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public Permissao obter(Long id) {
		return permissaoRepository.getOne(id);
	}
	
	public List<Permissao> listar(){
		List<Permissao> retorno = new ArrayList<Permissao>();
		
		retorno.addAll(permissaoRepository.findAll(new Sort(Sort.Direction.ASC,"nome")));
		
		return retorno;
	}

	public PermissaoRepository getPermissaoRepository() {
		return permissaoRepository;
	}

	public void setPermissaoRepository(PermissaoRepository permissaoRepository) {
		this.permissaoRepository = permissaoRepository;
	}
	
}

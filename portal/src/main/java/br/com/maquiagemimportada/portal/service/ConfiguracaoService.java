package br.com.maquiagemimportada.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maquiagemimportada.portal.domain.Configuracao;
import br.com.maquiagemimportada.portal.repository.ConfiguracaoRepository;

@Service
public class ConfiguracaoService {

	private static final Logger logger = LoggerFactory.getLogger(ConfiguracaoService.class);
	
	@Autowired
	private ConfiguracaoRepository configuracaoRepository;
	
	public List<Configuracao> listarAtivos(){
		List<Configuracao> retorno;
		
		try {
			retorno = configuracaoRepository.findAllByAtivoAndDeletado(true, false);
		}catch(Exception e) {
			logger.error(e.getMessage());
			retorno = new ArrayList<Configuracao>();
		}
		
		return retorno;
	}
	
	public List<Configuracao> listarNaoDeletados(){
		List<Configuracao> retorno;
		
		try {
			retorno = configuracaoRepository.findAllByDeletado(false);
		}catch(Exception e) {
			logger.error(e.getMessage());
			retorno = new ArrayList<Configuracao>();
		}
		
		return retorno;
	}
	
	@Transactional
	public Configuracao salvar(Configuracao configuracao) {
		return configuracaoRepository.save(configuracao);	
	}
	
	@Transactional
	public void apagar(Long id) {
		Configuracao configuracao = configuracaoRepository.getOne(id);
		configuracao.setDeletado(true);
		configuracaoRepository.save(configuracao);
	}
	
	public Configuracao getOne(Long id) {
		return configuracaoRepository.getOne(id);
	}

	public ConfiguracaoRepository getConfiguracaoRepository() {
		return configuracaoRepository;
	}

	public void setConfiguracaoRepository(ConfiguracaoRepository configuracaoRepository) {
		this.configuracaoRepository = configuracaoRepository;
	}
	
}

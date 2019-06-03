package br.com.maquiagemimportada.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public ConfiguracaoRepository getConfiguracaoRepository() {
		return configuracaoRepository;
	}

	public void setConfiguracaoRepository(ConfiguracaoRepository configuracaoRepository) {
		this.configuracaoRepository = configuracaoRepository;
	}
	
}

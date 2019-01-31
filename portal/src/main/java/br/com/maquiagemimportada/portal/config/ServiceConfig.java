package br.com.maquiagemimportada.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.maquiagemimportada.portal.service.ProdutoService;
import br.com.maquiagemimportada.portal.storage.ImagemStorage;
import br.com.maquiagemimportada.portal.storage.ImagemStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = ProdutoService.class)
public class ServiceConfig {

	@Bean
	public ImagemStorage imagemStorage() {
		return new ImagemStorageLocal();
	}
}
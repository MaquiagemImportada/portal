package br.com.maquiagemimportada.portal.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.maquiagemimportada.portal.domain.CategoriaProduto;

public class CategoriaProdutoConverter implements Converter<String, CategoriaProduto>{

	@Override
	public CategoriaProduto convert(String id) {
		if(!StringUtils.isEmpty(id)) {
			CategoriaProduto categoria = new CategoriaProduto();
			categoria.setId(Long.valueOf(id));
			return categoria;
		}
		return null;
	}

}
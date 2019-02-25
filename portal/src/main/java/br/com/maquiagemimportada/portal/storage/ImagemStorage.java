package br.com.maquiagemimportada.portal.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImagemStorage {

	public String salvarImagensTemporarias(MultipartFile[] files) throws IllegalStateException, IOException;
	
}
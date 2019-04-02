package br.com.maquiagemimportada.portal.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImagemStorage {

	String salvarImagens(MultipartFile[] files) throws IllegalStateException, IOException;
}
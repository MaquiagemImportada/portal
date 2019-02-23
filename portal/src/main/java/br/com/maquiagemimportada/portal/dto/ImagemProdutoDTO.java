package br.com.maquiagemimportada.portal.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ImagemProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;
	
	private String nome;
	
	public ImagemProdutoDTO(){
		super();
	}
	
	public ImagemProdutoDTO(Long id){
		setId(id);
	}
	
	public ImagemProdutoDTO(Long id, String nome){
		setId(id);
		setNome(nome);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome != null && nome.length() > 0) {
			String[] parts;
			try {
			if(nome.contains("/")) {
				parts = nome.split("/");
				this.nome = parts[parts.length - 1];
			}else if(nome.contains("\\")) {
				parts = nome.split("\\\\");
				this.nome = parts[parts.length - 1];
			}
			}catch(Exception e) {
				e.printStackTrace();
				this.nome = nome;
			}
		}else {
			this.nome = nome;
		}
	}
	
	
}

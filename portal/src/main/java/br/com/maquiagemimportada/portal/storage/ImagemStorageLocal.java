package br.com.maquiagemimportada.portal.storage;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import br.com.maquiagemimportada.portal.domain.Produto;
import br.com.maquiagemimportada.portal.util.Constantes;
import br.com.maquiagemimportada.portal.util.RenameImage;
import net.coobird.thumbnailator.Thumbnails;

public class ImagemStorageLocal implements ImagemStorage {

	private static final Logger logger = LoggerFactory.getLogger(ImagemStorageLocal.class);
	
	private Path diretorioImagens;
	
	public ImagemStorageLocal() {
		this(getDefault().getPath(Constantes.MI_MEDIA_HOME, Constantes.MI_PASTA_IMAGENS));
	}
	
	public ImagemStorageLocal(Path path) {
		diretorioImagens = path;
		
		criarPastas();
	}

	private void criarPastas() {
		try {
			Files.createDirectories(this.diretorioImagens);
		}catch(IOException ioe) {
			logger.error("Erro criando pastas de imagens");
		}
	}
	
	private void gerarThumbnails(String nomeArquivo) {
		
		if(nomeArquivo != null && !"".equals(nomeArquivo)) {
			
			File thumbP = new File(getNomeThumb(nomeArquivo, "P"));
			if(!thumbP.exists()) {
				try {
					Thumbnails.of(nomeArquivo).size(Constantes.MI_IMAGEM_PRODUTO_P_WIDTH, Constantes.MI_IMAGEM_PRODUTO_P_HEIGHT).toFiles(RenameImage.SUFFIX_P);
				}catch(Exception e) {
					logger.error("Erro ao tentar cricar thumb: "+thumbP.getName()+"\n"+e.getMessage());
				}
			}
			
			File thumbM = new File(getNomeThumb(nomeArquivo, "M"));
			if(!thumbM.exists()) {
				try {
					Thumbnails.of(nomeArquivo).size(Constantes.MI_IMAGEM_PRODUTO_M_WIDTH, Constantes.MI_IMAGEM_PRODUTO_M_HEIGHT).toFiles(RenameImage.SUFFIX_M);
				}catch(Exception e) {
					logger.error("Erro ao tentar cricar thumb: "+thumbP.getName()+"\n"+e.getMessage());
				}
			}
			
			File thumbG = new File(getNomeThumb(nomeArquivo, "G"));
			if(!thumbG.exists()) {
				try {
					Thumbnails.of(nomeArquivo).size(Constantes.MI_IMAGEM_PRODUTO_G_WIDTH, Constantes.MI_IMAGEM_PRODUTO_G_HEIGHT).toFiles(RenameImage.SUFFIX_G);
				}catch(Exception e) {
					logger.error("Erro ao tentar cricar thumb: "+thumbP.getName()+"\n"+e.getMessage());
				}
			}
		}
	}
	
	public void gerarThumbnails(Produto produto) {
		if(produto != null && produto.getImagensTemporarias() != null && !"".equals(produto.getImagensTemporarias())) {
			String[] imgsTmps = produto.getImagensTemporarias().split(",");
			
			for(String img : imgsTmps) {
				gerarThumbnails(img);
			}
		}
	}
	
	private String renomearArquivo(String nomeOriginal) {
		String[] splited = nomeOriginal.split("\\.");
		StringBuilder novoNome = new StringBuilder(UUID.randomUUID().toString());
		
		if(splited != null && splited.length > 0) {
			try {
				novoNome.append(".");
				novoNome.append(splited[splited.length-1]);
			}catch(Exception e) {
				logger.error(e.getMessage());
			}
		}
		
		return novoNome.toString();
	}
	
	/**
	 * Recebe o nome do arquivo original e retorna um array de string com os nomes dos arquivos redimensionados.
	 * Caso o nome do arquivo tenha o path completo, os nomes dos thumbs tambem serão retornados completos.
	 * Ex.:
	 * 
	 * Caso o nome do arquivo seja arquivo001.txt, serão retornados:
	 * arquivo001_300X400.txt
	 * arquivo001_150X200.txt
	 * arquivo001_45X60.txt
	 * 
	 * Caso o nome do arquivo seja C:\pasta1\pasta2\arquivo001.txt, serão retornados:
	 * C:\pasta1\pasta2\arquivo001_300X400.txt
	 * C:\pasta1\pasta2\arquivo001_150X200.txt
	 * C:\pasta1\pasta2\arquivo001_45X60.txt
	 * 
	 * 
	 * @param nomeOriginal
	 * @return String[] com os nomes dos arquivos redimensionados
	 */
	@SuppressWarnings("unused")
	private List<String> getNomesArquivosRedimensionados(String nomeOriginal) {
		List<String> retorno = new ArrayList<String>();
		
		if(nomeOriginal != null && !"".equals(nomeOriginal)) {
			try {
				int lstidx = nomeOriginal.lastIndexOf(".");
				if(lstidx != -1) {
					retorno.add(nomeOriginal.substring(0, lstidx)+"_"+Constantes.MI_IMAGEM_PRODUTO_G_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_G_HEIGHT+nomeOriginal.substring(lstidx));
					retorno.add(nomeOriginal.substring(0, lstidx)+"_"+Constantes.MI_IMAGEM_PRODUTO_M_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_M_HEIGHT+nomeOriginal.substring(lstidx));
					retorno.add(nomeOriginal.substring(0, lstidx)+"_"+Constantes.MI_IMAGEM_PRODUTO_P_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_P_HEIGHT+nomeOriginal.substring(lstidx));
				}else {
					retorno.add(nomeOriginal+"_"+Constantes.MI_IMAGEM_PRODUTO_G_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_G_HEIGHT);
					retorno.add(nomeOriginal+"_"+Constantes.MI_IMAGEM_PRODUTO_M_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_M_HEIGHT);
					retorno.add(nomeOriginal+"_"+Constantes.MI_IMAGEM_PRODUTO_P_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_P_HEIGHT);
				}
			}catch(Exception e) {
				logger.error(e.getMessage());
			}
		}
		return retorno;
	}
	
	/**
	 * Recebe o nome do arquivo no formato arquivo.jpg e retorna o nome do thumbnail correspondente .
	 * O tamanho passado como parametro deve ser P, M ou G.
	 * Caso o tamanho não seja um dos padrões, o tamanho M é usado como default.
	 * @param nomeArquivo
	 * @param tamanho
	 * @return Nome do Thumbnail
	 */
	public byte[] exibirThumb(String nome, String tamanho) {
		byte[] retorno;
		try {
			retorno = Files.readAllBytes(diretorioImagens.resolve(getNomeThumb(nome, tamanho)));
		} catch (IOException e) {
			logger.error(e.getMessage());
			retorno = new byte[] {};
		}
		
		return retorno;
	}
	
	public byte[] exibir(String nome) {
		byte[] retorno;
		try {
			retorno = Files.readAllBytes(diretorioImagens.resolve(nome));
		} catch (IOException e) {
			logger.error(e.getMessage());
			retorno = new byte[] {};
		}
		
		return retorno;
	}
	
	/**
	 * Recebe o nome do arquivo no formato arquivo.jpg e retorna o nome do thumbnail correspondente .
	 * O tamanho passado como parametro deve ser P, M ou G.
	 * Caso o tamanho não seja um dos padrões, o tamanho M é usado como default.
	 * @param nomeArquivo
	 * @param tamanho
	 * @return Nome do Thumbnail
	 */
	private String getNomeThumb(String nomeArquivo, String tamanho) {
		String retorno = "";
		
		if(nomeArquivo != null && nomeArquivo.length() > 0) {
			try {
				String[] part = nomeArquivo.split("\\.");
				if(part.length == 2) {
					if("G".equals(tamanho)) {
						retorno = part[0]+"_"+Constantes.MI_IMAGEM_PRODUTO_G_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_G_HEIGHT+"."+part[1];
					}else if("P".equals(tamanho)) {
						retorno = part[0]+"_"+Constantes.MI_IMAGEM_PRODUTO_P_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_P_HEIGHT+"."+part[1];
					}else {
						retorno = part[0]+"_"+Constantes.MI_IMAGEM_PRODUTO_M_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_M_HEIGHT+"."+part[1];
					}
				}
			}catch(Exception e) {
				logger.error(e.getMessage());
			}
		}
		
		return retorno;
	}
	
	@Override
	public String salvarImagens(MultipartFile[] files) throws IllegalStateException, IOException {
		String newName = "";
		if(files != null && files.length > 0) {
			if(files[0] != null) {
				MultipartFile file = files[0];
				String nomeArquivo = renomearArquivo(file.getOriginalFilename());
				newName = diretorioImagens.toAbsolutePath().toString() + getDefault().getSeparator()+nomeArquivo;
				file.transferTo(new File(newName));
			}
		}
		
		return newName;
	}
	
	public void apagar(String imagem) throws IOException {
		FileUtils.forceDelete(new File(getPastaImagens()+imagem));
		try {
			File thumbP = new File(getPastaImagens()+getNomeThumb(imagem,"P"));
			if(thumbP.exists()) {
				FileUtils.forceDelete(thumbP);
			}
			
			File thumbM = new File(getPastaImagens()+getNomeThumb(imagem,"M"));
			if(thumbM.exists()) {
				FileUtils.forceDelete(thumbM);
			}

			File thumbG = new File(getPastaImagens()+getNomeThumb(imagem,"G"));
			if(thumbG.exists()) {
				FileUtils.forceDelete(thumbG);
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public String getPastaImagens() {
		return diretorioImagens.toAbsolutePath().toString()+getDefault().getSeparator();
	}
}
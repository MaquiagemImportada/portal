package br.com.maquiagemimportada.portal.storage;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import br.com.maquiagemimportada.portal.domain.ImagemProduto;
import br.com.maquiagemimportada.portal.domain.Produto;
import br.com.maquiagemimportada.portal.util.Constantes;
import br.com.maquiagemimportada.portal.util.RenameImage;
import net.coobird.thumbnailator.Thumbnails;

public class ImagemStorageLocal implements ImagemStorage {

	private static final Logger logger = LoggerFactory.getLogger(ImagemStorageLocal.class);
	
	private Path diretorioImagens;
	private Path diretorioImagensTemporarias;
	
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
			Files.createDirectories(getDiretorioImagensTemporarias());
			
			if(logger.isDebugEnabled()) {
				logger.debug("Pastas de imagens criadas com sucesso: ");
				logger.debug(diretorioImagens.toAbsolutePath().toString());
				logger.debug(diretorioImagensTemporarias.toAbsolutePath().toString());
			}
		}catch(IOException ioe) {
			logger.error("Erro criando pastas de imagens");
		}
	}

	@Override
	public String salvarImagensTemporarias(MultipartFile[] files) throws IllegalStateException, IOException {
		String newName = "";
		if(files != null && files.length > 0) {
			if(files[0] != null) {
				MultipartFile file = files[0];
				String nomeArquivo = renomearArquivo(file.getOriginalFilename());
				newName = diretorioImagensTemporarias.toAbsolutePath().toString() + getDefault().getSeparator()+nomeArquivo;
				file.transferTo(new File(newName));
				gerarThumbnails(newName);
			}
		}
		
		return newName;
	}
	
	private void gerarThumbnails(String nomeArquivo) {
		try {
			Thumbnails.of(nomeArquivo).size(Constantes.MI_IMAGEM_PRODUTO_G_WIDTH, Constantes.MI_IMAGEM_PRODUTO_G_HEIGHT).toFiles(RenameImage.SUFFIX_G);
			Thumbnails.of(nomeArquivo).size(Constantes.MI_IMAGEM_PRODUTO_M_WIDTH, Constantes.MI_IMAGEM_PRODUTO_M_HEIGHT).toFiles(RenameImage.SUFFIX_M);
			Thumbnails.of(nomeArquivo).size(Constantes.MI_IMAGEM_PRODUTO_P_WIDTH, Constantes.MI_IMAGEM_PRODUTO_P_HEIGHT).toFiles(RenameImage.SUFFIX_P);
		}catch(Exception e) {
			logger.error(e.getMessage());
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
	
	@SuppressWarnings("unused")
	private String[] getNomesArquivosRedimensionados(String nomeOriginal) {
		String nome = "";
		
		try {
			if(nomeOriginal != null) {
				if(nomeOriginal.contains("\\.")) {
					String[] nm = nomeOriginal.split("\\.");
					if(nm.length > 2) {
						nome = nm[nm.length-2];
					}else {
						nome = nm[0];
					}
				}else {
					nome = nomeOriginal;
				}
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return new String[] {
			"Thumb-"+Constantes.MI_IMAGEM_PRODUTO_G_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_G_HEIGHT+"_"+nome,
			"Thumb-"+Constantes.MI_IMAGEM_PRODUTO_M_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_M_HEIGHT+"_"+nome,
			"Thumb-"+Constantes.MI_IMAGEM_PRODUTO_P_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_P_HEIGHT+"_"+nome
		};
	}
	
	public byte[] exibirTemporaria(String nome) {
		byte[] retorno;
		try {
			retorno = Files.readAllBytes(diretorioImagensTemporarias.resolve(nome));
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
	public byte[] exibirThumbTemporario(String nome, String tamanho) {
		byte[] retorno;
		try {
			retorno = Files.readAllBytes(diretorioImagensTemporarias.resolve(getNomeThumb(nome, tamanho)));
		} catch (IOException e) {
			logger.error(e.getMessage());
			retorno = new byte[] {};
		}
		
		return retorno;
	}
	
	public Path getDiretorioImagensTemporarias() {
		if(diretorioImagensTemporarias == null) {
			diretorioImagensTemporarias = getDefault().getPath(diretorioImagens.toString(), Constantes.MI_PASTA_IMAGENS_TEMPORARIAS);
		}
		return diretorioImagensTemporarias;
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
	
	public void moverImagensTemporarias(Produto produto) {
		for(ImagemProduto imagem : produto.getImagens()) {
			File file = new File(imagem.getCaminho());
			
			String newName = diretorioImagens.toAbsolutePath().toString() + getDefault().getSeparator()+file.getName();
			try {
				((MultipartFile) file).transferTo(new File(newName));
				logger.debug("Transferiu o arquivo original");
				String[] parts = newName.split("\\\\");
				if(parts != null && parts.length > 0) {
					((MultipartFile) file).transferTo(new File(getNomeThumb(parts[parts.length - 1], "P")));
					((MultipartFile) file).transferTo(new File(getNomeThumb(parts[parts.length - 1], "M")));
					((MultipartFile) file).transferTo(new File(getNomeThumb(parts[parts.length - 1], "G")));
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}
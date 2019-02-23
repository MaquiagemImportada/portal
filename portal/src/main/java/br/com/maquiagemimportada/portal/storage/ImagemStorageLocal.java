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
	public void salvarImagens(MultipartFile[] files) throws IllegalStateException, IOException {
		logger.debug("Salvando imagens");
		salvar(files, diretorioImagens);
	}

	@Override
	public String salvarImagensTemporarias(MultipartFile[] files) throws IllegalStateException, IOException {
		logger.debug("Salvando imagens temporarias");
		return salvar(files, diretorioImagensTemporarias);
	}
	
	private String salvar(MultipartFile[] files, Path path) throws IllegalStateException, IOException {
		String newName = "";
		if(files != null && files.length > 0) {
			if(files[0] != null) {
				MultipartFile file = files[0];
				String nomeArquivo = renomearArquivo(file.getOriginalFilename());
				newName = path.toAbsolutePath().toString() + getDefault().getSeparator()+nomeArquivo;
				file.transferTo(new File(newName));
				//redimensionarImagem(newName);
				try {
					Thumbnails.of(newName).size(Constantes.MI_IMAGEM_PRODUTO_G_WIDTH, Constantes.MI_IMAGEM_PRODUTO_G_HEIGHT).toFiles(RenameImage.SUFFIX_G);
					Thumbnails.of(newName).size(Constantes.MI_IMAGEM_PRODUTO_M_WIDTH, Constantes.MI_IMAGEM_PRODUTO_M_HEIGHT).toFiles(RenameImage.SUFFIX_M);
					Thumbnails.of(newName).size(Constantes.MI_IMAGEM_PRODUTO_P_WIDTH, Constantes.MI_IMAGEM_PRODUTO_P_HEIGHT).toFiles(RenameImage.SUFFIX_P);
				}catch(Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		return newName;
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
		try {
			return Files.readAllBytes(diretorioImagensTemporarias.resolve(nome));
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Path getDiretorioImagensTemporarias() {
		if(diretorioImagensTemporarias == null) {
			diretorioImagensTemporarias = getDefault().getPath(diretorioImagens.toString(), Constantes.MI_PASTA_IMAGENS_TEMPORARIAS);
		}
		return diretorioImagensTemporarias;
	}
}
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
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

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
			diretorioImagensTemporarias = getDefault().getPath(diretorioImagens.toString(), Constantes.MI_PASTA_IMAGENS_TEMPORARIAS);
			Files.createDirectories(this.diretorioImagensTemporarias);
			
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
		StringBuilder nomesArquivos = new StringBuilder();
		if(files != null && files.length > 0) {
			for(MultipartFile file : files) {
				if(file != null) {
					String nomeArquivo = renomearArquivo(file.getOriginalFilename());
					String newName = path.toAbsolutePath().toString() + getDefault().getSeparator()+nomeArquivo;
					file.transferTo(new File(newName));
					redimensionarImagem(newName);
					nomesArquivos.append(nomeArquivo);
					nomesArquivos.append("|");
				}
			}
		}
		String n = nomesArquivos.toString();
		if(n.length() > 0) {
			n.substring(0, n.length()-1);
		}
		return n;
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
	
	private void redimensionarImagem(String absPath) {
		try {
			for(String absNewFilePath: getNomesArquivosRedimensionados(absPath)) {
				ImageInfo origInfo = new ImageInfo(absPath); //load image info
				MagickImage image = new MagickImage(origInfo); //load image
				String[] tamanhoImg = absNewFilePath.split("_");
				String[] tamanhos = tamanhoImg[1].split("X");
				image = image.scaleImage(Integer.parseInt(tamanhos[0]), Integer.parseInt(tamanhos[1])); //to Scale image
				image.setFileName(absNewFilePath); //give new location
				image.writeImage(origInfo); //save
			}
		}catch(MagickException me) {
			logger.error("Erro ao redimensionar Imagem: "+absPath+"\n"+me.getMessage());
		}
	}
	
	private String[] getNomesArquivosRedimensionados(String nomeOriginal) {
		return new String[] {
			nomeOriginal+"_"+Constantes.MI_IMAGEM_PRODUTO_G_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_G_HEIGHT,
			nomeOriginal+"_"+Constantes.MI_IMAGEM_PRODUTO_M_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_M_HEIGHT,
			nomeOriginal+"_"+Constantes.MI_IMAGEM_PRODUTO_P_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_P_HEIGHT
		};
	}
}
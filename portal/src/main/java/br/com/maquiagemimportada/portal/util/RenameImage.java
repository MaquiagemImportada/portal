package br.com.maquiagemimportada.portal.util;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.name.Rename;

public class RenameImage extends Rename {

	public static final Rename SUFFIX_G = new Rename() {
		@Override
		public String apply(String fileName, ThumbnailParameter param)
		{
			return appendSuffix(fileName, "_"+Constantes.MI_IMAGEM_PRODUTO_G_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_G_HEIGHT);
		}
	};
	
	public static final Rename SUFFIX_M = new Rename() {
		@Override
		public String apply(String fileName, ThumbnailParameter param)
		{
			return appendSuffix(fileName, "_"+Constantes.MI_IMAGEM_PRODUTO_M_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_M_HEIGHT);
		}
	};
	
	public static final Rename SUFFIX_P = new Rename() {
		@Override
		public String apply(String fileName, ThumbnailParameter param)
		{
			return appendSuffix(fileName, "_"+Constantes.MI_IMAGEM_PRODUTO_P_WIDTH+"X"+Constantes.MI_IMAGEM_PRODUTO_P_HEIGHT);
		}
	};
	
	@Override
	public String apply(String name, ThumbnailParameter param) {
		return appendSuffix(name, "_"+param.getSize().width+"X"+param.getSize().height);
	}

}

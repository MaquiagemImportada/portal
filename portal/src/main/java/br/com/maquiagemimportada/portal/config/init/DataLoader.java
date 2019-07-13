package br.com.maquiagemimportada.portal.config.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.maquiagemimportada.portal.domain.Perfil;
import br.com.maquiagemimportada.portal.domain.Permissao;
import br.com.maquiagemimportada.portal.domain.Usuario;
import br.com.maquiagemimportada.portal.repository.PerfilRepository;
import br.com.maquiagemimportada.portal.repository.PermissaoRepository;
import br.com.maquiagemimportada.portal.repository.PessoaFisicaRepository;
import br.com.maquiagemimportada.portal.repository.UsuarioRepository;

@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PerfilRepository perfilRepository;
	@Autowired
	private PermissaoRepository permissaoRepository;
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		// ******************** USUARIO ********************
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		//USUARIO
		Usuario miUser = new Usuario();
		miUser.setUsername("miadmin");
		miUser.setPassword("miadmin");
		miUser.setEmail("ssseara@outlook.com");
		miUser.setConfirmado(true);
		usuarioRepository.save(miUser);
		usuarios.add(miUser);
		
		// ******************** PERMISSOES ********************
		
		List<Permissao> permissoes = new ArrayList<Permissao>();
		
		List<String> roles = new ArrayList<String>();
		//PRODUTO
		roles.add("PRODUTO_VISUALIZAR");
		roles.add("PRODUTO_CRIAR");
		roles.add("PRODUTO_EDITAR");
		roles.add("PRODUTO_APAGAR");
		//CATEGORIA
		roles.add("CATEGORIA_VISUALIZAR");
		roles.add("CATEGORIA_CRIAR");
		roles.add("CATEGORIA_EDITAR");
		roles.add("CATEGORIA_APAGAR");
		//PERFIL
		roles.add("PERFIL_VISUALIZAR");
		roles.add("PERFIL_CRIAR");
		roles.add("PERFIL_EDITAR");
		roles.add("PERFIL_APAGAR");
		//USUARIO
		roles.add("USUARIO_VISUALIZAR");
		roles.add("USUARIO_CRIAR");
		roles.add("USUARIO_EDITAR");
		roles.add("USUARIO_APAGAR");
		//CONFIGURACAO
		roles.add("CONFIGURACAO_VISUALIZAR");
		roles.add("CONFIGURACAO_CRIAR");
		roles.add("CONFIGURACAO_EDITAR");
		roles.add("CONFIGURACAO_APAGAR");
		
		for(String role : roles) {
			Permissao prm = new Permissao();
			prm.setNome(role);
			permissaoRepository.save(prm);
			permissoes.add(prm);
		}
		
		// ******************** PERFIL ********************
		
		//PERFIL
		Perfil perfil = new Perfil();
		perfil.setNome("Admin");
		perfil.setUsuarios(usuarios);
		perfil.setPermissoes(permissoes);
		perfilRepository.save(perfil);
		
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public PerfilRepository getPerfilRepository() {
		return perfilRepository;
	}

	public void setPerfilRepository(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}

	public PermissaoRepository getPermissaoRepository() {
		return permissaoRepository;
	}

	public void setPermissaoRepository(PermissaoRepository permissaoRepository) {
		this.permissaoRepository = permissaoRepository;
	}

	public PessoaFisicaRepository getPessoaFisicaRepository() {
		return pessoaFisicaRepository;
	}

	public void setPessoaFisicaRepository(PessoaFisicaRepository pessoaFisicaRepository) {
		this.pessoaFisicaRepository = pessoaFisicaRepository;
	}

}

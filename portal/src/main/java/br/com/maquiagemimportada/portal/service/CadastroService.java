package br.com.maquiagemimportada.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maquiagemimportada.portal.domain.Email;
import br.com.maquiagemimportada.portal.domain.PessoaFisica;
import br.com.maquiagemimportada.portal.domain.Telefone;
import br.com.maquiagemimportada.portal.domain.Usuario;
import br.com.maquiagemimportada.portal.dto.CadastroDTO;
import br.com.maquiagemimportada.portal.repository.PessoaFisicaRepository;
import br.com.maquiagemimportada.portal.repository.UsuarioRepository;

@Service
public class CadastroService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Transactional
	public CadastroDTO salvar(CadastroDTO dto) {
		PessoaFisica pf = new PessoaFisica();
		Usuario usuario = new Usuario();
		List<Telefone> telefones = new ArrayList<Telefone>();
		List<Email> emails = new ArrayList<Email>();
		
		Telefone t1 = new Telefone();
		t1.setDdd(Integer.parseInt(dto.getTelefone().substring(0,2)));
		t1.setNumero(dto.getTelefone().substring(2));
		telefones.add(t1);
		
		Email email = new Email();
		email.setEndereco(dto.getEmail());
		emails.add(email);
		
		pf.setCpf(dto.getCpf());
		pf.setDataNascimento(dto.getDataNascimento());
		pf.setNome(dto.getNome());
		pf.setTelefones(telefones);
		pf.setEmails(emails);
		pessoaFisicaRepository.save(pf);
		
		usuario.setEmail(dto.getEmail());
		usuario.setUsername(dto.getEmail());
		usuario.setPassword(dto.getSenha());
		usuario.setPessoaFisica(pf);
		
		usuarioRepository.save(usuario);
		
		dto.setUsuario(usuario);
		
		return dto;
	}
	
	@Transactional
	public void confirmarEmail(String hash) throws UsernameNotFoundException{
		Optional<Usuario> usuarioOptional = usuarioRepository.findByHashAndConfirmado(hash, false);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Link expirado!"));
		usuario.setConfirmado(true);
		usuario.setHash();
		usuarioRepository.save(usuario);
	}
	
	public Usuario obterUsuarioReenvioConfirmacao(String email) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailAndConfirmado(email, false);
		if(usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		
		return null;
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public PessoaFisicaRepository getPessoaFisicaRepository() {
		return pessoaFisicaRepository;
	}

	public void setPessoaFisicaRepository(PessoaFisicaRepository pessoaFisicaRepository) {
		this.pessoaFisicaRepository = pessoaFisicaRepository;
	}
	
}

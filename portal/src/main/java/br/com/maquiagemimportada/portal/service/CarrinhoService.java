package br.com.maquiagemimportada.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maquiagemimportada.portal.domain.Carrinho;
import br.com.maquiagemimportada.portal.repository.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public List<Carrinho> listar(){
		return carrinhoRepository.findAll();
	}

	public CarrinhoRepository getCarrinhoRepository() {
		return carrinhoRepository;
	}

	public void setCarrinhoRepository(CarrinhoRepository carrinhoRepository) {
		this.carrinhoRepository = carrinhoRepository;
	}
	
}

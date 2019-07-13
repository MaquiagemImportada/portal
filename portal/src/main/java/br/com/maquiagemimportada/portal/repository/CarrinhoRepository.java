package br.com.maquiagemimportada.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.maquiagemimportada.portal.domain.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

}

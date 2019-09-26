package br.com.saraiva.carrinho_compra.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.saraiva.carrinho_compra.domain.CarrinhoDomain;

/**
 * CarrinhoRepository
 */
public interface CarrinhoRepository extends CrudRepository<CarrinhoDomain, Long> {

    
}
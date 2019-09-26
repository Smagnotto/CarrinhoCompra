package br.com.saraiva.carrinho_compra.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.saraiva.carrinho_compra.domain.CarrinhoDomain;
import br.com.saraiva.carrinho_compra.domain.ProdutoDomain;
import br.com.saraiva.carrinho_compra.repository.CarrinhoRepository;
import br.com.saraiva.carrinho_compra.repository.ProdutoRepository;

/**
 * CarrinhoService
 */

 @Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public CarrinhoDomain adicionarProdutoCarrinho(ProdutoDomain produto) throws Exception {
        
        CarrinhoDomain carrinho = new CarrinhoDomain();
        carrinho.getProdutos().add(produto);

        carrinho.setProdutos(produtoRepository.getAllProdutoCarrinho(carrinho));

        return carrinhoRepository.save(carrinho);
    }

    public CarrinhoDomain getById(long id) {
        return carrinhoRepository.findById(id).orElse(null);
    }

    public List<CarrinhoDomain> getAll() {
        List<CarrinhoDomain> lst = new ArrayList<>();

        carrinhoRepository.findAll().forEach(carrinho -> lst.add(carrinho));

        return lst;
    }
}
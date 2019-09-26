package br.com.saraiva.carrinho_compra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.saraiva.carrinho_compra.domain.CarrinhoDomain;
import br.com.saraiva.carrinho_compra.domain.ProdutoDomain;
import br.com.saraiva.carrinho_compra.service.CarrinhoService;

/**
 * CarrinhoController
 */
@RestController
@RequestMapping(path = "carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService service;

    @PostMapping("/") 
    public ResponseEntity<CarrinhoDomain> adicionarProdutoCarrinho(@RequestBody ProdutoDomain produto) {
        
        try {
            CarrinhoDomain carrinho = service.adicionarProdutoCarrinho(produto);
            return new ResponseEntity<>(carrinho, HttpStatus.CREATED);
        }
        catch (Exception ex) { 
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<CarrinhoDomain> getAll() {
        return service.getAll();
    }
    
    @GetMapping(path="/{id}")
    public ResponseEntity<CarrinhoDomain> getProduct(@PathVariable long id) {
        CarrinhoDomain carrinho = service.getById(id);
        if (carrinho == null)
            return new ResponseEntity<>(carrinho, HttpStatus.NOT_FOUND);    
        else
            return new ResponseEntity<>(carrinho, HttpStatus.OK);    
    }
}
package br.com.saraiva.carrinho_compra.repository;

import java.util.Set;
import java.util.HashSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import br.com.saraiva.carrinho_compra.domain.CarrinhoDomain;
import br.com.saraiva.carrinho_compra.domain.ProdutoDomain;
import br.com.saraiva.config.ApplicationProperties;

/**
 * ProdutoRepository
 */
@Repository
public class ProdutoRepository {

    private final String url;
    private RestTemplate rest = new RestTemplate();

    ProdutoRepository() {
        url = ApplicationProperties.INSTANCE.getUrlServiceProduto();
    }

    public Set<ProdutoDomain> getAllProdutoCarrinho(CarrinhoDomain carrinho) throws Exception {
        Set<ProdutoDomain> listaProdutos = new HashSet<>();

        for (ProdutoDomain produto: carrinho.getProdutos()) {
            ResponseEntity<ProdutoDomain> response = rest.getForEntity(url + "/" + produto.getId(), ProdutoDomain.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ProdutoDomain produtoResponse = response.getBody();

                //Pega o produto só pra atualizar a descricao.
                produto.setId(0);
                produto.setDescricao(produtoResponse.getDescricao());
                produto.setValor(produtoResponse.getValor());
                produto.getCarrinho().add(carrinho);

                listaProdutos.add(produto);
            }else {
                //TODO estourar exception
                throw new Exception();
            }
        }

        return listaProdutos;
    }
}
package br.com.saraiva.carrinho_compra.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * CarrinhoDomain
 */
@Entity
@Table(name="carrinho")
public class CarrinhoDomain implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique = true, nullable = false)
    private long id;

    @ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL) 
    @JoinTable(name="carrinho_produto", joinColumns = {
    @JoinColumn(name="carrinho_ID", nullable=false, updatable=false)}, inverseJoinColumns = 
    {@JoinColumn(name="produto_ID", nullable=false, updatable=false)})
    private Set<ProdutoDomain> produtos = new HashSet<>();

    public void setProdutos(Set<ProdutoDomain> produtos) {
        this.produtos = produtos;
    }

    public Set<ProdutoDomain> getProdutos() {
        return this.produtos;
    }

    public double getValorTotal() {
        double valorTot = 0;

        for(ProdutoDomain produto: produtos) {
            valorTot+= produto.getValor();
        }

        return valorTot;
    }
}
package br.com.saraiva.carrinho_compra.domain;

import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ProdutoDomain
 */
@Entity
@Table(name = "produto")
public class ProdutoDomain implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique = true, nullable = false)
    private long id;

    @Transient
    private String descricao;

    @Column(name="quantidade", nullable = false)
    private int quantidade;

    @Transient
    private double valor;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "produtos")
    private Set<CarrinhoDomain> listaCarrinho = new HashSet<>();

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCarrinho(Set<CarrinhoDomain> listaCarrinho) {
        this.listaCarrinho = listaCarrinho;
    }

    public Set<CarrinhoDomain> getCarrinho() {
        return listaCarrinho;
    }

    public long getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(long id) {
        this.id = id;
    }
}
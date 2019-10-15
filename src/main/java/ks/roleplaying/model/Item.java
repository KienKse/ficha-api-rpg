package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
//@Table(name = "ITEM", schema = "rpg")
@Table(name = "ITEM")
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "PESO", nullable = false)
    private BigDecimal peso;

    @Column(name = "PRECO", nullable = false)
    private BigDecimal preco;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

    public Item() {
        //EMPTY
    }

    public Item(Item request) {
        this.nome = request.nome;
        this.peso= request.peso;
        this.preco = request.preco;
        this.quantidade = request.quantidade;

    }

    public Item(String nome, BigDecimal peso, BigDecimal preco, Integer quantidade) {
        this.nome = nome;
        this.peso = peso;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getPesoIndividual() {
        return peso;
    }

    public BigDecimal getPesoTotal() {
        return peso.multiply(BigDecimal.valueOf(quantidade));
    }

    public void setPesoIndividual(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
                                            
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

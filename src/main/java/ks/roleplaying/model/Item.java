package ks.roleplaying.model;

import org.hibernate.annotations.Type;
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

    @Column(name = "NOME", unique = true, nullable = false)
    private String nome;

    @Column(name = "PESO")
    private BigDecimal peso;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "LORE")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String lore;

    @Column(name = "user")
    private String user;

    public Item() {
        /** Construtor Vazio */
    }

    public Item(String nome, BigDecimal peso, BigDecimal preco, String lore) {
        this.nome = nome;
        this.peso = peso;
        this.preco = preco;
        this.lore = lore;
    }

    public Item(Item request) {
        this.nome = request.nome;
        this.peso= request.peso;
        this.preco = request.preco;
        this.lore = request.lore;
        this.user = request.user;
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HABILIDADE", schema = "rpg")
//@Table(name = "HABILIDADE")
@EntityListeners(AuditingEntityListener.class)
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "CUSTO", nullable = false)
    private Integer custo;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    public Habilidade() {
        //EMPTY
    }

    public Habilidade(Habilidade request) {
        this.nome = request.nome;
        this.custo = request.custo;
        this.descricao = request.descricao;
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

    public Integer getCusto() {
        return custo;
    }

    public void setCusto(Integer custo) {
        this.custo = custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

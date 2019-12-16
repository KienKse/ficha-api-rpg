package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
//@Table(name = "PERICIA", schema = "rpg")
@Table(name = "PERICIA")
@EntityListeners(AuditingEntityListener.class)
public class Pericia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "HABILIDADE_CHAVE", nullable = false)
    private String habilidade_chave;

    @Column(name = "TREINADA", nullable = false)
    private String treinada;

    @Column(name = "CD")
//    private String penalidade_armadura;
    private Integer cd;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Pericia() {
        /** Construtor Vazio */
    }

    public Pericia(String nome, String habilidade_chave, String treinada, Integer cd, String descricao) {
        this.nome = nome;
        this.habilidade_chave = habilidade_chave;
        this.treinada= treinada;
        this.cd = cd;
        this.descricao = descricao;
    }

    public Pericia(Pericia request) {
        this.nome = request.nome;
        this.habilidade_chave = request.habilidade_chave;
        this.treinada = request.treinada;
        this.cd = request.cd;
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

    public String getHabilidade_chave() {
        return habilidade_chave;
    }

    public void setHabilidade_chave(String habilidade_chave) {
        this.habilidade_chave = habilidade_chave;
    }

    public String getTreinada() {
        return treinada;
    }

    public void setTreinada(String treinada) {
        this.treinada = treinada;
    }

    public Integer getCd() {
        return cd;
    }

    public void setCd(Integer cd) {
        this.cd = cd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

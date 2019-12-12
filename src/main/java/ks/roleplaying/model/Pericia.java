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

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "TIPO_DESCRICAO")
    private String tipoDescricao;

    @Column(name = "CD", nullable = false)
    private Integer cd;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    public Pericia() {
        //EMPTY
    }

    public Pericia(String nome, String tipo, String tipoDescricao, Integer cd, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.tipoDescricao = tipoDescricao;
        this.cd = cd;
        this.descricao = descricao;
    }

    public Pericia(Pericia request) {
        this.nome = request.nome;
        this.tipo = request.tipo;
        this.tipoDescricao = request.tipoDescricao;
        this.cd = request.cd;
        this.descricao = request.descricao;
    }

    public Pericia(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Pericia(String nome, String descricao, Integer cd) {
        this.nome = nome;
        this.descricao = descricao;
        this.cd = cd;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoDescricao() {
        return tipoDescricao;
    }

    public void setTipoDescricao(String tipoDescricao) {
        this.tipoDescricao = tipoDescricao;
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

package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
//@Table(name = "HABILIDADE", schema = "rpg", uniqueConstraints=@UniqueConstraint(columnNames={"NOME"}))
@Table(name = "HABILIDADE")
@EntityListeners(AuditingEntityListener.class)
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NOME", unique = true, nullable = false)
    private String nome;

    @Column(name = "TEMPO_EXECUCAO")
    private String tempoExecucao;

    @Column(name = "ALCANCE")
    private String alcance;

    @Column(name = "DURACAO")
    private String duracao;

    @Column(name = "CUSTO", columnDefinition = "int4range")
    private Integer custo;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "user")
    private String user;

    public Habilidade() {
        /** Construtor Vazio */
    }

    public Habilidade(String nome, String tempoExecucao, String alcance, String duracao, Integer custo, String descricao) {
        this.nome = nome;
        this.tempoExecucao = tempoExecucao;
        this.alcance = alcance;
        this.duracao = duracao;
        this.custo = custo;
        this.descricao = descricao;
    }

    public Habilidade(Habilidade request) {
        this.nome = request.nome;
        this.tempoExecucao = request.tempoExecucao;
        this.alcance = request.alcance;
        this.duracao = request.duracao;
        this.custo = request.custo;
        this.descricao = request.descricao;
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

    public String getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(String tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

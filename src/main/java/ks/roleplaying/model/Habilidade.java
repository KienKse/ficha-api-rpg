package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "HABILIDADE", schema = "rpg")
//@Table(name = "HABILIDADE")
@EntityListeners(AuditingEntityListener.class)
public class Habilidade {

    /**
        https://tsrd.fandom.com/pt-br/wiki/Magias_B#Brilho
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, unique = true)
    private String nome;

    @Column(name = "NIVEL", nullable = false)
    private String nivel;

    @Column(name = "TEMPO_EXECUCAO")
    private String tempoExecucao;

    @Column(name = "ALCANCE")
    private String alcance;

    @Column(name = "AREA")
    private String area;

    @Column(name = "EFEITO")
    private String efeito;

    @Column(name = "ALVO")
    private String alvo;

    @Column(name = "DURACAO")
    private String duracao;

    @Column(name = "TESTE_RESISTENCIA")
    private String testeResistencia;

    @Column(name = "CUSTO")
    private Integer custo;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "FONTE")
    private String fonte;

    public Habilidade() {
        /** Construtor Vazio */
    }

    public Habilidade(String nome, String nivel, String descricao) {
        this.nome = nome;
        this.nivel = nivel;
        this.descricao = descricao;
    }

    public Habilidade(Habilidade request) {
        this.nome = request.nome;
        this.nivel = request.nivel;
        this.tempoExecucao = request.tempoExecucao;
        this.alcance = request.alcance;
        this.area = request.area;
        this.efeito = request.efeito;
        this.alvo = request.alvo;
        this.duracao = request.duracao;
        this.testeResistencia = request.testeResistencia;
        this.custo = request.custo;
        this.descricao = request.descricao;
        this.fonte = request.fonte;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public String getAlvo() {
        return alvo;
    }

    public void setAlvo(String alvo) {
        this.alvo = alvo;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getTesteResistencia() {
        return testeResistencia;
    }

    public void setTesteResistencia(String testeResistencia) {
        this.testeResistencia = testeResistencia;
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

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
}

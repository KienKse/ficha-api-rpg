package ks.roleplaying.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSONAGEM", schema = "rpg")
//@Table(name = "PERSONAGEM")
@EntityListeners(AuditingEntityListener.class)
public class Personagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 65)
    private String nome;

    @Column(name = "RACA", nullable = false, length = 45)
    private String raca;

    @Column(name = "CLASSE", nullable = false, length = 45)
    private String classe;

    @JoinColumn(name = "ID_PERSONAGEM_FK", referencedColumnName = "ID")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InventarioItem> inventarioItens;

    @OneToOne
    @JoinColumn(name = "ID_ATRIBUTO_FK", referencedColumnName = "ID")
    private Atributos atributos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="personagem_habilidades", joinColumns=
            {@JoinColumn(name="ID_PERSONAGEM")}, inverseJoinColumns=
            {@JoinColumn(name="ID_HABILIDADE")})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Habilidade> habilidades;

    @Column(name = "SANIDADE", nullable = false)
    private Integer sanidade = 0;

    @Column(name = "GOLD", nullable = false)
    private Integer gold = 0;

    @Transient
    private BigDecimal cargaMaxima = BigDecimal.ZERO;

    @Transient
    private BigDecimal cargaAtual = BigDecimal.ZERO;

    public Personagem() {
        /** Construtor Vazio */
    }

    public Personagem(Personagem request) {
        this.nome = request.nome;
        this.raca = request.raca;
        this.classe = request.classe;
        this.atributos = request.atributos;
        this.habilidades = request.habilidades;
        this.inventarioItens = request.inventarioItens;
    }

    public BigDecimal getCargaMaxima() {
        return BigDecimal.valueOf(atributos.getForca() * 3);
    }

    public BigDecimal getCargaAtual() {
        cargaAtual = BigDecimal.ZERO;
        this.inventarioItens.forEach(this::atribuirCarga);
        return cargaAtual;
    }

    private void atribuirCarga(InventarioItem inventarioItem) {
        if(inventarioItem.getItem().getPeso() != null)
            cargaAtual = cargaAtual.add(inventarioItem.getItem().getPeso().multiply(BigDecimal.valueOf(inventarioItem.getQuantidade())));
    }

    public List<Habilidade> getHabilidades() {
        if(habilidades == null) {
            habilidades = new ArrayList<>();
        }
        return habilidades;
    }

    public List<InventarioItem> getInventarioItens() {
        if(inventarioItens == null) {
            inventarioItens = new ArrayList<>();
        }
        return inventarioItens;
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setInventarioItens(List<InventarioItem> inventarioItens) {
        this.inventarioItens = inventarioItens;
    }

    public Atributos getAtributos() {
        return atributos;
    }

    public void setAtributos(Atributos atributos) {
        this.atributos = atributos;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public Integer getSanidade() {
        return sanidade;
    }

    public void setSanidade(Integer sanidade) {
        this.sanidade = sanidade;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public void setCargaMaxima(BigDecimal cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public void setCargaAtual(BigDecimal cargaAtual) {
        this.cargaAtual = cargaAtual;
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", classe='" + classe + '\'' +
                ", sanidade=" + sanidade +
                ", gold=" + gold +
                '}';
    }
}
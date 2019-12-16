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
//@Table(name = "PERSONAGEM", schema = "rpg")
@Table(name = "PERSONAGEM")
@EntityListeners(AuditingEntityListener.class)
public class Personagem implements Serializable {
    /**
     * Ícaro Santana
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 65)
    private String nome;

    @Column(name = "NIVEL", nullable = false)
    private Integer nivel;

    @Column(name = "RACA", nullable = false, length = 45)
    private String raca;

    @Column(name = "CLASSE", nullable = false, length = 45)
    private String classe;

    @JoinColumn(name = "ID_TENDENCIA_FK", referencedColumnName = "ID")
    @ManyToOne
    private Tendencia tendencia;

    @Transient
    private BigDecimal cargaMaxima;

    @Transient
    private BigDecimal cargaAtual;

    @JoinColumn(name = "ID_PERSONAGEM_FK", referencedColumnName = "ID")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<InventarioItem> inventarioItens;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="personagem_talentos", joinColumns=
            {@JoinColumn(name="ID_PERSONAGEM")}, inverseJoinColumns=
            {@JoinColumn(name="ID_TALENTO")})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Talento> talentos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name="personagem_pericicas", joinColumns=
            {@JoinColumn(name="ID_PERSONAGEM")}, inverseJoinColumns=
            {@JoinColumn(name="ID_PERICIA")})
    private List<Pericia> pericias;

    @OneToOne
    @JoinColumn(name = "ID_ATRIBUTO_FK", referencedColumnName = "ID")
    private Atributos atributos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="personagem_habilidades", joinColumns=
            {@JoinColumn(name="ID_PERSONAGEM")}, inverseJoinColumns=
            {@JoinColumn(name="ID_HABILIDADE")})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Habilidade> habilidades;

    @Transient
    private boolean gerarAtributosETendencia;

    public Personagem() {
        /** Construtor Vazio */
    }

    public Personagem(Personagem request) {
        this.nome = request.nome;
        this.nivel = request.nivel;
        this.raca = request.raca;
        this.classe = request.classe;
        this.atributos = request.atributos;
        this.habilidades = request.habilidades;
        this.pericias = request.pericias;
        this.talentos = request.talentos;
        this.inventarioItens = request.inventarioItens;
        this.gerarAtributosETendencia = request.gerarAtributosETendencia;
    }

    public boolean isGerarAtributosETendencia() {
        return gerarAtributosETendencia;
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
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

    public Atributos getAtributos() {
        return atributos;
    }

    public void setAtributos(Atributos atributos) {
        this.atributos = atributos;
    }

    public List<Habilidade> getHabilidades() {
        if(habilidades == null) {
            habilidades = new ArrayList<>();
        }
        return habilidades;
    }

    public Tendencia getTendencia() {
        return tendencia;
    }

    public void setTendencia(Tendencia tendencia) {
        this.tendencia = tendencia;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public List<InventarioItem> getInventarioItens() {
        if(inventarioItens == null) {
            inventarioItens = new ArrayList<>();
        }
        return inventarioItens;
    }

    public void setInventarioItens(List<InventarioItem> inventarioItens) {
        this.inventarioItens = inventarioItens;
    }

    public List<Talento> getTalentos() {
        if(talentos == null) {
            talentos = new ArrayList<>();
        }
        return talentos;
    }

    public void setTalentos(List<Talento> talentos) {
        this.talentos = talentos;
    }

    public List<Pericia> getPericias() {
        if(pericias == null) {
            pericias = new ArrayList<>();
        }
        return pericias;
    }

    public void setPericias(List<Pericia> pericias) {
        this.pericias = pericias;
    }
}
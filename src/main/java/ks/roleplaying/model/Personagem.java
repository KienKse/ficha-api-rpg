package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSONAGEM", schema = "rpg")
//@Table(name = "PERSONAGEM")
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

//    @Column(name = "PESO_TOTAL", nullable = false, precision=4, scale=2)
//    private BigDecimal pesoTotal;

    @JoinColumn(name = "ID_PERSONAGEM_FK", referencedColumnName = "ID")
    @OneToMany
    private List<InventarioItem> inventarioItens;

    @OneToOne
    @JoinColumn(name = "ID_ATRIBUTO_FK", referencedColumnName = "ID")
    private Atributos atributos;

    @ManyToMany
    @JoinTable(name="personagem_habilidades", joinColumns=
            {@JoinColumn(name="ID_PERSONAGEM")}, inverseJoinColumns=
            {@JoinColumn(name="ID_HABILIDADE")})
    private List<Habilidade> habilidades;

    public Personagem() {
        //EMPTY
    }

    public Personagem(Personagem request) {
        this.nome = request.nome;
        this.nivel = request.nivel;
        this.raca = request.raca;
        this.classe = request.classe;
        this.atributos = request.atributos;
        this.habilidades = request.habilidades;
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
}
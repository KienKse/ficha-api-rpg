package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
//@Table(name = "PERSONAGEM", schema = "rpg")
@Table(name = "PERSONAGEM")
@EntityListeners(AuditingEntityListener.class)
public class Personagem implements Serializable {
    /**
     * Ícaro Santana
     */

    /*
        2 - RESISTÊNCIAS
        Fort
        Ref
        Von
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

    @OneToOne
    @JoinColumn(name = "ID_INVENTARIO_FK", referencedColumnName = "ID")
    private Inventario inventario;

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
}
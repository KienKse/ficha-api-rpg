package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
//@Table(name = "ATRIBUTOS", schema = "rpg")
@Table(name = "ATRIBUTOS")
@EntityListeners(AuditingEntityListener.class)
public class Atributos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ATT_FOR", nullable = false)
    private Integer forca;

    @Column(name = "ATT_DES", nullable = false)
    private Integer destreza;

    @Column(name = "ATT_CON", nullable = false)
    private Integer constituicao;

    @Column(name = "ATT_INT", nullable = false)
    private Integer inteligencia;

    @Column(name = "ATT_SAB", nullable = false)
    private Integer sabedoria;

    @Column(name = "ATT_CAR", nullable = false)
    private Integer carisma;

    public Atributos() {
        //Empty
    }

    public Atributos(Integer forca, Integer destreza, Integer constituicao, Integer inteligencia, Integer sabedoria, Integer carisma) {
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.sabedoria = sabedoria;
        this.carisma = carisma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getDestreza() {
        return destreza;
    }

    public void setDestreza(Integer destreza) {
        this.destreza = destreza;
    }

    public Integer getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(Integer constituicao) {
        this.constituicao = constituicao;
    }

    public Integer getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(Integer inteligencia) {
        this.inteligencia = inteligencia;
    }

    public Integer getSabedoria() {
        return sabedoria;
    }

    public void setSabedoria(Integer sabedoria) {
        this.sabedoria = sabedoria;
    }

    public Integer getCarisma() {
        return carisma;
    }

    public void setCarisma(Integer carisma) {
        this.carisma = carisma;
    }
}

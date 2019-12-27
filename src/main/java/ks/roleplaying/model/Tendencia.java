package ks.roleplaying.model;

import ks.roleplaying.enums.TendenciaEnum;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TENDENCIA", schema = "rpg")
//@Table(name = "TENDENCIA")
@EntityListeners(AuditingEntityListener.class)
public class Tendencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(length = 2)
    private TendenciaEnum tendencia;

    public Tendencia() {
        /** Construtor Vazio */
    }

    public Tendencia(TendenciaEnum tendencia) {
        this.titulo = tendencia.getTendencia();
        this.tendencia = tendencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TendenciaEnum getTendencia() {
        return tendencia;
    }

    public void setTendencia(TendenciaEnum tendencia) {
        this.tendencia = tendencia;
    }
}

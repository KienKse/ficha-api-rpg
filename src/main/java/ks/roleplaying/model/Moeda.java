package ks.roleplaying.model;

import ks.roleplaying.enums.MoedaEnum;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "MOEDA", schema = "rpg")
//@Table(name = "MOEDA")
@EntityListeners(AuditingEntityListener.class)
public class Moeda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "TIBAR", nullable = false)
    private int tibar;

    @Column(name = "TIBAR_PRATA", nullable = false)
    private int tibarPrata;

    @Column(name = "TIBAR_OURO", nullable = false)
    private int tibarOuro;

    @Column(name = "TIBAR_PLATINA", nullable = false)
    private int tibarPlatina;

    public Moeda() {
        /** Construtor Vazio */
    }

    public Moeda(int tibar, int tibarPrata, int tibarOuro, int tibarPlatina) {
        this.tibar = tibar;
        this.tibarPrata = tibarPrata;
        this.tibarOuro = tibarOuro;
        this.tibarPlatina = tibarPlatina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTibar() {
        return tibar;
    }

    public void setTibar(int tibar) {
        this.tibar = tibar;
    }

    public int getTibarPrata() {
        return tibarPrata;
    }

    public void setTibarPrata(int tibarPrata) {
        this.tibarPrata = tibarPrata;
    }

    public int getTibarOuro() {
        return tibarOuro;
    }

    public void setTibarOuro(int tibarOuro) {
        this.tibarOuro = tibarOuro;
    }

    public int getTibarPlatina() {
        return tibarPlatina;
    }

    public void setTibarPlatina(int tibarPlatina) {
        this.tibarPlatina = tibarPlatina;
    }

    @Override
    public String toString() {
        return "Moeda{" +
                "tibar=" + tibar +
                ", tibarPrata=" + tibarPrata +
                ", tibarOuro=" + tibarOuro +
                ", tibarPlatina=" + tibarPlatina +
                '}';
    }
}

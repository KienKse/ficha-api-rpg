package ks.roleplaying.model;

import ks.roleplaying.enums.MoedaEnum;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
//@Table(name = "MOEDA", schema = "rpg")
@Table(name = "MOEDA")
@EntityListeners(AuditingEntityListener.class)
public class Moeda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "QUANTIDADE", nullable = false)
    private BigDecimal quantidade;

    @Enumerated(EnumType.STRING)
    @Column(length = 2, nullable = false)
    private MoedaEnum moeda;

    public Moeda() {
        /** Construtor Vazio */
    }

    public Moeda(MoedaEnum moeda, BigDecimal quantidade) {
        this.quantidade = quantidade;
        this.moeda = moeda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public MoedaEnum getMoeda() {
        return moeda;
    }

    public void setMoeda(MoedaEnum moeda) {
        this.moeda = moeda;
    }

}

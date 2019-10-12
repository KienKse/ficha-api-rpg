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

    @Column(name = "ATT_VON", nullable = false)
    private Integer vontade;
}

package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
//@Table(name = "INVENTARIO", schema = "rpg")
@Table(name = "INVENTARIO")
@EntityListeners(AuditingEntityListener.class)
public class Inventario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "ID_PERSONAGEM_FK", referencedColumnName = "ID")
//    private Personagem personagem;

    @Column(name = "PESO_TOTAL", nullable = false, precision=4, scale=2)
    private BigDecimal pesoTotal;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inventario")
//    private List<Item> items;

    @ManyToMany
    @JoinTable(name="inventario_item", joinColumns=
            {@JoinColumn(name="ID_INVENTARIO")}, inverseJoinColumns=
            {@JoinColumn(name="ID_ITEM")})
    private List<Item> itens;

}

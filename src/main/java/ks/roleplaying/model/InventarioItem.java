package ks.roleplaying.model;

import ks.roleplaying.repository.InventarioRepository;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "INVENTARIO_ITEM", schema = "rpg")
@Table(name = "INVENTARIO_ITEM")
@EntityListeners(AuditingEntityListener.class)
public class InventarioItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_ITEM_FK", referencedColumnName = "ID", nullable = false)
    private Item item;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    public InventarioItem() {
        /** Construtor Vazio */
    }


    public InventarioItem(Item item, Integer quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public InventarioItem(InventarioItem request) {
        this.item =  request.item;
        this.quantidade = request.quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}

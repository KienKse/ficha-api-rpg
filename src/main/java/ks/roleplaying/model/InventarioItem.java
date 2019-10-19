package ks.roleplaying.model;

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

    public InventarioItem(Item item, Integer quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public InventarioItem() {
        //EMPTY
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

    /*
    public void addItem(Item item) {
        if(itens == null) {
            this.itens = new ArrayList<>();
        } else {
            Item itemExistente = itens.stream()
                    .filter(itemLista -> item.getNome().equals(itemLista.getNome()))
                    .findAny()
                    .orElse(null);
            this.itens.add(item);
        }
    }

    public Item getItemByName(String nome) {
        return itens.stream()
                .filter(item -> nome.equals(item.getNome()))
                .findAny()
                .orElse(null);
    }

    public void removeItem(Item item) {
        try {
            Item itemExistente = itens.stream()
                    .filter(itemLista -> item.getNome().equals(itemLista.getNome()))
                    .findAny()
                    .orElse(null);
            itens.remove(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeItemByName(String nome) {
        try {
            Item itemExistente = itens.stream()
                    .filter(itemLista -> nome.equals(itemLista.getNome()))
                    .findAny()
                    .orElse(null);
            itens.remove(itemExistente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/


}

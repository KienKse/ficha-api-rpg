package ks.roleplaying.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @ManyToMany
    @JoinTable(name="inventario_item", joinColumns=
            {@JoinColumn(name="ID_INVENTARIO")}, inverseJoinColumns=
            {@JoinColumn(name="ID_ITEM")})
    private List<Item> itens;

    public void addItem(Item item) {
        if(itens == null) {
            this.itens = new ArrayList<>();
        } else {
            Item itemExistente = itens.stream()
                    .filter(itemLista -> item.getNome().equals(itemLista.getNome()))
                    .findAny()
                    .orElse(null);
            if(itemExistente == null) {
                this.itens.add(item);
            } else {
                itemExistente.setQuantidade(itemExistente.getQuantidade()+1);
            }
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
            if(itemExistente.getQuantidade()-1 == 0) {
                itens.remove(item);
            } else {
                itemExistente.setQuantidade(itemExistente.getQuantidade()-1);
            }
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
            if(itemExistente.getQuantidade()-1 == 0) {
                itens.remove(itemExistente);
            } else {
                itemExistente.setQuantidade(itemExistente.getQuantidade()-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

package ks.roleplaying.service;

import ks.roleplaying.controller.ResourceNotFoundException;
import ks.roleplaying.model.Item;
import ks.roleplaying.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAll() {
        LOGGER.debug("Obtendo itens");
        return itemRepository.findAll();
    }

    public Item addNewItemCarga(String nome, BigDecimal peso, BigDecimal preco, String lore) {
        Item item = getItemByNome(nome);
        //TODO: FIX REFATORAR
        if(item == null) {
            return itemRepository.save(new Item(nome, peso, preco, lore));
        }
        return null;
    }

     public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("ITEM", "id", itemId));
    }

    public Item getItemByNome(String nome) {
        return itemRepository.findAll()
        .stream()
        .filter(itemLista -> nome.equals(itemLista.getNome()))
        .findAny()
        .orElse(null);
    }

    public void deleteItem(Long itemId) {
        Item item = getItemById(itemId);

        itemRepository.delete(item);
    }

    //TODO TESTE DEV
    public void deleteAllItens() {
        itemRepository.deleteAll();
    }

    public ResponseEntity<Object> updateItem(Item itemDetails, Long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if(!itemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        itemDetails.setId(itemId);

        itemRepository.save(itemDetails);

        return ResponseEntity.noContent().build();
    }

}
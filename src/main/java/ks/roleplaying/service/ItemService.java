package ks.roleplaying.service;

import ks.roleplaying.controller.ResourceNotFoundException;
import ks.roleplaying.model.Item;
import ks.roleplaying.repository.ItemRepository;
import ks.roleplaying.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public Item addNewItem(Item request) {
        Item item = new Item(request);

        return itemRepository.save(item);
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("ITEM", "id", itemId));
    }

    public List<Item> getItemByName(String nome) {
        return itemRepository.findByNome(nome);
    }

    public void deleteItem(Long itemId) {
        Item item = getItemById(itemId);

        itemRepository.delete(item);
    }

    // TODO: UPDATE
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
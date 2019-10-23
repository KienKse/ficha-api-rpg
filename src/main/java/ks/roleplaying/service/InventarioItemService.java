package ks.roleplaying.service;

import ks.roleplaying.controller.ResourceNotFoundException;
import ks.roleplaying.model.InventarioItem;
import ks.roleplaying.repository.InventarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventarioItemService.class);

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<InventarioItem> getAll() {
        LOGGER.debug("Obtendo itens");
        return inventarioRepository.findAll();
    }

    public InventarioItem addNewInventarioItem(InventarioItem request) {
        InventarioItem inventarioItem = new InventarioItem(request);

        return inventarioRepository.save(inventarioItem);
    }

    public InventarioItem getInventarioItemById(Long inventarioItemId) {
        return inventarioRepository.findById(inventarioItemId)
                .orElseThrow(() -> new ResourceNotFoundException("INVENTARIO_ITEM", "id", inventarioItemId));
    }

    public void deleteInventarioItem(Long inventarioItemId) {
        InventarioItem item = getInventarioItemById(inventarioItemId);

        inventarioRepository.delete(item);
    }

    public void save(InventarioItem inventarioItem) {
        this.inventarioRepository.save(inventarioItem);
    }

    public ResponseEntity<Object> updateInventarioItem(InventarioItem inventarioItemDetails, Long inventarioItemId) {
        Optional<InventarioItem> inventarioItemOptional = inventarioRepository.findById(inventarioItemId);

        if(!inventarioItemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        inventarioItemDetails.setId(inventarioItemId);

        inventarioRepository.save(inventarioItemDetails);

        return ResponseEntity.noContent().build();
    }

}
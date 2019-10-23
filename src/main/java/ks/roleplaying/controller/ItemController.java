package ks.roleplaying.controller;

import ks.roleplaying.model.InventarioItem;
import ks.roleplaying.model.Item;
import ks.roleplaying.model.Personagem;
import ks.roleplaying.repository.InventarioRepository;
import ks.roleplaying.service.ItemService;
import ks.roleplaying.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Get ALL - Item
    @GetMapping("")
    public List<Item> getAll() {
        return itemService.getAll();
    }

    // Add Item
    @PostMapping("/add")
    public ResponseEntity addNewItem(@Valid @RequestBody Item request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.addNewItem(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // AddAll Item
    @PostMapping("/addList")
    public void addListItens(@Valid @RequestBody List<Item> itens) {
            itens.forEach(this::adicionarItens);
    }

    private void adicionarItens(Item item) {
        try {
             this.itemService.addNewItem(item);
        } catch (Exception e) {
            System.out.println("Exceção: " + e.getLocalizedMessage());
        }
    }

    // Get - Item
    @GetMapping("/{id}")
    public @ResponseBody
    Item getItemById(@PathVariable(value = "id") Long itemId) {
        return itemService.getItemById(itemId);
    }

    // Delete - Item
    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long itemId) {
//        Item item = itemService.getItemById(itemId);
        itemService.deleteItem(itemId);

        return ResponseEntity.ok().build();
    }

    // Update Item
    @PutMapping("/upd/{id}")
    public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable Long id){

        Optional<Item> itemOptional = Optional.ofNullable(itemService.getItemById(id));

        if (!itemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        itemService.updateItem(item, id);

        return ResponseEntity.noContent().build();
    }

}


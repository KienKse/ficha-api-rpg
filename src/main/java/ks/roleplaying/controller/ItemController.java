package ks.roleplaying.controller;

import ks.roleplaying.model.Item;
import ks.roleplaying.service.ItemService;
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
    @GetMapping("/all")
    public List<Item> getAll() {
        return itemService.getAll();
    }

    /*
[
    {
	    "nome": "Adaga da Morte",
	    "peso": 1.2,
	    "preco": 500,
	    "quantidade": 1
    },
    {
	    "nome": "Poção da Vida",
	    "peso": 0.2,
	    "preco": 70,
	    "quantidade": 1
    },
    {
        "nome": "Poção da Mana",
        "peso": 0.50,
        "preco": 100,
        "quantidade": 1
    }
]

     */

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
    Item getItemById(@PathVariable(value = "id") Long carId) {
        return itemService.getItemById(carId);
    }

//     Get - Item - ByName
    public List<Item> getItemByNome(String nome) {
        return itemService.getItemByName(nome);
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


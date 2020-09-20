package ks.roleplaying.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
@Api(value = "Item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("")
    @ApiOperation(value = "Obter todos os itens ", authorizations = @Authorization(value = "Bearer"))
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @PostMapping("/add")
    @ApiOperation(value = "Adicionar um item", authorizations = @Authorization(value = "Bearer"))
    public ResponseEntity addNewItem(@Valid @RequestBody Item request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.add(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/addList")
    @ApiOperation(value = "Adicionar uma lista itens")
    public void addListItens(@Valid @RequestBody List<Item> itens) {
            itens.forEach(this::adicionarItens);
    }

    private void adicionarItens(Item item) {
        try {
             this.itemService.addNewItemCarga(item.getNome(), item.getPeso(), item.getPreco(), item.getLore());
        } catch (Exception e) {
            System.out.println("Exceção: " + e.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter um item pelo ID")
    public @ResponseBody
    Item getItemById(@PathVariable(value = "id") Long itemId) {
        return itemService.getItemById(itemId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar um item pelo ID")
    public @ResponseBody
    ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long itemId) {
        itemService.deleteItem(itemId);

        return ResponseEntity.ok().build();
    }

//    @DeleteMapping("/del")
//    @ApiOperation(value = "Deletar todos os itens")
//    public @ResponseBody ResponseEntity<?> deleteAllItem() {
//        itemService.deleteAllItens();
//        return ResponseEntity.ok().build();
//    }

    @PutMapping("/upd/{id}")
    @ApiOperation(value = "Atualizar um item pelo ID")
    public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable Long id){

        Optional<Item> itemOptional = Optional.ofNullable(itemService.getItemById(id));

        if (!itemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        itemService.updateItem(item, id);

        return ResponseEntity.noContent().build();
    }

}


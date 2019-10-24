package ks.roleplaying.controller;

import ks.roleplaying.model.InventarioItem;
import ks.roleplaying.model.Item;
import ks.roleplaying.model.Personagem;
import ks.roleplaying.service.InventarioItemService;
import ks.roleplaying.service.ItemService;
import ks.roleplaying.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/inventario")
public class InventarioItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PersonagemService personagemService;

    @Autowired
    private InventarioItemService inventarioItemService;

    @PostMapping("/addItemPersonagem/{id}")
    public ResponseEntity addNewInventarioItem(@Valid @RequestBody InventarioItem request, @PathVariable(value = "id") Long personagemId) {
        return adicionarInventarioItem(request, personagemId);
    }

    private ResponseEntity adicionarInventarioItem(InventarioItem request, Long personagemId) {
        Personagem personagem = personagemService.getPersonagemById(personagemId);
        Item item = itemService.getItemByNome(request.getItem().getNome());
        if(personagem != null && item != null) {
            InventarioItem inventarioItem =  personagem.getInventarioItens()
            .stream()
            .filter(inventario-> inventario.getItem() == item)
            .findAny()
            .orElse(null);
            try {
                if(inventarioItem != null) {
                    if(request.isAdicionar()) {
                        inventarioItem.setQuantidade(inventarioItem.getQuantidade() + request.getQuantidade());
                        inventarioItemService.save(inventarioItem);
                    } else {
                        inventarioItem.setQuantidade(inventarioItem.getQuantidade() - request.getQuantidade());
                        if (inventarioItem.getQuantidade() <= 0) {
                            inventarioItemService.deleteInventarioItem(inventarioItem.getId());
                        } else {
                            inventarioItemService.save(inventarioItem);
                        }
                    }
                    return ResponseEntity.status(HttpStatus.OK).body("Operação realizada com sucesso");
                } else {
                    InventarioItem novoInventarioItem = new InventarioItem(item, request.getQuantidade() != null ? request.getQuantidade() : 1);
                    inventarioItemService.save(novoInventarioItem);
                    personagem.getInventarioItens().add(novoInventarioItem);
                    return ResponseEntity.status(HttpStatus.OK).body(personagemService.updatePersonagem(personagem, personagemId));
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu algum erro");
    }

}
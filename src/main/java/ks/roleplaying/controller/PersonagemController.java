package ks.roleplaying.controller;

import ks.roleplaying.model.Personagem;
import ks.roleplaying.service.PersonagemService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    // Get ALL - Personagem
    @GetMapping("/all")
    public List<Personagem> getAll() {
        return personagemService.getAll();
    }

    /*

    {
	    "nome": "Roger",
	    "classe": "Mago",
	    "raca": "Humano",
	    "nivel": 1
    }

     */

    // Add Personagem
    @PostMapping("/add")
    public ResponseEntity addNewPersonagem(@Valid @RequestBody Personagem request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(personagemService.addNewPersonagem(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Get - Personagem
    @GetMapping("/{id}")
    public @ResponseBody
    Personagem getPersonagemById(@PathVariable(value = "id") Long carId) {
        return personagemService.getPersonagemById(carId);
    }

    // Delete - Personagem
    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> deletePersonagem(@PathVariable(value = "id") Long personagemId) {
//        Personagem personagem = personagemService.getPersonagemById(personagemId);
        personagemService.deletePersonagem(personagemId);

        return ResponseEntity.ok().build();
    }

    // Update Personagem
    @PutMapping("/upd/{id}")
    public ResponseEntity<Personagem> updatePersonagem(@RequestBody Personagem personagem, @PathVariable Long id){

        Optional<Personagem> personagemOptional = Optional.ofNullable(personagemService.getPersonagemById(id));

        if (!personagemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        personagemService.updatePersonagem(personagem, id);

        return ResponseEntity.noContent().build();
    }

}


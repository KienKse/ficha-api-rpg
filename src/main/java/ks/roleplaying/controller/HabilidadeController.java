package ks.roleplaying.controller;

import ks.roleplaying.model.Habilidade;
import ks.roleplaying.service.HabilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habilidade")
public class HabilidadeController {

    @Autowired
    private HabilidadeService habilidadeService;

    // Get ALL - Habilidade
    @GetMapping("")
    public List<Habilidade> getAll() {
        return habilidadeService.getAll();
    }

    // Add Habilidade
    @PostMapping("/add")
    public ResponseEntity addNewHabilidade(@Valid @RequestBody Habilidade request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(habilidadeService.addNewHabilidade(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // AddAll Habilidade
    @PostMapping("/addList")
    public void addListHabilidade(@RequestBody List<Habilidade> habilidades) {
        habilidades.forEach(this::adicionarHabilidade);
    }

    private void adicionarHabilidade(Habilidade habilidade) {
        try {
            this.habilidadeService.addNewHabilidade(habilidade);
        } catch (Exception e) {
            System.out.println("Exceção: " + e.getLocalizedMessage());
        }
    }

    // Get - Habilidade
    @GetMapping("/{id}")
    public @ResponseBody
    Habilidade getHabilidadeById(@PathVariable(value = "id") Long habilidadeId) {
        return habilidadeService.getHabilidadeById(habilidadeId);
    }

    // Delete - Habilidade
    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> deleteHabilidade(@PathVariable(value = "id") Long habilidadeId) {
//        Habilidade habilidade = habilidadeService.getHabilidadeById(habilidadeId);
        habilidadeService.deleteHabilidade(habilidadeId);

        return ResponseEntity.ok().build();
    }

    // Update Habilidade
    @PutMapping("/upd/{id}")
    public ResponseEntity<Habilidade> updateHabilidade(@RequestBody Habilidade habilidade, @PathVariable Long id){

        Optional<Habilidade> habilidadeOptional = Optional.ofNullable(habilidadeService.getHabilidadeById(id));

        if (!habilidadeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        habilidadeService.updateHabilidade(habilidade, id);

        return ResponseEntity.noContent().build();
    }

}


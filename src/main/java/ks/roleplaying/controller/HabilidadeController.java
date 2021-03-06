package ks.roleplaying.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Habilidade")
public class HabilidadeController {

    @Autowired
    private HabilidadeService habilidadeService;

    @GetMapping("")
    @ApiOperation(value = "Obter todos as habilidades")
    public List<Habilidade> getAll() {
        return habilidadeService.getAll();
    }

    @PostMapping("/add")
    @ApiOperation(value = "Adicionar uma habilidade")
    public ResponseEntity addNewHabilidade(@Valid @RequestBody Habilidade request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(habilidadeService.addNewHabilidade(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/addList")
    @ApiOperation(value = "Adicionar uma lista de habilidades")
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

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter uma habilidade pelo ID")
    public @ResponseBody Habilidade getHabilidadeById(@PathVariable(value = "id") Long habilidadeId) {
        return habilidadeService.getHabilidadeById(habilidadeId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar uma habilidade pelo ID")
    public @ResponseBody ResponseEntity<?> deleteHabilidade(@PathVariable(value = "id") Long habilidadeId) {
        habilidadeService.deleteHabilidade(habilidadeId);

        return ResponseEntity.ok().build();
    }

//    @DeleteMapping("/del")
//    @ApiOperation(value = "Deletar todos os itens")
//    public @ResponseBody ResponseEntity<?> deleteAllItem() {
//        habilidadeService.deleteAllHabilidade();
//        return ResponseEntity.ok().build();
//    }

    @PutMapping("/upd/{id}")
    @ApiOperation(value = "Atualizar uma habilidade pelo ID")
    public ResponseEntity<Habilidade> updateHabilidade(@RequestBody Habilidade habilidade, @PathVariable Long id){

        Optional<Habilidade> habilidadeOptional = Optional.ofNullable(habilidadeService.getHabilidadeById(id));

        if (!habilidadeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        habilidadeService.updateHabilidade(habilidade, id);

        return ResponseEntity.noContent().build();
    }

}


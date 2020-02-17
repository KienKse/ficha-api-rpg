package ks.roleplaying.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ks.roleplaying.model.Talento;
import ks.roleplaying.service.TalentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/talento")
@Api(value = "Talento")
public class TalentoController {

    @Autowired
    private TalentoService talentoService;

    @GetMapping("")
    @ApiOperation(value = "Obter todos as talentos")
    public List<Talento> getAll() {
        return talentoService.getAll();
    }

    @PostMapping("/add")
    @ApiOperation(value = "Adicionar um talento")
    public ResponseEntity addNewTalento(@Valid @RequestBody Talento request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(talentoService.addNewTalento(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/addList")
    @ApiOperation(value = "Adicionar uma lista de talentos")
    public void addListTalento(@RequestBody List<Talento> talentos) {
        talentos.forEach(this::adicionarTalento);
    }

    private void adicionarTalento(Talento talento) {
        try {
            this.talentoService.addNewTalento(talento);
        } catch (Exception e) {
            System.out.println("Exceção: " + e.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter um talento pelo ID")
    public @ResponseBody Talento getTalentoById(@PathVariable(value = "id") Long talentoId) {
        return talentoService.getTalentoById(talentoId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar um talento pelo ID")
    public @ResponseBody ResponseEntity<?> deleteTalento(@PathVariable(value = "id") Long talentoId) {
//        Talento talento = talentoService.getTalentoById(talentoId);
        talentoService.deleteTalento(talentoId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/upd/{id}")
    @ApiOperation(value = "Atualizar um talento pelo ID")
    public ResponseEntity<Talento> updateTalento(@RequestBody Talento talento, @PathVariable Long id){

        Optional<Talento> talentoOptional = Optional.ofNullable(talentoService.getTalentoById(id));

        if (!talentoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        talentoService.updateTalento(talento, id);

        return ResponseEntity.noContent().build();
    }

}


package ks.roleplaying.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ks.roleplaying.model.Pericia;
import ks.roleplaying.service.PericiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pericia")
@Api(value = "Pericia")
public class PericiaController {

    @Autowired
    private PericiaService periciaService;

    @GetMapping("")
    @ApiOperation(value = "Obter todos as pericias")
    public List<Pericia> getAll() {
        return periciaService.getAll();
    }

    @PostMapping("/add")
    @ApiOperation(value = "Adicionar uma pericia")
    public ResponseEntity addNewPericia(@Valid @RequestBody Pericia request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(periciaService.addNewPericia(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/addList")
    @ApiOperation(value = "Adicionar uma lista de pericias")
    public void addListPericia(@RequestBody List<Pericia> pericias) {
        pericias.forEach(this::adicionarPericia);
    }

    private void adicionarPericia(Pericia pericia) {
        try {
            this.periciaService.addNewPericia(pericia);
        } catch (Exception e) {
            System.out.println("Exceção: " + e.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter uma pericia pelo ID")
    public @ResponseBody Pericia getPericiaById(@PathVariable(value = "id") Long periciaId) {
        return periciaService.getPericiaById(periciaId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar uma pericia pelo ID")
    public @ResponseBody ResponseEntity<?> deletePericia(@PathVariable(value = "id") Long periciaId) {
//        Pericia pericia = periciaService.getPericiaById(periciaId);
        periciaService.deletePericia(periciaId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/upd/{id}")
    @ApiOperation(value = "Atualizar uma pericia pelo ID")
    public ResponseEntity<Pericia> updatePericia(@RequestBody Pericia pericia, @PathVariable Long id){

        Optional<Pericia> periciaOptional = Optional.ofNullable(periciaService.getPericiaById(id));

        if (!periciaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        periciaService.updatePericia(pericia, id);

        return ResponseEntity.noContent().build();
    }

}


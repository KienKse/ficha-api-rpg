package ks.roleplaying.controller;

import ks.roleplaying.model.Usuario;
import ks.roleplaying.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/add")
    @Transactional
    public ResponseEntity save(@Valid @RequestBody Usuario request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/del")
    public @ResponseBody
    ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long usuarioId) {
        usuarioService.deleteById(usuarioId);

        return ResponseEntity.ok().build();
    }

}


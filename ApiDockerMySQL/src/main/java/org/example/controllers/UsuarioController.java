package org.example.controllers;

import org.example.models.Usuario;
import org.example.services.UsuarioService;
import org.example.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.API_CLIENT)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/Postar")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        Usuario savedUser = usuarioService.save(usuario);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/Listar")
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/Buscar/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> updated = usuarioService.update(id, usuario);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/Deletar/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

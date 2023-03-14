package com.dev.manicure.controller;

import com.dev.manicure.entity.Usuario;
import com.dev.manicure.exception.ResourceNotFoundException;
import com.dev.manicure.repository.UsuarioRepository;
import com.dev.manicure.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping(value = "/usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuarioHolder) {
        Usuario usuario = usuarioService.cadastrarUsuario(usuarioHolder);
        return ResponseEntity.created(URI.create("/usuario" + usuario.getId())).body(usuario);
    }

    @PutMapping(value = "/usuario/{uuid}")
    public ResponseEntity<Usuario>  atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario = usuarioService.atualizarUsuario(id,usuario);
        return ResponseEntity.created(URI.create("/usuario" + usuario.getId())).body(usuario);
    }

    @DeleteMapping(value = "/usuario/delete/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable(value = "id") Long id){
        usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return usuarioService.deleteUsuario(id);
    }
}

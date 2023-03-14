package com.dev.manicure.service;

import com.dev.manicure.entity.Usuario;
import com.dev.manicure.exception.ResourceNotFoundException;
import com.dev.manicure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Usuario cadastrarUsuario(Usuario usuario) {
        Usuario usuarioExitente = usuarioRepository.findByUserName(usuario.getNome());
        if (usuarioExitente != null) {
            throw new RuntimeException("Usuário já existe!");
        }
        usuario.setSenha(passwordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Usuario atualizarUsuario(Long id, Usuario usuarioHolder) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado!!"));
        usuario.setNome(usuarioHolder.getNome());
        usuario.setSenha(usuarioHolder.getSenha());
        usuario.setNascimento(usuarioHolder.getNascimento());
        usuario.setTelefone(usuarioHolder.getTelefone());
        usuario.setEmail(usuarioHolder.getEmail());
        return usuarioRepository.save(usuario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> deleteUsuario (Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
package com.dev.manicure.authentication;

import com.dev.manicure.entity.Usuario;
import com.dev.manicure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario usuarioExitente = usuarioRepository.findByUserNameFetchRoles(userName);
        if (usuarioExitente == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        return UserDefault.create(usuarioExitente);
    }
}

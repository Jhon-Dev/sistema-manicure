package com.dev.manicure.authentication;

import com.dev.manicure.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDefault implements UserDetails {

    private String nome;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDefault(Usuario usuario){
        this.nome = usuario.getNome();
        this.senha = usuario.getSenha();
        List<SimpleGrantedAuthority> authorities;

       authorities = usuario.getRoles().stream().map(role -> {
            return new SimpleGrantedAuthority("ROLE_" + role.getName());
        }).collect(Collectors.toList());

       this.authorities = authorities;
    }

    public static UserDefault create (Usuario usuario){
        return new UserDefault(usuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

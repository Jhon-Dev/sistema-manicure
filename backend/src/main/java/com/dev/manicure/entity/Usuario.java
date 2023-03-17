package com.dev.manicure.entity;

import com.dev.manicure.entity.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NOME", nullable = false, length = 100)
    private String userName;
    @Column(name = "SENHA", nullable = false, length = 100)
    private String password;

    @Column(name = "EMAIL", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "TELEFONE", length = 12)
    private Integer telefone;

    @Column(name = "NASCIMENTO", nullable = false, length = 20)
    private Date nascimento;

    @Column(name = "PACOTE_MENSAL", length = 5)
    private Boolean pacoteMensal;

    @OneToMany(mappedBy = "usuario")
    private List<ServicoPrestado> servicosPrestados;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return userName;
    }

    public void setNome(String nome) {
        this.userName = nome;
    }

    public String getSenha() {
        return password;
    }

    public void setSenha(String senha) {
        this.password = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Boolean getPacoteMensal() {
        return pacoteMensal;
    }

    public void setPacoteMensal(Boolean pacoteMensal) {
        this.pacoteMensal = pacoteMensal;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

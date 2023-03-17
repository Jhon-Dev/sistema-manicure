package com.dev.manicure.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

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

    @Column(name = "CD_ROLES")
    @ManyToMany
    private List<Role> roles;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

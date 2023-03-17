package com.dev.manicure.entity;

import com.dev.manicure.entity.enums.TipoServico;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_SERVICO_PRESTADO")
public class ServicoPrestado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIPO_SERVICO", nullable = false, length = 75)
    @ElementCollection(targetClass = TipoServico.class)
    private List<TipoServico> tiposServico;

    @Column(name = "DATA", nullable = false)
    private Date data;

    @Column(name = "VALOR")
    private Integer valor;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TipoServico> getTiposServico() {
        return tiposServico;
    }

    public void setTiposServico(List<TipoServico> tiposServico) {
        this.tiposServico = tiposServico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

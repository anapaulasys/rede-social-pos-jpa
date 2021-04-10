package com.posjava.redesocial.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.posjava.redesocial.model.dto.PostagemDTO;
import com.posjava.redesocial.model.mapper.PostagemMapper;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Informe o campo criado em")
    private Date criadoEm;
    @NotNull(message = "Informe o conteudo")
    @Length(max = 3000, min = 3)
    @Column(length = 3000)
    private String conteudo;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "postagem", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Comentario> comentarios;

    public Postagem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public PostagemDTO toDto() {
        return PostagemMapper.INSTANCE.toDto(this);
    }
}

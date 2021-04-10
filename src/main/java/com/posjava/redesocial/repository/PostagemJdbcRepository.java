package com.posjava.redesocial.repository;

import com.google.common.collect.Lists;
import com.posjava.redesocial.model.dto.ComentarioDTO;
import com.posjava.redesocial.model.dto.PostagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostagemJdbcRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<ComentarioDTO> getComentarioByPostagemId(Long idPostagem) {
        String sql = "select usuario.nome, " +
                " usuario.foto," +
                " comentario.conteudo," +
                " comentario.criado_em from comentario " +
                " inner join usuario on usuario.id = comentario.usuario_id" +
                " where comentario.postagem_id = ? ";

        return jdbcTemplate.query(sql, new Object[]{idPostagem}, (resultSet) -> {
            List<ComentarioDTO> comentarios = Lists.newArrayList();
            while (resultSet.next()) {
                ComentarioDTO dto = new ComentarioDTO();
                dto.setNomeUsuario(resultSet.getString(1));
                dto.setFotoUsuario(resultSet.getString(2));
                dto.setConteudo(resultSet.getString(3));
                dto.setCriadoEm(resultSet.getDate(4));
                comentarios.add(dto);
            }
            return comentarios;
        });
    }


    public List<PostagemDTO> getAll(String conteudo, Pageable pageable) {
        String sql = " select postagem.id," +
                " postagem.conteudo, " +
                " postagem.criado_em," +
                " usuario.id," +
                " usuario.nome, " +
                " usuario.foto," +
                " (select count(id) from curtida where curtida.postagem_id = postagem.id) " +
                " from postagem" +
                " inner join usuario on usuario.id = postagem.usuario_id " +
                " where lower(postagem.conteudo) like ? " +
                " order by postagem.criado_em desc" +
                " limit ? offset ? ";
        return jdbcTemplate.query(sql,
                new Object[]{"%" + conteudo.toLowerCase() + "%", pageable.getPageSize(), pageable.getOffset()},
                resultSet -> {
                    List<PostagemDTO> postagens = Lists.newArrayList();
                    while (resultSet.next()) {
                        PostagemDTO dto = new PostagemDTO();
                        dto.setId(resultSet.getLong(1));
                        dto.setConteudo(resultSet.getString(2));
                        dto.setCriadoEm(resultSet.getDate(3));
                        dto.setIdUsuario(resultSet.getLong(4));
                        dto.setNomeUsuario(resultSet.getString(5));
                        dto.setFotoUsuario(resultSet.getString(6));
                        dto.setCurtidas(resultSet.getInt(7));
                        dto.setComentarios(getComentarioByPostagemId(dto.getId()));
                        postagens.add(dto);
                    }
                    return postagens;
                });
    }

}

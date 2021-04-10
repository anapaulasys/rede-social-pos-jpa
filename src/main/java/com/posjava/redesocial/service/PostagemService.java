package com.posjava.redesocial.service;

import com.google.common.base.Strings;
import com.posjava.redesocial.model.Curtida;
import com.posjava.redesocial.model.Postagem;
import com.posjava.redesocial.model.Story;
import com.posjava.redesocial.model.Usuario;
import com.posjava.redesocial.model.dto.PostagemDTO;
import com.posjava.redesocial.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostagemService extends AbstractService<Postagem> {

    @Autowired
    private PostagemJpaRepository postagemJpaRepository;

    @Autowired
    private StoryJpaRepository storyJpaRepository;

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    private CurtidaJpaRepository curtidaJpaRepository;

    @Autowired
    private PostagemJdbcRepository postagemJdbcRepository;

    @Override
    public JpaRepository<Postagem, Long> getRepository() {
        return postagemJpaRepository;
    }

    public Postagem save(Postagem postagem) {
        postagem.setCriadoEm(new Date());
        return super.save(postagem);
    }

    @Override
    public Page<Postagem> findAll(String filto, Pageable page) {
        return null;
    }

    public Integer countCurtidasByPostagemId(Long postagemId) {
        return curtidaJpaRepository.countCurtidaByPostagemId(postagemId);
    }

    public void curtir(Long usuarioId, Long postagemId) {

        Curtida postagemJaCurtida = curtidaJpaRepository.findFirstByUsuarioIdAndPostagemId(usuarioId, postagemId);

        if (postagemJaCurtida == null) {
            Usuario usuario = usuarioJpaRepository.getOne(usuarioId);
            Postagem postagem = postagemJpaRepository.getOne(postagemId);

            Curtida curtida = new Curtida();
            curtida.setCriadoEm(new Date());
            curtida.setUsuario(usuario);
            curtida.setPostagem(postagem);

            curtidaJpaRepository.save(curtida);
        }
    }

    public Page<Story> findAllStory(Pageable page) {
        return storyJpaRepository.findAll(page);
    }

    public List<PostagemDTO> findAllPostagem(String nome, Pageable page) {
        if (nome == null) {
            nome = "";
        }
        return postagemJdbcRepository.getAll(nome, page);
    }


}

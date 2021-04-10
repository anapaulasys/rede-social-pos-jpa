package com.posjava.redesocial.service;

import com.posjava.redesocial.model.Comentario;
import com.posjava.redesocial.repository.ComentarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService extends AbstractService<Comentario> {

    @Autowired
    private ComentarioJpaRepository comentarioJpaRepository;

    @Override
    public JpaRepository<Comentario, Long> getRepository() {
        return comentarioJpaRepository;
    }

    @Override
    public Page<Comentario> findAll(String filto, Pageable page) {
        return null;
    }
}

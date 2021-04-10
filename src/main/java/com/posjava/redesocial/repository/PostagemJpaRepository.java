package com.posjava.redesocial.repository;

import com.posjava.redesocial.model.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostagemJpaRepository extends JpaRepository<Postagem, Long> {

    @Query("select p from Postagem p where lower(p.conteudo) like lower(concat('%',:conteudo, '%')) order by p.criadoEm desc")
    Page<Postagem> findAllByConteudoLike(@Param("conteudo") String conteudo, Pageable pageable);

    Page<Postagem> findAllByOrderByCriadoEmDesc(Pageable pageable);
}

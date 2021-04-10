package com.posjava.redesocial.repository;

import com.posjava.redesocial.model.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurtidaJpaRepository extends JpaRepository<Curtida, Long> {

    public Curtida findFirstByUsuarioIdAndPostagemId(Long usuarioId, Long postagemId);

    public Integer countCurtidaByPostagemId(Long postagemId);
}

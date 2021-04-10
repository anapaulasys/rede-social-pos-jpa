package com.posjava.redesocial.repository;

import com.posjava.redesocial.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    @Query("select u from Usuario u where u.email = :email and u.id <> :id")
    Usuario findByEmailAndIdNotEquals(@Param("email") String email, @Param("id") Long id);

    @Query("select u from Usuario u where lower(u.nome) like lower(concat('%',:nome, '%'))")
    Page<Usuario> findAllByNomeLike(@Param("nome") String nome, Pageable pageable);


}

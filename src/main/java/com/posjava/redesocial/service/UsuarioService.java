package com.posjava.redesocial.service;

import com.google.common.base.Strings;
import com.posjava.redesocial.model.Usuario;
import com.posjava.redesocial.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
public class UsuarioService extends AbstractService<Usuario> {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Override
    public JpaRepository<Usuario, Long> getRepository() {
        return usuarioJpaRepository;
    }

    public Usuario save(Usuario usuario) {
        Usuario existente = null;
        if (usuario.getId() != null) {
            existente = usuarioJpaRepository.findByEmailAndIdNotEquals(usuario.getEmail(), usuario.getId());
        } else {
            usuarioJpaRepository.findByEmail(usuario.getEmail());
        }
        if (existente != null) {
            throw new ValidationException("Já existe um usuário com esse email " + usuario.getEmail());
        }
        return super.save(usuario);
    }

    public Page<Usuario> findAll(String nome, Pageable page) {
        if (Strings.isNullOrEmpty(nome)) {
            return usuarioJpaRepository.findAll(page);
        }
        return usuarioJpaRepository.findAllByNomeLike(nome, page);
    }
}

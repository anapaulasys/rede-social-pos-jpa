package com.posjava.redesocial.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<T> {

    public abstract JpaRepository<T, Long> getRepository();

    public T save(T object) {
        return getRepository().save(object);
    }

    public List<T> getAll() {
        return getRepository().findAll();
    }

    public void delete(Long id) {
        getRepository().deleteById(id);
    }

    public T getOne(Long id) {
        return getRepository().getOne(id);
    }

    public abstract Page<T> findAll(String filto, Pageable page);


}

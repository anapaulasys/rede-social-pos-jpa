package com.posjava.redesocial.resources;

import com.posjava.redesocial.service.AbstractService;
import com.posjava.redesocial.util.Util;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractResource<T> {

    public abstract AbstractService<T> getService();

    public ResponseEntity<T> create(@RequestBody @Valid T object) {
        try {
            T save = getService().save(object);
            return ResponseEntity.ok(save);
        } catch (Exception e) {
            return Util.getResposeError(e.getMessage());
        }
    }

    public ResponseEntity<T> get(@PathVariable Long id) {
        try {
            T one = getService().getOne(id);
            return ResponseEntity.ok(one);
        } catch (Exception e) {
            return Util.getResposeError(e.getMessage());
        }
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            getService().delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return Util.getResposeError(e.getMessage());
        }
    }

    public ResponseEntity<List<T>> getAll(@RequestParam(value = "filtro", required = false) String filtro,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("limit") Integer limit) {
        try {
            PageRequest pagable = PageRequest.of(page, limit);
            return ResponseEntity.ok(getService().findAll(filtro, pagable).getContent());
        } catch (Exception e) {
            return Util.getResposeError(e.getMessage());
        }
    }

}

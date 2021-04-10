package com.posjava.redesocial.resources;

import com.posjava.redesocial.model.Comentario;
import com.posjava.redesocial.service.AbstractService;
import com.posjava.redesocial.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ComentarioResource extends AbstractResource<Comentario> {

    @Autowired
    private ComentarioService comentarioService;

    @Override
    public AbstractService<Comentario> getService() {
        return comentarioService;
    }

    @PostMapping("/comentario")
    public ResponseEntity<Comentario> create(@RequestBody @Valid Comentario comentario) {
        return super.create(comentario);
    }

    @GetMapping("/comentario/{id}")
    public ResponseEntity<Comentario> get(@PathVariable Long id) {
        return super.get(id);
    }

    @DeleteMapping("/comentario/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

}

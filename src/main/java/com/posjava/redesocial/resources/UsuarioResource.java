package com.posjava.redesocial.resources;

import com.posjava.redesocial.model.Usuario;
import com.posjava.redesocial.model.dto.UsuarioDTO;
import com.posjava.redesocial.service.AbstractService;
import com.posjava.redesocial.service.UsuarioService;
import com.posjava.redesocial.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsuarioResource extends AbstractResource<Usuario> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public AbstractService<Usuario> getService() {
        return usuarioService;
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario) {
        return super.create(usuario);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> get(@PathVariable Long id) {
        return super.get(id);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioDTO>> getAllUser(@RequestParam(value = "filtro", required = false) String filtro,
                                                       @RequestParam("page") Integer page,
                                                       @RequestParam("limit") Integer limit) {
        try {
            PageRequest pagable = PageRequest.of(page, limit);
            List<Usuario> usuarios = getService().findAll(filtro, pagable).getContent();
            return ResponseEntity.ok(usuarios.stream().map(Usuario::toDto)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return Util.getResposeError(e.getMessage());
        }
    }


}

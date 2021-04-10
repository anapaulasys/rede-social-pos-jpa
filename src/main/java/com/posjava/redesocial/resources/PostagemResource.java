package com.posjava.redesocial.resources;

import com.posjava.redesocial.model.Postagem;
import com.posjava.redesocial.model.Story;
import com.posjava.redesocial.model.dto.PostagemDTO;
import com.posjava.redesocial.service.AbstractService;
import com.posjava.redesocial.service.PostagemService;
import com.posjava.redesocial.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostagemResource extends AbstractResource<Postagem> {

    @Autowired
    private PostagemService postagemService;

    @Override
    public AbstractService<Postagem> getService() {
        return postagemService;
    }

    @PostMapping("/postagem")
    public ResponseEntity<Postagem> create(@RequestBody @Valid Postagem postagem) {
        return super.create(postagem);
    }

    @GetMapping("/postagem/{id}")
    public ResponseEntity<Postagem> get(@PathVariable Long id) {
        return super.get(id);
    }

    @DeleteMapping("/postagem/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @GetMapping("/postagem")
    public ResponseEntity<List<PostagemDTO>> getAllPostagens(@RequestParam(value = "filtro", required = false) String filtro,
                                                             @RequestParam("page") Integer page,
                                                             @RequestParam("limit") Integer limit) {
        try {
            PageRequest pagable = PageRequest.of(page, limit);
            return ResponseEntity.ok(postagemService.findAllPostagem(filtro, pagable));
        } catch (Exception e) {
            return Util.getResposeError(e.getMessage());
        }
    }

    @GetMapping("/story")
    public ResponseEntity<List<Story>> getAllStory(@RequestParam("page") Integer page,
                                                   @RequestParam("limit") Integer limit) {
        Page<Story> allStory = postagemService.findAllStory(PageRequest.of(page, limit));
        return ResponseEntity.ok(allStory.getContent());
    }

    @GetMapping("/curtir")
    public void curtir(@RequestParam("usuarioId") Long usuarioId, @RequestParam("postagemId") Long postagemId) {
        postagemService.curtir(usuarioId, postagemId);
    }

    @GetMapping("/curtidas-por-postagem")
    public Integer curtidasPorPostagem(@RequestParam("postagemId") Long postagemId) {
        return postagemService.countCurtidasByPostagemId(postagemId);
    }

}

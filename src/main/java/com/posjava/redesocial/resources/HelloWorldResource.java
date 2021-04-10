package com.posjava.redesocial.resources;

import com.posjava.redesocial.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloWorldResource {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @PostMapping("/hello")
    public String hello(String hello){
        return hello;
    }

}

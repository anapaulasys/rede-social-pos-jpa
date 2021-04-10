package com.posjava.redesocial;

import com.github.javafaker.Faker;
import com.posjava.redesocial.model.Usuario;
import com.posjava.redesocial.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    UsuarioService service;

    @Test
    public void insereMuitosRegistros() {
        int qnt = 1000;
        Faker faker = new Faker(new Locale("pt-BR"));
        while (qnt > 0) {
            try {
                Usuario usuario = new Usuario();
                usuario.setNome(faker.name().fullName());
                usuario.setEmail(faker.internet().emailAddress());
                usuario.setBiografia(faker.gameOfThrones().quote());
                usuario.setFoto("https://avatars.dicebear.com/api/avataaars/"+faker.name().firstName()+".svg");
                usuario.setSenha(faker.internet().password());
                Usuario retorno = service.save(usuario);
                Assertions.assertNotNull(retorno.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            qnt--;
        }
        Assertions.assertEquals(qnt, 0);
    }
}

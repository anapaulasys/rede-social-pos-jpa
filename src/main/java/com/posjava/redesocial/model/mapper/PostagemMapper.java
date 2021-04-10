package com.posjava.redesocial.model.mapper;

import com.posjava.redesocial.model.Postagem;
import com.posjava.redesocial.model.dto.PostagemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostagemMapper {
    PostagemMapper INSTANCE = Mappers.getMapper(PostagemMapper.class);

    @Mapping(source = "usuario.nome", target = "nomeUsuario")
    @Mapping(source = "usuario.id", target = "idUsuario")
    @Mapping(source = "usuario.foto", target = "fotoUsuario")
    PostagemDTO toDto(Postagem postagem);

}

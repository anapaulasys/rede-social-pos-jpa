package com.posjava.redesocial.model.mapper;

import com.posjava.redesocial.model.Usuario;
import com.posjava.redesocial.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "biografia", target = "sobre")
    UsuarioDTO toDto(Usuario usuario);

    Usuario fromDto(UsuarioDTO dto);

}

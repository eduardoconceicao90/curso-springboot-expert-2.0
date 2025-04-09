package io.github.eduardoconceicao90.libraryapi.controller.mapper;

import io.github.eduardoconceicao90.libraryapi.model.Livro;
import io.github.eduardoconceicao90.libraryapi.model.dto.CadastroLivroDTO;
import io.github.eduardoconceicao90.libraryapi.model.dto.ResultadoPesquisaLivroDTO;
import io.github.eduardoconceicao90.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(cadastroLivroDTO.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO cadastroLivroDTO);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);

}

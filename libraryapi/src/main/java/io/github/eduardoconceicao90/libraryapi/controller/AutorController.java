package io.github.eduardoconceicao90.libraryapi.controller;

import io.github.eduardoconceicao90.libraryapi.model.Autor;
import io.github.eduardoconceicao90.libraryapi.model.dto.AutorDTO;
import io.github.eduardoconceicao90.libraryapi.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("autores")
public class AutorController {

    private final AutorService service;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autorDTO) {
        Autor autor = autorDTO.mapearParaAutor();
        service.salvar(autor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable String id) {
        Optional<Autor> autorOptional = service.obterPorId(UUID.fromString(id));

        if(autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDTO autorDTO = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(autorDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        Optional<Autor> autorOptional = service.obterPorId(UUID.fromString(id));

        if(autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(required = false) String nome, @RequestParam(required = false) String nacionalidade) {
        List<Autor> resultado = service.pesquisa(nome, nacionalidade);

        List<AutorDTO> lista = resultado.stream().map(autor ->
                new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade())
        ).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}

package io.github.eduardoconceicao90.libraryapi.controller;

import io.github.eduardoconceicao90.libraryapi.controller.mapper.LivroMapper;
import io.github.eduardoconceicao90.libraryapi.model.Livro;
import io.github.eduardoconceicao90.libraryapi.model.dto.CadastroLivroDTO;
import io.github.eduardoconceicao90.libraryapi.model.dto.ResultadoPesquisaLivroDTO;
import io.github.eduardoconceicao90.libraryapi.model.enums.GeneroLivro;
import io.github.eduardoconceicao90.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("livros")
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO livroDTO) {
        Livro livro = mapper.toEntity(livroDTO);
        service.salvar(livro);
        URI location = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable String id) {
        return service.obterPorId(UUID.fromString(id)).map(livro -> {
            ResultadoPesquisaLivroDTO livroDTO = mapper.toDTO(livro);
            return ResponseEntity.ok(livroDTO);
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        Optional<Livro> livroOptional = service.obterPorId(UUID.fromString(id));

        if (livroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deletar(livroOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResultadoPesquisaLivroDTO>> pesquisa(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String nomeAutor,
            @RequestParam(required = false) GeneroLivro genero,
            @RequestParam(required = false) Integer anoPublicacao
    ) {
        List<ResultadoPesquisaLivroDTO> livrosDTO = service
                .pesquisa(isbn, titulo, nomeAutor, genero, anoPublicacao)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(livrosDTO);
    }

}
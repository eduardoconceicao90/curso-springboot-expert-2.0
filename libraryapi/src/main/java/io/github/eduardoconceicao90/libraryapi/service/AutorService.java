package io.github.eduardoconceicao90.libraryapi.service;

import io.github.eduardoconceicao90.libraryapi.model.Autor;
import io.github.eduardoconceicao90.libraryapi.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutorService {

    private final AutorRepository repository;

    public Autor salvar(Autor autor) {
        return repository.save(autor);
    }
}

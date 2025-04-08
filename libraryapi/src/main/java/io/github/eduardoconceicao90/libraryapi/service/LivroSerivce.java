package io.github.eduardoconceicao90.libraryapi.service;

import io.github.eduardoconceicao90.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LivroSerivce {

    private final LivroRepository repository;
}

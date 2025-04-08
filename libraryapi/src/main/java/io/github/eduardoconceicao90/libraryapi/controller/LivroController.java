package io.github.eduardoconceicao90.libraryapi.controller;

import io.github.eduardoconceicao90.libraryapi.service.LivroSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("livros")
public class LivroController {

    private final LivroSerivce service;

}

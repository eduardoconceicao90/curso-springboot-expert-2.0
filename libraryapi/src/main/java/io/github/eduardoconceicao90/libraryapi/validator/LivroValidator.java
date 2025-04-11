package io.github.eduardoconceicao90.libraryapi.validator;

import io.github.eduardoconceicao90.libraryapi.exception.CampoInvalidoException;
import io.github.eduardoconceicao90.libraryapi.exception.RegistroDuplicadoException;
import io.github.eduardoconceicao90.libraryapi.model.Livro;
import io.github.eduardoconceicao90.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LivroValidator {

    private static final int ANO_EXIGENCIA_PRECO = 2020;

    private final LivroRepository livroRepository;

    public void validar(Livro livro) {
        if(existeLivroComIsbn(livro)) {
            throw new RegistroDuplicadoException("ISBN já cadastrado!");
        }

        if(isPrecoObrigatorioNulo(livro)) {
            throw new CampoInvalidoException(
                    "preco", "Preço é obrigatório para livros com ano de publicação a partir de 2020!"
            );
        }
    }

    private boolean isPrecoObrigatorioNulo(Livro livro) {
        return livro.getPreco() == null && livro.getDataPublicacao() != null
                && livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO;
    }

    private boolean existeLivroComIsbn(Livro livro) {
        Optional<Livro> livroEncontrado = livroRepository.findByIsbn(livro.getIsbn());

        if(livro.getId() == null) {
            return livroEncontrado.isPresent();
        }

        return livroEncontrado.map(Livro::getId).stream().anyMatch(id -> !id.equals(livro.getId()));
    }

}

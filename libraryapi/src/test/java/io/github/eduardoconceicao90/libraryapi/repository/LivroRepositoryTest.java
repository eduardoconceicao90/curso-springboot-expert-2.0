package io.github.eduardoconceicao90.libraryapi.repository;

import io.github.eduardoconceicao90.libraryapi.model.Autor;
import io.github.eduardoconceicao90.libraryapi.model.Livro;
import io.github.eduardoconceicao90.libraryapi.model.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro().builder()
                .isbn("90887-84874")
                .preco(BigDecimal.valueOf(100))
                .genero(GeneroLivro.FICCAO)
                .titulo("Livro Teste")
                .dataPublicacao(LocalDate.of(1980, 1, 2))
                .build();

        Autor autor = autorRepository
                .findById(UUID.fromString("76e7c418-ccf9-4e2a-af20-c28b9e50ab55"))
                .orElse(null);

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro().builder()
                .isbn("90887-84874")
                .preco(BigDecimal.valueOf(100))
                .genero(GeneroLivro.FICCAO)
                .titulo("Segundo Livro")
                .dataPublicacao(LocalDate.of(1980, 1, 2))
                .build();

        Autor autor = new Autor().builder()
                .nome("José")
                .nacionalidade("Brasileira")
                .dataNascimento(LocalDate.of(1951, 1, 31))
                .build();

        autorRepository.save(autor);
        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro().builder()
                .isbn("90887-84874")
                .preco(BigDecimal.valueOf(100))
                .genero(GeneroLivro.FICCAO)
                .titulo("Outro Livro")
                .dataPublicacao(LocalDate.of(1980, 1, 2))
                .build();

        Autor autor = new Autor().builder()
                .nome("João")
                .nacionalidade("Brasileira")
                .dataNascimento(LocalDate.of(1951, 1, 31))
                .build();

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("cfbc87ce-5932-4792-bff0-78ef5973861b");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("76e7c418-ccf9-4e2a-af20-c28b9e50ab55");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);
        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("cfbc87ce-5932-4792-bff0-78ef5973861b");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade(){
        UUID id = UUID.fromString("22238c02-8118-45ba-a9f0-202dfc3acc67");
        repository.deleteById(id);
    }

}
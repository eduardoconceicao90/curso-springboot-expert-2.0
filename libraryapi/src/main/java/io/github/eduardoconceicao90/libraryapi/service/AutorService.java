package io.github.eduardoconceicao90.libraryapi.service;

import io.github.eduardoconceicao90.libraryapi.exception.OperacaoNaoPermitidaException;
import io.github.eduardoconceicao90.libraryapi.model.Autor;
import io.github.eduardoconceicao90.libraryapi.model.Usuario;
import io.github.eduardoconceicao90.libraryapi.repository.AutorRepository;
import io.github.eduardoconceicao90.libraryapi.repository.LivroRepository;
import io.github.eduardoconceicao90.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AutorService {

    private final AutorRepository repository;
    private final LivroRepository livroRepository;
    private final AutorValidator validator;
    private final SecurityService securityService;

    public Autor salvar(Autor autor) {
        validator.validar(autor);
        Usuario usuario = securityService.obterUsuarioLogado();
        autor.setUsuario(usuario);
        return repository.save(autor);
    }

    public void atualizar(Autor autor) {
        if(autor.getId() == null) {
            throw new IllegalArgumentException("O id do autor é obrigatório");
        }
        validator.validar(autor);
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Autor autor) {
        if(possuiLivrosAssociados(autor)) {
            throw new OperacaoNaoPermitidaException("Não é possível deletar o autor, pois ele possui livros associados.");
        }
        repository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade) {
        if(nome != null && nacionalidade != null) {
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if(nome != null) {
            return repository.findByNome(nome);
        }

        if(nacionalidade != null) {
            return repository.findByNacionalidade(nacionalidade);
        }

        return repository.findAll();
    }

    public List<Autor> pesquisaByExample(String nome, String nacionalidade) {
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                //.withIgnorePaths("id", "dataNascimento","dataCadastro")
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Autor> autorExample = Example.of(autor, matcher);
        return repository.findAll(autorExample);
    }

    public boolean possuiLivrosAssociados(Autor autor){
        return livroRepository.existsByAutor(autor);
    }

}
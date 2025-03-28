package io.github.eduardoconceicao90.libraryapi.service;

import io.github.eduardoconceicao90.libraryapi.model.Autor;
import io.github.eduardoconceicao90.libraryapi.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AutorService {

    private final AutorRepository repository;

    public Autor salvar(Autor autor) {
        return repository.save(autor);
    }

    public void atualizar(Autor autor) {
        if(autor.getId() == null) {
            throw new IllegalArgumentException("O id do autor é obrigatório");
        }

        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Autor autor) {
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

}

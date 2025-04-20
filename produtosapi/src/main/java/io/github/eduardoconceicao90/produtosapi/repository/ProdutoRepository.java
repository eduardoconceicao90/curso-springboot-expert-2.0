package io.github.eduardoconceicao90.produtosapi;

import io.github.eduardoconceicao90.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {
}

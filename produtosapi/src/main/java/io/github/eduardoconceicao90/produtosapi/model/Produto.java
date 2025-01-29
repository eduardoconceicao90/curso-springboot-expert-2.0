package io.github.eduardoconceicao90.produtosapi.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "produto")
public class Produto {

    private String id;
    private String nome;
    private String descricao;
    private Double preco;

}

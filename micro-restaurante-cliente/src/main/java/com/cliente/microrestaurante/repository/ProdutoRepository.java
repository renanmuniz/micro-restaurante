package com.cliente.microrestaurante.repository;

import com.cliente.microrestaurante.modelo.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);

    @Query(value =
            "SELECT produto.id, produto.nome, produto.descricao, produto.tamanho, preco.preco\n" +
                    "FROM produto \n" +
                    "INNER JOIN preco on (preco.id_produto=produto.id) \n" +
                    "order by produto.nome ASC; ",
            nativeQuery = true)
    Page<Produto> carregaCardapio(Pageable paginacao);

    @Query(value =
            "SELECT produto.id, produto.nome, produto.descricao, produto.tamanho, preco.preco " +
                    "FROM produto " +
                    "INNER JOIN preco on (preco.id_produto=produto.id) " +
                    "WHERE produto.id = :id",
            nativeQuery = true)
    Optional<Produto> findById(Long id);
}

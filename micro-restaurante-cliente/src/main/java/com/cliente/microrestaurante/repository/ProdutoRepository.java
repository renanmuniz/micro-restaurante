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
            "SELECT * FROM produto order by produto.nome ASC; ",
            nativeQuery = true)
    Page<Produto> carregaCardapio(Pageable paginacao);

    @Query(value =
            "SELECT * FROM produto WHERE produto.id = :id",
            nativeQuery = true)
    Optional<Produto> findById(Long id);

}

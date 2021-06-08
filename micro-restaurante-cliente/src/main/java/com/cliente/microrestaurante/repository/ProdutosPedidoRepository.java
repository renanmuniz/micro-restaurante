package com.cliente.microrestaurante.repository;

import com.cliente.microrestaurante.modelo.ProdutosPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutosPedidoRepository extends JpaRepository<ProdutosPedido, Long> {
    @Query(value =
            "SELECT produto.nome, produtos_pedido.quantidade, produtos_pedido.valor, produtos_pedido.observacao\n" +
                    "FROM produtos_pedido \n" +
                    "INNER JOIN pedido on (pedido.id = produtos_pedido.id_pedido)\n" +
                    "INNER JOIN produto on (produtos_pedido.id_produto = produto.id) \n" +
                    "where pedido.id=:id",
            nativeQuery = true)
    Page<ProdutosPedido> carregaItensPedido(Long id, Pageable paginacao);

}

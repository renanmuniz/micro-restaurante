package com.cliente.microrestaurante.repository;

import com.cliente.microrestaurante.modelo.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "select * from pedido where id_usuario = :usuario and id = :pedido order by dtHrPedido desc",
            nativeQuery = true)
    Optional<Pedido> carregaPedidoEspecifico(Long usuario, Long pedido);

    @Query(value = "select * from pedido where id_usuario = :usuario order by dtHrPedido desc",
        nativeQuery = true)
    Page<Pedido> carregaPedidosTodos(Long usuario, Pageable paginacao);


    @Query(value = "select * from pedido where id_usuario = :usuario and dthrfinalizado is null order by dtHrPedido desc",
            nativeQuery = true)
    Page<Pedido> carregaPedidosPendentes(Long usuario, Pageable paginacao);


    @Query(value = "select * from pedido where id_usuario = :usuario and dthrfinalizado is not null order by dtHrPedido desc",
            nativeQuery = true)
    Page<Pedido> carregaPedidosFinalizados(Long usuario, Pageable paginacao);

}

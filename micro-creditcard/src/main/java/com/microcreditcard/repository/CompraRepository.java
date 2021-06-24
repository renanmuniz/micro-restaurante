package com.microcreditcard.repository;


import com.microcreditcard.modelo.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    Page<Compra> findByidcartao(Long id, Pageable paginacao);

    Page<Compra> findByidusuario(Long id, Pageable paginacao);

    @Query(value = "select * from compras " +
            "where id_cartao = :idcartao" +
            " and aprovada = true " +
            " and estornada = false" +
            " and dtHrCompra between :dtInicio and :dtFim" +
            " order by dtHrCompra desc",
            nativeQuery = true)
    Page<Compra> comprasAprovadas(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao);

    @Query(value = "select * from compras where id_cartao = :idcartao" +
            " and aprovada = false " +
            " and dtHrCompra between :dtInicio and :dtFim" +
            " order by dtHrCompra desc",
            nativeQuery = true)
    Page<Compra> comprasRecusadas(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao);

    @Query(value = "select * from compras where id_cartao = :idcartao" +
            " and aprovada = true and estornada = true " +
            " and dtHrCompra between :dtInicio and :dtFim" +
            " order by dtHrCompra desc",
            nativeQuery = true)
    Page<Compra> comprasEstornadas(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao);

    @Query(value = "select * from compras where id_cartao = :idcartao" +
            " and aprovada = true" +
            " and estornada = false" +
            " and paga = false" +
            " and dtHrCompra between :dtInicio and :dtFim" +
            " order by dtHrCompra desc",
            nativeQuery = true)
    Page<Compra> comprasPedentesPgto(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao);

    @Query(value = "select * from compras where id_cartao = :idcartao" +
            " and aprovada = true" +
            " and estornada = false" +
            " and paga = true" +
            " and dtHrCompra between :dtInicio and :dtFim" +
            " order by dtHrCompra desc",
            nativeQuery = true)
    Page<Compra> comprasPagas(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao);

    @Query(value = "select sum(valor) from compras where id_cartao = 1" +
            " and aprovada = true" +
            " and estornada = false" +
            " and paga = false",
            nativeQuery = true)
    Double limiteUtilizado(Long idcartao);


    @Query(value="select compras.*, cartao.numero_cartao as numerocartao, cartao.nome as titularcartao" +
            " from compras" +
            " INNER JOIN cartao on (compras.id_cartao = cartao.id)" +
            " where compras.id = :idCompra", nativeQuery = true)
    Optional<Compra> detalharCompra (Long idCompra);
}

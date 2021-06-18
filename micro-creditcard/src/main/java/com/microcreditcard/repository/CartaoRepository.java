package com.microcreditcard.repository;


import com.microcreditcard.modelo.Cartao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByNome(String nome);

    Page<Cartao> findByNome(String nome, Pageable paginacao);

    Cartao getOne(Long id);
}

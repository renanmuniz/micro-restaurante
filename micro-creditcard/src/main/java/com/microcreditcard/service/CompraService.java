package com.microcreditcard.service;

import com.microcreditcard.controller.dto.CompraDto;
import com.microcreditcard.controller.form.CompraForm;
import com.microcreditcard.modelo.Cartao;
import com.microcreditcard.modelo.Compra;
import com.microcreditcard.repository.CartaoRepository;
import com.microcreditcard.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    public Page<CompraDto> listarTodasPorIdCartao(Long id, Pageable paginacao) {
        Page<Compra> compras;
        compras = compraRepository.findByidcartao(id, paginacao);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarTodasPorIdUsuario(Long id, Pageable paginacao) {
        Page<Compra> compras;
        compras = compraRepository.findByidusuario(id, paginacao);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasAprovadasPorIdCartao(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao) {
        Page<Compra> compras;
        compras = compraRepository.comprasAprovadas(idcartao, dtInicio, dtFim, paginacao);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasRecusadasPorIdCartao(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao) {
        Page<Compra> compras;
        compras = compraRepository.comprasRecusadas(idcartao, dtInicio, dtFim, paginacao);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasEstornadasPorIdCartao(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao) {
        Page<Compra> compras;
        compras = compraRepository.comprasEstornadas(idcartao, dtInicio, dtFim, paginacao);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasPendentesPgtoPorIdCartao(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao) {
        Page<Compra> compras;
        compras = compraRepository.comprasPedentesPgto(idcartao, dtInicio, dtFim, paginacao);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasPagasPorIdCartao(Long idcartao, LocalDateTime dtInicio, LocalDateTime dtFim, Pageable paginacao) {
        Page<Compra> compras;
        compras = compraRepository.comprasPagas(idcartao, dtInicio, dtFim, paginacao);
        return CompraDto.converter(compras);
    }

    public CompraDto detalharCompra(Long idCompra) {
        Optional<Compra> compraOptional = compraRepository.detalharCompra(idCompra);
        if (compraOptional.isPresent()) {
            return new CompraDto(compraOptional.get());
        }
        return null;
    }

    public CompraDto comprar(CompraForm form) {
        Cartao cartao = cartaoRepository.findByNumeroCartao(form.getNumeroCartao());
        Double limiteDisp = cartao.valorCreditoDisponivel
                - compraRepository.limiteUtilizado(cartao.id);
        if (form.getValor() > limiteDisp) {
            return null;
        } else {
            Compra compra = new Compra();
            compra.idcartao = cartao.id;
            compra.idusuario = form.getIdUsuario();
            compra.valor = form.getValor();
            compra.dtHrCompra = LocalDateTime.now();
            CompraDto compraDto = new CompraDto(compraRepository.save(compra));
            return compraDto;
        }
    }

}

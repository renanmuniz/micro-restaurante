package com.microcreditcard.service;

import com.microcreditcard.controller.dto.CompraDto;
import com.microcreditcard.controller.form.CompraForm;
import com.microcreditcard.modelo.Cartao;
import com.microcreditcard.modelo.Compra;
import com.microcreditcard.repository.CartaoRepository;
import com.microcreditcard.repository.CompraRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    public Page<CompraDto> listarTodasPorIdCartao(Long idCartao, Pageable paginacao) {
        Page<Compra> compras;
        compras = compraRepository.findByidcartao(idCartao, paginacao);
        compras = atribuirNumeroETitularCartaoUnico(idCartao, compras);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarTodasPorIdUsuario(Long idCartao, Pageable paginacao) {
        Page<Compra> compras;
        compras = compraRepository.findByidusuario(idCartao, paginacao);
        compras = atribuirNumeroETitularCartaoDiversos(idCartao, compras);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasAprovadasPorIdCartao(Long idcartao, String dtInicioStr, String dtFimStr, Pageable paginacao) {
        Page<Compra> compras;
        LocalDateTime dtInicio = converterData(dtInicioStr).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dtFim = converterData(dtFimStr).withHour(23).withMinute(59).withSecond(59);
        compras = compraRepository.comprasAprovadas(idcartao, dtInicio, dtFim, paginacao);
        compras = atribuirNumeroETitularCartaoUnico(idcartao, compras);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasRecusadasPorIdCartao(Long idcartao, String dtInicioStr, String dtFimStr, Pageable paginacao) {
        Page<Compra> compras;
        LocalDateTime dtInicio = converterData(dtInicioStr).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dtFim = converterData(dtFimStr).withHour(23).withMinute(59).withSecond(59);
        compras = compraRepository.comprasRecusadas(idcartao, dtInicio, dtFim, paginacao);
        compras = atribuirNumeroETitularCartaoUnico(idcartao, compras);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasEstornadasPorIdCartao(Long idcartao, String dtInicioStr, String dtFimStr, Pageable paginacao) {
        Page<Compra> compras;
        LocalDateTime dtInicio = converterData(dtInicioStr).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dtFim = converterData(dtFimStr).withHour(23).withMinute(59).withSecond(59);
        compras = compraRepository.comprasEstornadas(idcartao, dtInicio, dtFim, paginacao);
        compras = atribuirNumeroETitularCartaoUnico(idcartao, compras);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasPendentesPgtoPorIdCartao(Long idcartao, String dtInicioStr, String dtFimStr, Pageable paginacao) {
        Page<Compra> compras;
        LocalDateTime dtInicio = converterData(dtInicioStr).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dtFim = converterData(dtFimStr).withHour(23).withMinute(59).withSecond(59);
        compras = compraRepository.comprasPedentesPgto(idcartao, dtInicio, dtFim, paginacao);
        compras = atribuirNumeroETitularCartaoUnico(idcartao, compras);
        return CompraDto.converter(compras);
    }

    public Page<CompraDto> listarComprasPagasPorIdCartao(Long idcartao, String dtInicioStr, String dtFimStr, Pageable paginacao) {
        Page<Compra> compras;
        LocalDateTime dtInicio = converterData(dtInicioStr).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dtFim = converterData(dtFimStr).withHour(23).withMinute(59).withSecond(59);
        compras = compraRepository.comprasPagas(idcartao, dtInicio, dtFim, paginacao);
        compras = atribuirNumeroETitularCartaoUnico(idcartao, compras);
        return CompraDto.converter(compras);
    }

    public CompraDto cadastrar(CompraForm form) {
        Cartao cartao = cartaoRepository.findByNumeroCartao(form.getNumeroCartao());
        Double limiteDisp = cartao.valorCreditoDisponivel
                - compraRepository.limiteUtilizado(cartao.id);
        Compra compra = new Compra();
        compra.idcartao = cartao.id;
        compra.idusuario = form.getIdUsuario();
        compra.numerocartao = cartao.getNumeroCartao();
        compra.titularcartao = cartao.getNome();
        compra.valor = form.getValor();
        compra.estornada = false;
        compra.paga = false;
        compra.dtHrCompra = LocalDateTime.now();
        if (form.getValor() > limiteDisp) {
            compra.aprovada = false;
            compraRepository.save(compra);
            return null;
        } else {
            compra.aprovada = true;
            return new CompraDto(compraRepository.save(compra));
        }
    }

    public CompraDto detalhar(Long idCompra) {
        Optional<Compra> compraOptional = compraRepository.detalharCompra(idCompra);
        if (compraOptional.isPresent()) {
            Cartao cartao = cartaoRepository.getOne(compraOptional.get().idcartao);
            compraOptional.get().setNumerocartao(cartao.getNumeroCartao());
            compraOptional.get().setTitularcartao(cartao.getNome());
            return new CompraDto(compraOptional.get());
        }
        return null;
    }

    // Metodos de utilidade:

    public LocalDateTime converterData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime dateTime = LocalDate.parse(data, formatter).atStartOfDay();
        return dateTime;
    }

    public Page<Compra> atribuirNumeroETitularCartaoUnico(Long idCartao, Page<Compra> compras) {
        Cartao cartao = cartaoRepository.getOne(idCartao);
        for (Compra compra : compras) {
            compra.setNumerocartao(cartao.getNumeroCartao());
            compra.setTitularcartao(cartao.getNome());
        }
        return compras;
    }

    public Page<Compra> atribuirNumeroETitularCartaoDiversos(Long idCartao, Page<Compra> compras) {
        for (Compra compra : compras) {
            Cartao cartao = cartaoRepository.getOne(idCartao);
            compra.setNumerocartao(cartao.getNumeroCartao());
            compra.setTitularcartao(cartao.getNome());
        }
        return compras;
    }

}

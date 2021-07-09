package com.microrestaurante.service;

import com.microrestaurante.controller.dto.PedidoDto;
import com.microrestaurante.controller.dto.ProdutosPedidoDto;
import com.microrestaurante.modelo.Pedido;
import com.microrestaurante.repository.PedidoRepository;
import com.microrestaurante.repository.PedidoRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDto buscarPedido(Long idPedido) {
        return PedidoRepositoryJdbc.getPedido(idPedido);
    }

    public List<ProdutosPedidoDto> buscarProdutosPedido(Long idPedido) {
        return PedidoRepositoryJdbc.getProdutosPedido(idPedido);
    }

    public List<PedidoDto> buscarPedidosPagosAAceitar() {
        return PedidoRepositoryJdbc.getPagosAAceitar();
    }

    public List<PedidoDto> buscarPedidosAceitosAPreparar() {
        return PedidoRepositoryJdbc.getAceitosAPreparar();
    }

    public List<PedidoDto> buscarPedidosProntosAEntregar() {
        return PedidoRepositoryJdbc.getProntosAEntregar();
    }

    public List<PedidoDto> buscarPedidosEntregues(String diaInicio, String diaFim) {
        LocalDateTime dtInicio = converterData(diaInicio).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dtFim = converterData(diaFim).withHour(23).withMinute(59).withSecond(59);
        return PedidoRepositoryJdbc.getEntregues(dtInicio, dtFim);
    }

    public List<PedidoDto> buscarPedidosCanceladosAEstornar() {
        return PedidoRepositoryJdbc.getCanceladosAEstornar();
    }

    public List<PedidoDto> buscarPedidosCanceladosEEstornados() {
        return PedidoRepositoryJdbc.getCanceladosEEstornados();
    }

    public Boolean setAceito(Long idPedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);
        if(pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setAceito(true);
            pedidoRepository.save(pedido);
            return true;
        } else {
            return false;
        }
    }

    public Boolean setCancelado(Long idPedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);
        if(pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setCancelado(true);
            // chamar fila estorno cartao
            pedidoRepository.save(pedido);
            return true;
        } else {
            return false;
        }
    }

    public Boolean setPronto(Long idPedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);
        if(pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setPronto(true);
            pedidoRepository.save(pedido);
            return true;
        } else {
            return false;
        }
    }

    public Boolean setEntregue(Long idPedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(idPedido);
        if(pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setEntregue(true);
            pedidoRepository.save(pedido);
            return true;
        } else {
            return false;
        }
    }


    public LocalDateTime converterData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime dateTime = LocalDate.parse(data, formatter).atStartOfDay();
        return dateTime;
    }

}

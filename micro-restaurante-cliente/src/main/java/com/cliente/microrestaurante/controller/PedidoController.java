package com.cliente.microrestaurante.controller;

import com.cliente.microrestaurante.controller.dto.PedidoDto;
import com.cliente.microrestaurante.modelo.Pedido;
import com.cliente.microrestaurante.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<PedidoDto> buscarEspecifico(@RequestParam(required = true) Long usuario,
                                                      @RequestParam(required = true)Long pedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.carregaPedidoEspecifico(usuario, pedido);
        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(new PedidoDto(pedidoOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public Page<PedidoDto> buscarTodos(@RequestParam(required = true) Long usuario,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                    Pageable paginacao) {
        Page<Pedido> pedidos = pedidoRepository.carregaPedidosTodos(usuario, paginacao);
        return PedidoDto.converter(pedidos);
    }

}

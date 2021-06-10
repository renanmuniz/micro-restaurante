package com.cliente.microrestaurante.controller;

import com.cliente.microrestaurante.controller.dto.PedidoDto;
import com.cliente.microrestaurante.controller.form.ProdutosPedidoForm;
import com.cliente.microrestaurante.modelo.Pedido;
import com.cliente.microrestaurante.modelo.Produto;
import com.cliente.microrestaurante.modelo.ProdutosPedido;
import com.cliente.microrestaurante.repository.PedidoRepository;
import com.cliente.microrestaurante.repository.ProdutoRepository;
import com.cliente.microrestaurante.repository.ProdutosPedidoRepository;
import com.cliente.microrestaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<PedidoDto> buscarEspecifico(@RequestParam(required = true) Long usuario,
                                                      @RequestParam(required = true) Long pedido) {
        PedidoDto ped = pedidoService.buscarEspecifico(usuario, pedido);
        if (ped != null) {
            return ResponseEntity.ok(ped);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public Page<PedidoDto> buscarTodos(@RequestParam(required = true) Long usuario,
                                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                               Pageable paginacao) {
        return pedidoService.buscarTodos(usuario, paginacao);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoDto> cadastrar(@RequestParam(required = true) Long idUsuario,
                                               @RequestBody List<ProdutosPedidoForm> produtos) {
        return ResponseEntity.ok(pedidoService.cadastrar(idUsuario, produtos));
    }

}

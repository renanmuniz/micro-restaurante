package com.microrestaurante.controller;

import com.microrestaurante.controller.dto.PedidoDto;
import com.microrestaurante.controller.dto.ProdutoDto;
import com.microrestaurante.controller.dto.ProdutosPedidoDto;
import com.microrestaurante.controller.form.ProdutoForm;
import com.microrestaurante.repository.PedidoRepository;
import com.microrestaurante.service.PedidoService;
import com.microrestaurante.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> buscarPorId(@PathVariable Long id) {
        PedidoDto pedido = pedidoService.buscarPedido(id);
        if(pedido!=null){
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detalhe/{id}")
    public ResponseEntity<List<ProdutosPedidoDto>> detalhar(@PathVariable Long id) {
        List<ProdutosPedidoDto> produtosPedido = pedidoService.buscarProdutosPedido(id);
        if (produtosPedido != null) {
            return ResponseEntity.ok(produtosPedido);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/aceitar")
    public ResponseEntity<List<PedidoDto>> listarPagosAAceitar() {
        List<PedidoDto> pedidos = pedidoService.buscarPedidosPagosAAceitar();
        if (pedidos != null) {
            return ResponseEntity.ok(pedidos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/preparar")
    public ResponseEntity<List<PedidoDto>> listarPagosAPreparar() {
        List<PedidoDto> pedidos = pedidoService.buscarPedidosAceitosAPreparar();
        if (pedidos != null) {
            return ResponseEntity.ok(pedidos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/entregar")
    public ResponseEntity<List<PedidoDto>> listarProntosAEntregar() {
        List<PedidoDto> pedidos = pedidoService.buscarPedidosProntosAEntregar();
        if (pedidos != null) {
            return ResponseEntity.ok(pedidos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/finalizados")
    public ResponseEntity<List<PedidoDto>> listarFinalizados(@RequestParam(required = true) String diaInicio,
                                                             @RequestParam(required = true) String diaFim) {
        List<PedidoDto> pedidos = pedidoService.buscarPedidosEntregues(diaInicio,diaFim);
        if (pedidos != null) {
            return ResponseEntity.ok(pedidos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cancelados")
    public ResponseEntity<List<PedidoDto>> listarCancelados() {
        List<PedidoDto> pedidos = pedidoService.buscarPedidosCanceladosEEstornados();
        if (pedidos != null) {
            return ResponseEntity.ok(pedidos);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/canceladosfaltaestornar")
    public ResponseEntity<List<PedidoDto>> listarCanceladosFaltaEstornar() {
        List<PedidoDto> pedidos = pedidoService.buscarPedidosCanceladosAEstornar();
        if (pedidos != null) {
            return ResponseEntity.ok(pedidos);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/aceitar/{id}")
    @Transactional
    public ResponseEntity<PedidoDto> setAceito(@PathVariable Long id) {
        Boolean aceito = pedidoService.setAceito(id);
        if (aceito) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cancelar/{id}")
    @Transactional
    public ResponseEntity<PedidoDto> setCancelado(@PathVariable Long id) {
        Boolean aceito = pedidoService.setCancelado(id);
        if (aceito) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/pronto/{id}")
    @Transactional
    public ResponseEntity<PedidoDto> setPronto(@PathVariable Long id) {
        Boolean pronto = pedidoService.setPronto(id);
        if (pronto) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/entregue/{id}")
    @Transactional
    public ResponseEntity<PedidoDto> setEntregue(@PathVariable Long id) {
        Boolean pronto = pedidoService.setEntregue(id);
        if (pronto) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

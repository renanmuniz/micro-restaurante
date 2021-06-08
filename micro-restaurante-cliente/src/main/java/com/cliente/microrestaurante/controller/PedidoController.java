package com.cliente.microrestaurante.controller;

import com.cliente.microrestaurante.controller.dto.PedidoDto;
import com.cliente.microrestaurante.controller.form.ProdutosPedidoForm;
import com.cliente.microrestaurante.modelo.Pedido;
import com.cliente.microrestaurante.modelo.Produto;
import com.cliente.microrestaurante.modelo.ProdutosPedido;
import com.cliente.microrestaurante.repository.PedidoRepository;
import com.cliente.microrestaurante.repository.ProdutoRepository;
import com.cliente.microrestaurante.repository.ProdutosPedidoRepository;
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
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutosPedidoRepository produtosPedidoRepository;

    @GetMapping
    public ResponseEntity<PedidoDto> buscarEspecifico(@RequestParam(required = true) Long usuario,
                                                      @RequestParam(required = true) Long pedido) {
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

    @PostMapping
    @Transactional
    public ResponseEntity<PedidoDto> cadastrar(@RequestParam(required = true) Long idUsuario,
                                               @RequestBody List<ProdutosPedidoForm> produtos,
                                               UriComponentsBuilder uriBuilder) {

        Pedido pedido = new Pedido(idUsuario, LocalDateTime.now());
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        Double valorTotalAux = 0.0;

        for(int x = 0; x < produtos.size(); x++) {
            ProdutosPedido produtoPedido = produtos.get(x).converter();
            Produto p = produtoRepository.findById(produtos.get(x).idProduto).get();
            produtoPedido.setIdPedido(pedidoSalvo.id);
            produtoPedido.setValor(produtoPedido.quantidade * p.preco);
            produtosPedidoRepository.save(produtoPedido);
            valorTotalAux += produtoPedido.quantidade * p.preco;
        }

        pedido.valorTotal = valorTotalAux;
        pedidoRepository.save(pedido);

        return ResponseEntity.ok(new PedidoDto(pedido));
    }

}

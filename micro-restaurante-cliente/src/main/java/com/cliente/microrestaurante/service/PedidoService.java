package com.cliente.microrestaurante.service;

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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutosPedidoRepository produtosPedidoRepository;

    public PedidoDto buscarEspecifico(Long usuario, Long pedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.carregaPedidoEspecifico(usuario, pedido);
        if (pedidoOptional.isPresent()) {
            return new PedidoDto(pedidoOptional.get());
        }
        return null;
    }

    public Page<PedidoDto> buscarTodos(Long usuario, Pageable paginacao) {
        Page<Pedido> pedidos = pedidoRepository.carregaPedidosTodos(usuario, paginacao);
        return PedidoDto.converter(pedidos);
    }

    public PedidoDto cadastrar(Long idUsuario, List<ProdutosPedidoForm> produtos) {
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
        return new PedidoDto(pedido);
    }

    public Pedido setarPago(Long idPedido, Boolean pago, String uuidcompra) {
        Pedido pedido = pedidoRepository.getOne(idPedido);
        pedido.setUuidpagamento(uuidcompra);
        pedido.setPago(pago);
        pedidoRepository.save(pedido);
        return pedido;
    }



}

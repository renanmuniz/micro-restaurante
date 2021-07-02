package com.cliente.microrestaurante.repository;

import com.cliente.microrestaurante.config.jdbc.ConnectionFactory;
import com.cliente.microrestaurante.controller.dto.ProdutosPedidoDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositoryJdbc {

    public static List<ProdutosPedidoDto> getProdutosPedido(Long idUsuario, Long idPedido){

        String sql = "SELECT produto.nome, produto.tamanho, produtos_pedido.quantidade, produto.preco, produtos_pedido.valor" +
                " FROM produtos_pedido" +
                " INNER JOIN produto ON produtos_pedido.id_produto = produto.id" +
                " INNER JOIN pedido ON produtos_pedido.id_pedido = pedido.id" +
                " WHERE pedido.id_usuario = ?" +
                " AND pedido.id = ?";

        try {
            java.util.List<ProdutosPedidoDto> produtos = new ArrayList<ProdutosPedidoDto>();
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
            stmt.setString(1,idUsuario.toString());
            stmt.setString(2,idPedido.toString());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                ProdutosPedidoDto produto = new ProdutosPedidoDto();
                produto.setNomeProduto(rs.getString("produto.nome"));
                produto.setTamanhoProduto(rs.getString("produto.tamanho"));
                produto.setQuantidadeProduto(rs.getDouble("produtos_pedido.quantidade"));
                produto.setPrecoProduto(rs.getDouble("produto.preco"));
                produto.setSubtotal(rs.getDouble("produtos_pedido.valor"));
                produtos.add(produto);
            }
            rs.close();
            stmt.close();
            return produtos;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}

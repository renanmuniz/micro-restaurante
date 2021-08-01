package com.microrestaurante.repository;

import com.microrestaurante.config.jdbc.ConnectionFactory;
import com.microrestaurante.controller.dto.PedidoDto;
import com.microrestaurante.controller.dto.ProdutosPedidoDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositoryJdbc {

    public static PedidoDto getPedido(Long idPedido) {
        String sql = "select " +
                "pedido.id, usuario.nome, pedido.valortotal, pedido.dthrpedido, pedido.pago, pedido.aceito, " +
                "pedido.pronto, pedido.entregue, pedido.cancelado, pedido.estornado, pedido.dthrfinalizado, " +
                "pedido.uuidpagamento " +
                "from pedido " +
                "inner join usuario on (pedido.id_usuario = usuario.id) " +
                "where pedido.id = ?;";
        try {
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
            stmt.setString(1, idPedido.toString());
            ResultSet rs = stmt.executeQuery();
            PedidoDto pedido = new PedidoDto();
            while (rs.next()) {
                pedido.setId(rs.getLong("pedido.id"));
                pedido.setNome(rs.getString("usuario.nome"));
                pedido.setValorTotal(rs.getDouble("pedido.valortotal"));
                pedido.setDtHrPedido(rs.getString("pedido.dthrpedido"));
                pedido.setPago(rs.getBoolean("pedido.pago"));
                pedido.setAceito(rs.getBoolean("pedido.aceito"));
                pedido.setPronto(rs.getBoolean("pedido.pronto"));
                pedido.setEntregue(rs.getBoolean("pedido.entregue"));
                pedido.setCancelado(rs.getBoolean("pedido.cancelado"));
                pedido.setEstornado(rs.getBoolean("pedido.estornado"));
                pedido.setDtHrFinalizado(rs.getString("pedido.dthrfinalizado"));
                pedido.setUuidpagamento(rs.getString("pedido.uuidpagamento"));
            }
            rs.close();
            stmt.close();
            return pedido;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<ProdutosPedidoDto> getProdutosPedido(Long idPedido) {
        String sql = "SELECT produto.nome, produto.tamanho, produtos_pedido.quantidade, produto.preco, produtos_pedido.valor" +
                " FROM produtos_pedido" +
                " INNER JOIN produto ON produtos_pedido.id_produto = produto.id" +
                " INNER JOIN pedido ON produtos_pedido.id_pedido = pedido.id" +
                " WHERE pedido.id = ?";

        try {
            List<ProdutosPedidoDto> produtos = new ArrayList<ProdutosPedidoDto>();
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
            stmt.setString(1, idPedido.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
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

    public static List<PedidoDto> getPagosAAceitar() {
        String sql = "select pedido.id, usuario.nome, pedido.valortotal, pedido.dthrpedido, pedido.pago, " +
                "pedido.aceito, pedido.pronto, pedido.entregue, pedido.cancelado, pedido.estornado, " +
                "pedido.dthrfinalizado, pedido.uuidpagamento " +
                "from pedido " +
                "inner join usuario on (pedido.id_usuario = usuario.id) " +
                "where pago = true " +
                "and aceito = false " +
                "and pronto = false " +
                "and entregue = false " +
                "and cancelado = false " +
                "and estornado = false;";
        try {
            List<PedidoDto> pedidos = new ArrayList<PedidoDto>();
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoDto pedido = new PedidoDto();
                pedido.setId(rs.getLong("pedido.id"));
                pedido.setNome(rs.getString("usuario.nome"));
                pedido.setValorTotal(rs.getDouble("pedido.valortotal"));
                pedido.setDtHrPedido(rs.getString("pedido.dthrpedido"));
                pedido.setPago(rs.getBoolean("pedido.pago"));
                pedido.setAceito(rs.getBoolean("pedido.aceito"));
                pedido.setPronto(rs.getBoolean("pedido.pronto"));
                pedido.setEntregue(rs.getBoolean("pedido.entregue"));
                pedido.setCancelado(rs.getBoolean("pedido.cancelado"));
                pedido.setEstornado(rs.getBoolean("pedido.estornado"));
                pedido.setDtHrFinalizado(rs.getString("pedido.dthrfinalizado"));
                pedido.setUuidpagamento(rs.getString("pedido.uuidpagamento"));
                pedidos.add(pedido);
            }

            rs.close();
            stmt.close();
            return pedidos;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<PedidoDto> getAceitosAPreparar() {
        String sql = "select pedido.id, usuario.nome, pedido.valortotal, pedido.dthrpedido, pedido.pago, " +
                "pedido.aceito, pedido.pronto, pedido.entregue, pedido.cancelado, pedido.estornado, " +
                "pedido.dthrfinalizado, pedido.uuidpagamento " +
                "from pedido " +
                "inner join usuario on (pedido.id_usuario = usuario.id) " +
                "where pago = true " +
                "and aceito = true " +
                "and pronto = false " +
                "and entregue = false " +
                "and cancelado = false " +
                "and estornado = false;";
        try {
            List<PedidoDto> pedidos = new ArrayList<PedidoDto>();
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoDto pedido = new PedidoDto();
                pedido.setId(rs.getLong("pedido.id"));
                pedido.setNome(rs.getString("usuario.nome"));
                pedido.setValorTotal(rs.getDouble("pedido.valortotal"));
                pedido.setDtHrPedido(rs.getString("pedido.dthrpedido"));
                pedido.setPago(rs.getBoolean("pedido.pago"));
                pedido.setAceito(rs.getBoolean("pedido.aceito"));
                pedido.setPronto(rs.getBoolean("pedido.pronto"));
                pedido.setEntregue(rs.getBoolean("pedido.entregue"));
                pedido.setCancelado(rs.getBoolean("pedido.cancelado"));
                pedido.setEstornado(rs.getBoolean("pedido.estornado"));
                pedido.setDtHrFinalizado(rs.getString("pedido.dthrfinalizado"));
                pedido.setUuidpagamento(rs.getString("pedido.uuidpagamento"));
                pedidos.add(pedido);
            }

            rs.close();
            stmt.close();
            return pedidos;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<PedidoDto> getProntosAEntregar() {
        String sql = "select pedido.id, usuario.nome, pedido.valortotal, pedido.dthrpedido, pedido.pago, " +
                "pedido.aceito, pedido.pronto, pedido.entregue, pedido.cancelado, pedido.estornado, " +
                "pedido.dthrfinalizado, pedido.uuidpagamento " +
                "from pedido " +
                "inner join usuario on (pedido.id_usuario = usuario.id) " +
                "where pago = true " +
                "and aceito = true " +
                "and pronto = true " +
                "and entregue = false " +
                "and cancelado = false " +
                "and estornado = false;";
        try {
            List<PedidoDto> pedidos = new ArrayList<PedidoDto>();
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoDto pedido = new PedidoDto();
                pedido.setId(rs.getLong("pedido.id"));
                pedido.setNome(rs.getString("usuario.nome"));
                pedido.setValorTotal(rs.getDouble("pedido.valortotal"));
                pedido.setDtHrPedido(rs.getString("pedido.dthrpedido"));
                pedido.setPago(rs.getBoolean("pedido.pago"));
                pedido.setAceito(rs.getBoolean("pedido.aceito"));
                pedido.setPronto(rs.getBoolean("pedido.pronto"));
                pedido.setEntregue(rs.getBoolean("pedido.entregue"));
                pedido.setCancelado(rs.getBoolean("pedido.cancelado"));
                pedido.setEstornado(rs.getBoolean("pedido.estornado"));
                pedido.setDtHrFinalizado(rs.getString("pedido.dthrfinalizado"));
                pedido.setUuidpagamento(rs.getString("pedido.uuidpagamento"));
                pedidos.add(pedido);
            }

            rs.close();
            stmt.close();
            return pedidos;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<PedidoDto> getEntregues(LocalDateTime dtInicio, LocalDateTime dtFim) {
        String sql = "select pedido.id, usuario.nome, pedido.valortotal, pedido.dthrpedido, pedido.pago, " +
                "pedido.aceito, pedido.pronto, pedido.entregue, pedido.cancelado, pedido.estornado, " +
                "pedido.dthrfinalizado, pedido.uuidpagamento " +
                "from pedido " +
                "inner join usuario on (pedido.id_usuario = usuario.id) " +
                "where pago = true " +
                "and aceito = true " +
                "and pronto = true " +
                "and entregue = true " +
                "and cancelado = false " +
                "and estornado = false " +
                "and pedido.dthrpedido between ? and ?;";
        try {
            List<PedidoDto> pedidos = new ArrayList<PedidoDto>();
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
            stmt.setString(1, dtInicio.toString());
            stmt.setString(2, dtFim.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoDto pedido = new PedidoDto();
                pedido.setId(rs.getLong("pedido.id"));
                pedido.setNome(rs.getString("usuario.nome"));
                pedido.setValorTotal(rs.getDouble("pedido.valortotal"));
                pedido.setDtHrPedido(rs.getString("pedido.dthrpedido"));
                pedido.setPago(rs.getBoolean("pedido.pago"));
                pedido.setAceito(rs.getBoolean("pedido.aceito"));
                pedido.setPronto(rs.getBoolean("pedido.pronto"));
                pedido.setEntregue(rs.getBoolean("pedido.entregue"));
                pedido.setCancelado(rs.getBoolean("pedido.cancelado"));
                pedido.setEstornado(rs.getBoolean("pedido.estornado"));
                pedido.setDtHrFinalizado(rs.getString("pedido.dthrfinalizado"));
                pedido.setUuidpagamento(rs.getString("pedido.uuidpagamento"));
                pedidos.add(pedido);
            }

            rs.close();
            stmt.close();
            return pedidos;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<PedidoDto> getCanceladosAEstornar() {
        String sql = "select pedido.id, usuario.nome, pedido.valortotal, pedido.dthrpedido, pedido.pago, " +
                "pedido.aceito, pedido.pronto, pedido.entregue, pedido.cancelado, pedido.estornado, " +
                "pedido.dthrfinalizado, pedido.uuidpagamento " +
                "from pedido " +
                "inner join usuario on (pedido.id_usuario = usuario.id) " +
                "where pago = true " +
                "and aceito = false " +
                "and pronto = false " +
                "and entregue = false " +
                "and cancelado = true " +
                "and estornado = false;";
        try {
            List<PedidoDto> pedidos = new ArrayList<PedidoDto>();
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoDto pedido = new PedidoDto();
                pedido.setId(rs.getLong("pedido.id"));
                pedido.setNome(rs.getString("usuario.nome"));
                pedido.setValorTotal(rs.getDouble("pedido.valortotal"));
                pedido.setDtHrPedido(rs.getString("pedido.dthrpedido"));
                pedido.setPago(rs.getBoolean("pedido.pago"));
                pedido.setAceito(rs.getBoolean("pedido.aceito"));
                pedido.setPronto(rs.getBoolean("pedido.pronto"));
                pedido.setEntregue(rs.getBoolean("pedido.entregue"));
                pedido.setCancelado(rs.getBoolean("pedido.cancelado"));
                pedido.setEstornado(rs.getBoolean("pedido.estornado"));
                pedido.setDtHrFinalizado(rs.getString("pedido.dthrfinalizado"));
                pedido.setUuidpagamento(rs.getString("pedido.uuidpagamento"));
                pedidos.add(pedido);
            }

            rs.close();
            stmt.close();
            return pedidos;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<PedidoDto> getCanceladosEEstornados() {
        String sql = "select pedido.id, usuario.nome, pedido.valortotal, pedido.dthrpedido, pedido.pago, " +
                "pedido.aceito, pedido.pronto, pedido.entregue, pedido.cancelado, pedido.estornado, " +
                "pedido.dthrfinalizado, pedido.uuidpagamento " +
                "from pedido " +
                "inner join usuario on (pedido.id_usuario = usuario.id) " +
                "where pago = true " +
                "and aceito = true " +
                "and pronto = false " +
                "and entregue = false " +
                "and cancelado = true " +
                "and estornado = true;";
        try {
            List<PedidoDto> pedidos = new ArrayList<PedidoDto>();
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PedidoDto pedido = new PedidoDto();
                pedido.setId(rs.getLong("pedido.id"));
                pedido.setNome(rs.getString("usuario.nome"));
                pedido.setValorTotal(rs.getDouble("pedido.valortotal"));
                pedido.setDtHrPedido(rs.getString("pedido.dthrpedido"));
                pedido.setPago(rs.getBoolean("pedido.pago"));
                pedido.setAceito(rs.getBoolean("pedido.aceito"));
                pedido.setPronto(rs.getBoolean("pedido.pronto"));
                pedido.setEntregue(rs.getBoolean("pedido.entregue"));
                pedido.setCancelado(rs.getBoolean("pedido.cancelado"));
                pedido.setEstornado(rs.getBoolean("pedido.estornado"));
                pedido.setDtHrFinalizado(rs.getString("pedido.dthrfinalizado"));
                pedido.setUuidpagamento(rs.getString("pedido.uuidpagamento"));
                pedidos.add(pedido);
            }

            rs.close();
            stmt.close();
            return pedidos;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}

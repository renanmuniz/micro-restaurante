package com.microrestaurante.config.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
    public static void testarConexao() {
        try {
            System.out.println("[JDBC] Testando conexão com banco de dados via JDBC...");
            Connection conn = new ConnectionFactory().getConnection();
            System.out.println("[JDBC] Conexao aberta...");
            conn.close();
            System.out.println("[JDBC] Teste bem sucedido.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

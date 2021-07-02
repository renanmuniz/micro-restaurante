package com.cliente.microrestaurante.config.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    String urlDb="jdbc:mysql://localhost:3306/restaurantedb?useTimeZone=true&serverTimezone=America/Sao_Paulo";

    String usuarioDb="root";

    String senhaDb="3885";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(urlDb, usuarioDb, senhaDb);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

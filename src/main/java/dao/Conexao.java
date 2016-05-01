/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anderson
 */
public class Conexao {

    private Connection obterConexao() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        String connectionUrl = "jdbc:sqlserver://localhost:1433"
                + "databaseName=Namtab";
        try {
            // Passo 1: Registrar driver JDBC.
            Class.forName("com.microsoft,sqlserver.jdbc.SQLServerDriver").newInstance();
            // abrir conexao
            conn = DriverManager.getConnection(connectionUrl, "login", "senha");

        } catch (Exception ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

}

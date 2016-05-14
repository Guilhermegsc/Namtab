/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anderson
 */
public class ProdutoDAO {
        private Connection obterConexao() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        // Passo 1: Registrar driver JDBC.
        Class.forName("org.apache.derby.jdbc.ClientDataSource");

        // Passo 2: Abrir a conexão
        conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/posto;SecurityMechanism=3",
                "app", // usuario
                "app"); // senha
        return conn;
    }

    public ArrayList<Produto> listaProduto() {
        Statement stmt = null;
        Connection conn = null;
        Combustivel comb = null;

        String sql = "SELECT ID_PRODUTO, NOME_PROODUTO, PRECO, CATEGORIA, TAMANHO, TIPO"
                + " FROM PRODUTO WHERE STATUS_PRODUTO = TRUE ";

        ArrayList<Produto> lista = new ArrayList();

        try {
            conn = obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);


            while (resultados.next()) {
                int idProduto = resultados.getInt("ID_PRODUTO");
                String nome = resultados.getString("NOME_PROODUTO");
                double preco = resultados.getDouble("PRECO");
                String categoria = resultados.getString("CATEGORIA");
                double tamanho = resultados.getDouble("TAMANHO");
                String tipo = resultados.getString("TIPO");
                            
                switch (idProduto) {
                    // lista oleo
                    case 1:
                        OleoLubrificante ol = new OleoLubrificante(idProduto, nome, preco, tamanho);
                        lista.add(ol);
                        break;
                    // lista extintor    
                    case 2:
                        Extintor ext = new Extintor(idProduto, nome, preco, categoria, tamanho);
                        lista.add(ext);
                        break;
                    // lista combustivel    
                    default:
                        Combustivel com = new Combustivel(idProduto, nome, preco, tipo);
                        lista.add(com);
                        break;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Código colocado aqui para garantir que a conexão com o banco
            // seja sempre fechada, independentemente se executado com sucesso
            // ou erro.
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }

    public void atualizarProduto(Produto prod) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = " UPDATE INTO PRODUTO (PRECO, DATA_MODIFICACAO) "
                + " VALUES (?, ?) WHERE ID_PRODUTO = '?' ";
        try {
            conn = obterConexao();

            conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, prod.getPreco());
            stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            stmt.setInt(1, prod.getIdProduto());

            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException ex) {
            try {
                // Caso ocorra algum erro, tenta desfazer todas as ações realizadas no BD.
                if (conn != null & !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            try {
                // Caso ocorra algum erro, tenta desfazer todas as ações realizadas no BD.
                if (conn != null & !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}

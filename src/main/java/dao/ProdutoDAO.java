/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public Usuario buscarUsuario(String idUsuario) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Usuario us = null;

        String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL "
                + "FROM TB_CONTATO WHERE ID_CONTATO = ?";
        try {

            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idUsuario);
            ResultSet resultados = stmt.executeQuery();

            while (resultados.next()) {
                String id = resultados.getString("CPF");
                String nome = resultados.getString("nome");
                String senha = resultados.getString("senha");
                int idFilial = resultados.getInt("idFilial");
                int perfil = resultados.getInt("perfil");
                java.util.Date dataNasc = resultados.getDate("dataNasc");
                String funcao = resultados.getString("funcao");
                us = new Usuario(idUsuario, nome, idFilial, perfil, funcao);
                break;
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
        return us;
    }

    public ArrayList<Combustivel> listaProduto() {
        Statement stmt = null;
        Connection conn = null;
        Combustivel comb = null;

        String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL "
                + "FROM TB_CONTATO";

        ArrayList<Combustivel> lista = new ArrayList();

        try {
            conn = obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

            while (resultados.next()) {
                String nome = resultados.getString("nome");
                double preco = resultados.getDouble("preco");
                String idUsuario = resultados.getString("idUsuario");
                double quantidade = resultados.getDouble("quantidade");
                int idFilial = resultados.getInt("idFilial");
                String funcao = resultados.getString("funcao");
                Date dtOcorrencia = resultados.getDate("dataOcorrencia");
                             

                comb = new Combustivel(nome, preco, idUsuario, quantidade, idFilial, dtOcorrencia);

                lista.add(comb);
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

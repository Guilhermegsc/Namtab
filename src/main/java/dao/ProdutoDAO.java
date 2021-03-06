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
public class ProdutoDAO extends Conexao{


    public ArrayList<Produto> listaProduto() {
        Statement stmt = null;
        Connection conn = null;
        Combustivel comb = null;

        String sql = "SELECT ID_PRODUTO, NOME_PRODUTO, PRECO, TIPO, CATEGORIA, TAMANHO"
                + " FROM PRODUTO WHERE STATUS_PRODUTO = TRUE ";

        ArrayList<Produto> lista = new ArrayList();

        try {
            conn = obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            while (resultados.next()) {
                int idProduto = resultados.getInt("ID_PRODUTO");
                String nome = resultados.getString("NOME_PRODUTO");
                double preco = resultados.getDouble("PRECO");
                String tipo = resultados.getString("TIPO");
                String categoria = resultados.getString("CATEGORIA");
                double tamanho = resultados.getDouble("TAMANHO");

                switch (tipo) {

                    case "COMB":  // lista combustivel   
                        Combustivel com = new Combustivel(idProduto, nome, preco, tipo);
                        lista.add(com);
                        break;
                    case "PROD":
                        if (idProduto == 5) {  // lista oleo 
                            OleoLubrificante ol = new OleoLubrificante(idProduto, nome, preco, tamanho, tipo);
                            lista.add(ol);
                            break;
                        } else if (idProduto == 6) {  // lista extintor
                            Extintor ext = new Extintor(idProduto, nome, preco, tipo, categoria, tamanho);
                            lista.add(ext);
                            break;
                        }
                    default:
                        Produto outro = new Produto(idProduto, nome, preco, tipo);
                        lista.add(outro);
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

    public void atualizarProduto(int idProduto, double novoPreco) {
        Statement stmt = null;
        Connection conn = null;

        String sql = " UPDATE PRODUTO SET PRECO = "
                +novoPreco+" WHERE ID_PRODUTO = "+idProduto;
        try {
            conn = obterConexao();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

     /*       conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, novoPreco);
            stmt.setInt(1, idProduto);

            stmt.executeUpdate();
            conn.commit();*/
            

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

    public double buscaPreco(int idProduto) {
        Statement stmt = null;
        Connection conn = null;
        double preco = 0;

        String sql = " SELECT PRECO FROM PRODUTO WHERE ID_PRODUTO = " + idProduto;
        try {
            conn = obterConexao();

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                preco = rs.getDouble("PRECO");
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
        return preco;
    }

}

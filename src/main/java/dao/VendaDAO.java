package dao;

import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO extends Conexao {

    Conexao conecta = new Conexao();

    public void vendaOleo(OleoLubrificante comb) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "INSERT INTO VENDA (CPF, ID_FILIAL, ID_PRODUTO, PRECO_PRODUTO, QUANTIDADE, VALOR_VENDA, STATUS_VENDA)"
                + " VALUES ('?', ?, ?, ?, ?, ?, TRUE)";
        try {
            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();
            ProdutoDAO prod = new ProdutoDAO();
            double precoProd = prod.buscaPreco(comb.getIdProduto());

            conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, comb.getIdUsuario());
            stmt.setInt(2, comb.getIdFilial());
            stmt.setInt(3, comb.getIdProduto());
            stmt.setDouble(4, precoProd);
            stmt.setDouble(5, comb.getQuantidade());
            stmt.setDouble(6, (comb.getQuantidade() * precoProd));

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

    public void vendaExtintor(Extintor ext) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "INSERT INTO VENDA (CPF, ID_FILIAL, ID_PRODUTO, PRECO_PRODUTO, QUANTIDADE, VALOR_VENDA, STATUS_VENDA)"
                + " VALUES ('?', ?, ?, ?, ?, ?, TRUE) ";
        try {
            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();
            ProdutoDAO prod = new ProdutoDAO();
            double precoProd = prod.buscaPreco(ext.getIdProduto());

            conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ext.getIdUsuario());
            stmt.setInt(2, ext.getIdFilial());
            stmt.setInt(3, ext.getIdProduto());
            stmt.setDouble(4, precoProd);
            stmt.setDouble(5, ext.getQuantidade());
            stmt.setDouble(6, (ext.getQuantidade() * precoProd));

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

    public void vendaCombustivel(Combustivel comb) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "INSERT INTO VENDA (CPF, ID_FILIAL, ID_PRODUTO, PRECO_PRODUTO, QUANTIDADE, VALOR_VENDA, STATUS_VENDA)"
                + " VALUES ('?', ?, ?, ?, ?, ?, TRUE)";
        try {
            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();
            ProdutoDAO prod = new ProdutoDAO();
            double precoProd = prod.buscaPreco(comb.getIdProduto());

            conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, comb.getIdUsuario());
            stmt.setInt(2, comb.getIdFilial());
            stmt.setInt(3, comb.getIdProduto());
            stmt.setDouble(4, precoProd);
            stmt.setDouble(5, comb.getValorVenda() / precoProd);
            stmt.setDouble(6, (comb.getValorVenda()));

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
    
    public ArrayList<Produto> listarProdutos() {
        Statement stmt = null;
        Connection conn = null;
        Usuario us = null;

        String sql = "SELECT ID_VENDA, ID_FILIAL, CPF, ID_PRODUTO, DATA_VENDA, "
                + "VALOR_VENDA, QUANTIDADE, PRECO_PRODUTO FROM VENDA WHERE STATUS_VENDA = TRUE";

        ArrayList<Produto> lista = new ArrayList();

        try {
            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            while (resultados.next()) {
                int idVenda = resultados.getInt("ID_VENDA");
                int idFilial = resultados.getInt("ID_FILIAL");
                String idUsuario = resultados.getString("CPF");
                int idProduto = resultados.getInt("ID_PRODUTO");
                Date dataVenda = resultados.getDate("DATA_VENDA");
                double valorVenda = resultados.getDouble("VALOR_VENDA");
                double quantidade = resultados.getDouble("QUANTIDADE");
                double precoProd = resultados.getDouble("PRECO_PRODUTO");

                // us = new Usuario(id, nome, idFilial, perfil, funcao);

                // lista.add(us);
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

    public void inativarVenda(int idVenda) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE VENDA SET STATUS_VENDA = FALSE WHERE ID_VENDA = '" + idVenda + "'";
        try {
            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            //System.out.println("Registro incluido com sucesso.");

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
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

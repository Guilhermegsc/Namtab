package dao;

import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO extends Conexao {

    Conexao conecta = new Conexao();

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void vendaOleo(OleoLubrificante comb) {
        PreparedStatement stmt = null;
        Connection conn = null;
        
        String sql = "INSERT INTO VENDA (CPF, ID_FILIAL, ID_PRODUTO, DATA_VENDA, PRECO_PRODUTO, QUANTIDADE, VALOR_VENDA, STATUS_VENDA)"
                + " VALUES ('?', ?, ?, '?', ?, ?, ?, TRUE)";
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
            stmt.setString(4, getDateTime());
            stmt.setDouble(5, precoProd);
            stmt.setDouble(6, comb.getQuantidade());
            stmt.setDouble(7, (comb.getQuantidade()/precoProd));

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

    public void insertSQL(String comandoSQL) throws Exception {

        try {
            Statement st = null;
            st = conecta.obterConexao().createStatement();
            st.executeUpdate(comandoSQL);

        } catch (Exception e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public void inativarVenda(int idVenda) {
        String comandoSql = "UPDATE VENDA SET statusVenda = false WHERE "
                + "idVenda = " + idVenda;

        try {
            insertSQL(comandoSql);
        } catch (Exception e) {
            System.out.println("Erro ao inativar: " + e.getMessage());
        }

    }

}

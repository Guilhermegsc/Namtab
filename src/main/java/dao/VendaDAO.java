package dao;

import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO extends Conexao {

    Conexao conecta = new Conexao();

    public int efetuaVenda(String CPF, int idFilial, int idProduto,
            java.sql.Date dataVenda,
            float valorVenda
    ) throws SQLException, ClassNotFoundException {
                    String comandoSQL = "INSERT INTO VENDA(CPF, idFilial, idProduto,"
                    + "dataVenda,valorVenda, statusVenda ) VALUES("
                    + CPF + "'," + idFilial + "'," + idProduto
                    + "'," + dataVenda + "'," + valorVenda + "'," + true + ");";

        try {   
            insertSQL(comandoSQL);
            return 1;
        } catch (Exception e) {
            return 0;
        } finally {
            conecta.obterConexao().close();
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
    
    public void inativarVenda(int idVenda){
        String comandoSql = "UPDATE VENDA SET statusVenda = false WHERE "
                + "idVenda = "+idVenda;
        
        try {
            insertSQL(comandoSql);
        } catch (Exception e) {
            System.out.println("Erro ao inativar: " + e.getMessage());
        }
        
        
    }
    
     public void vendaCombustivel (Combustivel com) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "INSERT INTO USUARIO(CPF,NOME,SENHA,ID_FILIAL,PERFIL,FUNCAO,STATUS_USUARIO) "
                + "     VALUES (?,?,?,?,?,?,true)";
        try {
            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();

            conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, us.getIdUsuario());
            stmt.setString(2, us.getNome());
            stmt.setString(3, us.getSenha());
            stmt.setInt(4, us.getIdFilial());
            stmt.setInt(5, us.getTipoPerfil());
            stmt.setString(6, us.getFuncao());

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

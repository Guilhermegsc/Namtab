package dao;

import entity.Filial;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thiago
 */
public class FiliaisDAO extends Conexao{

    public ArrayList<Filial> listaFiliais() {
        Statement stmt = null;
        Connection conn = null;

        String sql = "SELECT ID_FILIAL, NOME_FILIAL, ESTADO"
                + " FROM FILIAL WHERE STATUS_FILIAL = TRUE ";

        ArrayList<Filial> lista = new ArrayList();

        try {
            conn = obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            while (resultados.next()) {
                int idFilial = resultados.getInt("ID_FILIAL");
                String nomeFilial = resultados.getString("NOME_FILIAL");
                String UF = resultados.getString("ESTADO");
                String descricao = idFilial+" - "+nomeFilial+" - "+UF;
                
                Filial filial = new Filial(idFilial, nomeFilial, UF, descricao);
                lista.add(filial);
                
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
    public Filial buscaFilial(int idFilial) {
        Statement stmt = null;
        Connection conn = null;
        Filial filial = null;
        String sql = "SELECT ID_FILIAL, NOME_FILIAL, ESTADO"
                + " FROM FILIAL WHERE STATUS_FILIAL = TRUE AND ID_FILIAL = " + idFilial + "";


        try {
            conn = obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            while (resultados.next()) {
                String nomeFilial = resultados.getString("NOME_FILIAL");
                String UF = resultados.getString("ESTADO");
                String descricao = idFilial+" - "+nomeFilial+" - "+UF;
                
                filial = new Filial(idFilial, nomeFilial, UF, descricao);
                
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
        return filial;
    }
}

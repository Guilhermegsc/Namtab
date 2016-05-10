/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Usuario;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anderson
 */
public class UsuarioDAO {

    public Usuario buscarUsuario(String idUsuario) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Usuario us = null;

        String sql = " SELECT CPF, NOME, ID_FILIAL, PERFIL, DATA_NASC, FUNCAO FROM USUARIO "
                + "WHERE STATUS_USUARIO = TRUE AND CPF = '?' ";
        try {

            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idUsuario);
            ResultSet resultados = stmt.executeQuery();

            while (resultados.next()) {
                String id = resultados.getString("CPF");
                String nome = resultados.getString("NOME");
                int idFilial = resultados.getInt("ID_FILIAL");
                int perfil = resultados.getInt("PERFIL");
                java.util.Date dataNasc = resultados.getDate("DATA_NASC");
                String funcao = resultados.getString("FUNCAO");
                us = new Usuario(idUsuario, nome, idFilial, perfil, null, funcao);
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

    public ArrayList<Usuario> listarUsuarios() {
        Statement stmt = null;
        Connection conn = null;
        Usuario us = null;

        String sql = "SELECT CPF, NOME, ID_FILIAL, PERFIL, DATA_NASC, FUNCAO "
                + "FROM USUARIO WHERE STATUS_USUARIO = TRUE";

        ArrayList<Usuario> lista = new ArrayList();

        try {
            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

            while (resultados.next()) {
                String id = resultados.getString("CPF");
                String nome = resultados.getString("NOME");
                int idFilial = resultados.getInt("ID_FILIAL");
                int perfil = resultados.getInt("PERFIL");
                Date dataNasc = resultados.getDate("DATA_NASC");
                String funcao = resultados.getString("FUNCAO");

                us = new Usuario(id, nome, idFilial, perfil, dataNasc, funcao);

                lista.add(us);
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

    public static void incluirUsuario(Usuario us) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "INSERT INTO USUARIO(CPF,NOME,SENHA,ID_FILIAL,PERFIL,DATA_NASC,FUNCAO,STATUS_USUARIO) "
                + "     VALUES (?,?,?,?,?,'1995-10-30',?,true)";
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
            stmt.setDate(6, us.getDataNasc());
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

    public void inativarUsuario(String idUsuario) {
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE INTO USUARIO (STATUS_USUARIO) VALUES (FALSE) WHERE CPF = '" + idUsuario + "'";
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

    public void alterarUsuario(Usuario us) {
        PreparedStatement stmt = null;
        Connection conn = null;

        // inserir usuario a ser alterado 
        String sql = "UPDATE INTO USUARIO (NOME, ID_FILIAL, PERFIL, FUNCAO) "
                + "VALUES ('?', ?, ?, '?') WHERE CPF = '?'";
        try {
            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();

            conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, us.getNome());
            stmt.setInt(2, us.getIdFilial());
            stmt.setInt(3, us.getTipoPerfil());
            stmt.setString(4, us.getFuncao());
            stmt.setString(5, us.getIdUsuario());

            stmt.executeUpdate();
            conn.commit();

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

    public void alterarSenha(String idUsuario, String novaSenha) {
        PreparedStatement stmt = null;
        Connection conn = null;

        // inserir usuario a ser alterado 
        String sql = "UPDATE INTO USUARIO (SENHA) VALUES ('" + novaSenha + "') WHERE CPF = '" + idUsuario + "'";
        try {
            Conexao conexao = new Conexao();
            conn = conexao.obterConexao();

            conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, novaSenha);

            stmt.executeUpdate();
            conn.commit();

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
    
    public static void main(String[] args) {
        
       
            
        Usuario us = new Usuario("12345678901", "teste123", "FULANO", 1, 1, null , null);
        incluirUsuario(us);
        
    }
}

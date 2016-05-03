/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anderson
 */
public class UsuarioDAO {

    public void inserirUsuario(Usuario us) {
        Connection conn = null;
        Statement stmt = null;
        Conexao cn = new Conexao();

        try {
            // conecta com o banco
            conn = cn.obterConexao();

            //executa a query
            stmt = conn.createStatement();

            String sql = "INSERT INTO Registration "
                    + "VALUES (100, 'Zara', 'Ali', 18)";
            stmt.executeUpdate(sql);

        } catch (SQLException se) {
            //trata erro do jdbc
            se.printStackTrace();
        } catch (Exception e) {
            //trata erros
            e.printStackTrace();
        } finally {
            //fecha conexão
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public ArrayList<Usuario> listaUsuarios() {
        Connection conn = null;
        Statement stmt = null;
        Conexao cn = new Conexao();

        try {
            // conecta com o banco
            conn = cn.obterConexao();
            //executa a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT id, first, last, age FROM Registration";
            ResultSet rs = stmt.executeQuery(sql);
            
            //popula array
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        //arrumar
        return null;
    }

}

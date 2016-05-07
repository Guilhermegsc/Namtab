package dao;

import java.sql.SQLException;
import java.sql.Statement;

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
    

}

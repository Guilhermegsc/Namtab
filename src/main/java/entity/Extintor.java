package entity;

import java.sql.Date;

/**
 *
 * @author Anderson
 */
public class Extintor extends Produto {
   private String categoria;
   private double tamanho = 0;

    public Extintor(int idProduto, long idUsuario, int idFilial, double quantidade) {
        super(idProduto, idUsuario, idFilial, quantidade);
    }

    public Extintor(int idVenda, int idProduto, int idFilial, long idUsuario, String nomeUsuario, String nomeProduto, 
            double preco, Date dataVenda, double quantidade, double valorVenda, String categoria, double tamanho, String nomeFilial) {
        super(idVenda, idProduto, idFilial, idUsuario, nomeUsuario, nomeProduto, preco, dataVenda, quantidade, valorVenda, nomeFilial);
        this.categoria = categoria.toUpperCase();
        this.tamanho = tamanho;
    }

    public String getCategoria() {
        if (categoria.isEmpty()) {
            return "";
        } else {
            return categoria;
        }
    }

    public double getTamanho() {
        return tamanho;
    }

    public Extintor(int idProduto, String nomeProduto, double preco, String categoria, double tamanho) {
        super(idProduto, nomeProduto, preco);
        this.categoria = categoria.toUpperCase();
        this.tamanho = tamanho;
    }
   
   
}

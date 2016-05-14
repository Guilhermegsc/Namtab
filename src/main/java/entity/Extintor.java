package entity;

import java.sql.Date;

/**
 *
 * @author Anderson
 */
public class Extintor extends Produto {
   private String categoria;
   private double tamanho;

    public Extintor(long idUsuario, int idFilial, int idProduto, double valorVenda, String categoria, double tamanho) {
        super(idUsuario, idFilial, idProduto, valorVenda);
        this.categoria = categoria.toUpperCase();
        this.tamanho = tamanho;
    }

    public Extintor(int idVenda, int idProduto, int idFilial, long idUsuario, String nomeUsuario, String nomeProduto, 
            double preco, Date dataVenda, double quantidade, double valorVenda, String categoria, double tamanho) {
        super(idVenda, idProduto, idFilial, idUsuario, nomeUsuario, nomeProduto, preco, dataVenda, quantidade, valorVenda);
        this.categoria = categoria.toUpperCase();
        this.tamanho = tamanho;
    }

    public String getCategoria() {
        return categoria;
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

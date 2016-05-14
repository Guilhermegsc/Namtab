package entity;

import java.sql.Date;

/**
 *
 * @author Anderson
 */
public class OleoLubrificante extends Produto{

    private double tamanho;

    public OleoLubrificante(long idUsuario, int idFilial, int idProduto, double valorVenda, double tamanho) {
        super(idUsuario, idFilial, idProduto, valorVenda);
        this.tamanho = tamanho;
    }

    public OleoLubrificante(int idVenda, int idProduto, int idFilial, long idUsuario, String nomeUsuario, String nomeProduto, double preco, Date dataVenda, double quantidade, double valorVenda, double tamanho) {
        super(idVenda, idProduto, idFilial, idUsuario, nomeUsuario, nomeProduto, preco, dataVenda, quantidade, valorVenda);
        this.tamanho = tamanho;
    }

    public OleoLubrificante(int idProduto, String nomeProduto, double preco, double tamanho) {
        super(idProduto, nomeProduto, preco);
        this.tamanho = tamanho;
    }

    public OleoLubrificante(int idProduto, long idUsuario, int idFilial, double quantidade) {
        super(idProduto, idUsuario, idFilial, quantidade);
    }
    

    public double getTamanho() {
        return tamanho;
    }
}
package entity;

import java.sql.Date;

/**
 *
 * @author Anderson
 */
public class OleoLubrificante extends Produto{

    private double tamanho = 0;

    public OleoLubrificante(String idUsuario, int idFilial, int idProduto, double valorVenda, double tamanho) {
        super(idUsuario, idFilial, idProduto, valorVenda);
        this.tamanho = tamanho;
    }

    public OleoLubrificante(int idVenda, int idProduto, int idFilial, String idUsuario, String nomeUsuario, String nomeProduto, 
            double preco, Date dataVenda, double quantidade, double valorVenda, double tamanho, String nomeFilial) {
        super(idVenda, idProduto, idFilial, idUsuario, nomeUsuario, nomeProduto, preco, dataVenda, quantidade, valorVenda, nomeFilial);
        this.tamanho = tamanho;
    }

    public OleoLubrificante(int idProduto, String nomeProduto, double preco, double tamanho, String tipo) {
        super(idProduto, nomeProduto, preco, tipo);
        this.tamanho = tamanho;
    }

    public OleoLubrificante(int idProduto, String idUsuario, int idFilial, double quantidade) {
        super(idProduto, idUsuario, idFilial, quantidade);
    }

    public OleoLubrificante() {
    }
    

    public double getTamanho() {
        return tamanho;
    }
}
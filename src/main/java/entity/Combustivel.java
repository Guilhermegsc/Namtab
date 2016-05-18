package entity;

import java.sql.Date;

/**
 *
 * @author Anderson
 */
public class Combustivel extends Produto {

    private String tipo;

    public Combustivel(String idUsuario, int idFilial, int idProduto, double valorVenda, String tipo) {
        super(idUsuario, idFilial, idProduto, valorVenda);
        this.tipo = tipo.toUpperCase();
    }

    public Combustivel(int idVenda, int idProduto, int idFilial, String idUsuario, String nomeUsuario, String nomeProduto, double preco,
            Date dataVenda, double quantidade, double valorVenda, String tipo, String nomeFilial) {
        super(idVenda, idProduto, idFilial, idUsuario, nomeUsuario, nomeProduto, preco, dataVenda, quantidade, valorVenda, nomeFilial);
        this.tipo = tipo.toUpperCase();
    }

    public String getTipo() {
        if (tipo.isEmpty()) {
            return "";
        } else {
            return tipo;
        }
    }

    public Combustivel() {
    }

    public Combustivel(int idProduto, String nomeProduto, double preco, String tipo) {
        super(idProduto, nomeProduto, preco);
        this.tipo = tipo.toUpperCase();
    }

    public Combustivel(String tipo, int idProduto, String idUsuario, int idFilial, double quantidade) {
        super(idProduto, idUsuario, idFilial, quantidade);
        this.tipo = tipo;
    }

}

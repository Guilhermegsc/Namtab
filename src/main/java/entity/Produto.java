package entity;

import java.util.Date;

/**
 *
 * @author Anderson
 */
public class Produto {
    private int idProduto;
    private String nomeUsuario;
    private String nomeProduto;
    private String nomeFilial;
    private double preco;
    private int idVenda;
    private long idUsuario;
    private int idFilial;
    private Date dataVenda;
    private double quantidade;
    private double valorVenda;

    public Produto(int idProduto, String nomeProduto, double preco) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto.toUpperCase();
        this.preco = preco;
    }

    public Produto(int idProduto, long idUsuario, int idFilial, double quantidade) {
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
        this.idFilial = idFilial;
        this.quantidade = quantidade;
    }
    
   
    
    public Produto (long idUsuario, int idFilial, int idProduto, double valorVenda) {
        this.idUsuario = idUsuario;
        this. idFilial = idFilial;
        this.idProduto = idProduto;
        this.valorVenda = valorVenda;   
        }

    public Produto(int idVenda, int idProduto, int idFilial, long idUsuario, String nomeUsuario, String nomeProduto, 
                            double preco, Date dataVenda, double quantidade, double valorVenda, String nomeFilial) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto.toUpperCase();
        this.preco = preco;
        this.idVenda = idVenda;
        this.idUsuario = idUsuario;
        this.idFilial = idFilial;
        this.dataVenda = dataVenda;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
        this.nomeUsuario = nomeUsuario.toUpperCase();
        this.nomeFilial = nomeFilial.toUpperCase();
    }

    
    
    public int getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        if (nomeProduto.isEmpty()) {
            return "";
        } else {
            return nomeProduto;
        }
    }

    public double getPreco() {
        return preco;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public String getNomeUsuario() {
        if (nomeUsuario.isEmpty()) {
            return "";
        } else {
            return nomeUsuario;
        }
    }

    public String getNomeFilial() {
        if (nomeFilial.isEmpty()) {
            return "";
        } else {
            return nomeFilial;
        }
    }
    
    
    
}

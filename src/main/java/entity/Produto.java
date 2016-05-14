package entity;

import java.sql.Date;

/**
 *
 * @author Anderson
 */
public abstract class Produto {
    private int idProduto;
    private String nomeUsuario;
    private String nomeProduto;
    private double preco;
    private int idVenda;
    private int idUsuario;
    private int idFilial;
    private Date dataVenda;
    private double quantidade;
    private double valorVenda;

    public Produto(int idProduto, String nomeProduto, double preco) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }
    
   
    
    public Produto (int idUsuario, int idFilial, int idProduto, double valorVenda) {
        this.idUsuario = idUsuario;
        this. idFilial = idFilial;
        this.idProduto = idProduto;
        this.valorVenda = valorVenda;   
        }

    public Produto(int idVenda, int idProduto, int idFilial, int idUsuario, String nomeUsuario, String nomeProduto, 
                            double preco, Date dataVenda, double quantidade, double valorVenda) {
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
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public int getIdUsuario() {
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
        return nomeUsuario;
    }
    
    
    
}

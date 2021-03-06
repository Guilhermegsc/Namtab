package entity;

import java.text.SimpleDateFormat;
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
    private String idUsuario;
    private int idFilial;
    private Date dataVenda;
    private double quantidade;
    private double valorVenda;
    private String tipo;

    public Produto(int idProduto, String nomeProduto, double preco, String tipo) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto.toUpperCase();
        this.preco = preco;
        this.tipo = tipo.toUpperCase();
    }

    public Produto(int idProduto, String idUsuario, int idFilial, double quantidade) {
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
        this.idFilial = idFilial;
        this.quantidade = quantidade;
    }
    
   
    
    public Produto (String idUsuario, int idFilial, int idProduto, double valorVenda) {
        this.idUsuario = idUsuario;
        this. idFilial = idFilial;
        this.idProduto = idProduto;
        this.valorVenda = valorVenda;  
        }

    public Produto(int idVenda, int idProduto, int idFilial, String idUsuario, String nomeUsuario, String nomeProduto, 
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

    // Construtor feito para a busca de produto na tela de alterar venda
    public Produto(int idVenda, double preco, double quantidade, double valorVenda, String nomeUsuario,
                       String nomeProduto, String nomeFilial, Date dataVenda ) {
        
        this.idVenda = idVenda;
        this.preco = preco;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
        this.nomeUsuario = nomeUsuario;
        this.nomeProduto = nomeProduto;
        this.nomeFilial = nomeFilial;
        this.dataVenda = dataVenda;     
    }
    
    public Produto(){
        
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

    public String getIdUsuario() {
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
    
    public String getTipo(){
        return this.tipo;
    }
    
    
}

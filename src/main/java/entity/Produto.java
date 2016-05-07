/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Anderson
 */
public abstract class Produto {
    private String nome;
    private double preco;
    private String idUsuario;

    public Produto(String nome, double preco, String idUsuario) {
        this.nome = nome;
        this.preco = preco;
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    
    
}

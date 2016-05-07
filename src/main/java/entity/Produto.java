/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Anderson
 */
public abstract class Produto {
    private String nome;
    private double preco;
    private String idUsuario;
    private int idFilial;
    private Date dtOcorrencia;

    public Produto(String nome, double preco, String idUsuario, int idFilial) {
        this.nome = nome;
        this.preco = preco;
        this.idUsuario = idUsuario;
        this.idFilial = idFilial;
    }
    
    public Produto(String nome, double preco, String idUsuario, int idFilial, Date dtOcorrencia) {
        this.nome = nome;
        this.preco = preco;
        this.idUsuario = idUsuario;
        this.idFilial = idFilial;
        this.dtOcorrencia = dtOcorrencia;
    }
    
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
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

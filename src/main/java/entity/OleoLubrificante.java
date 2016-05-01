/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Anderson
 */
public class OleoLubrificante extends Produto{
    
    private String categoria;
    private String classificacao;
    private Date validade;
    private String marca;

    public OleoLubrificante(String categoria, String classificacao, Date validade, String marca, String nome, double preco, int idUsuario) {
        super(nome, preco, idUsuario);
        this.categoria = categoria;
        this.classificacao = classificacao;
        this.validade = validade;
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public Date getValidade() {
        return validade;
    }

    public String getMarca() {
        return marca;
    }
    
    
}

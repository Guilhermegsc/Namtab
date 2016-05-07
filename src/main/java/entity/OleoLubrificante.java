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
public class OleoLubrificante extends Produto{
    
    private String categoria;
    private Date validade;

    public OleoLubrificante(String categoria, Date validade, String nome, double preco, String idUsuario) {
        super(nome, preco, idUsuario);
        this.categoria = categoria;
        this.validade = validade;
    }

    public String getCategoria() {
        return categoria;
    }

    public Date getValidade() {
        return validade;
    }
   
}

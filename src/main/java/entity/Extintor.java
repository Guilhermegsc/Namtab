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
public class Extintor extends Produto {
    
    private double tamanho;
    private String classe;
    private Date validade;

    public Extintor(double tamanho, String classe, Date validade, String nome, double preco, String idUsuario) {
        super(nome, preco, idUsuario);
        this.tamanho = tamanho;
        this.classe = classe;
        this.validade = validade;
    }

    public double getTamanho() {
        return tamanho;
    }

    public String getClasse() {
        return classe;
    }

    public Date getValidade() {
        return validade;
    }

    
    
}

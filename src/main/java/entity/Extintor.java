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
public class Extintor extends Produto {
    
    private String tipo;
    private double tamanho;
    private String classe;
    private String Marca;
    private Date validade;

    public Extintor(String tipo, double tamanho, String classe, String Marca, Date validade, String nome, double preco, int idUsuario) {
        super(nome, preco, idUsuario);
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.classe = classe;
        this.Marca = Marca;
        this.validade = validade;
    }

    public String getTipo() {
        return tipo;
    }

    public double getTamanho() {
        return tamanho;
    }

    public String getClasse() {
        return classe;
    }

    public String getMarca() {
        return Marca;
    }

    public Date getValidade() {
        return validade;
    }

    
    
}

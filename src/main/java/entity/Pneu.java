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
public class Pneu extends Produto{
    
    private int aro;
    private String tipo;
    private String marca;

    public Pneu(int aro, String tipo, String marca, String nome, double preco, int idUsuario) {
        super(nome, preco, idUsuario);
        this.aro = aro;
        this.tipo = tipo;
        this.marca = marca;
    }

    public int getAro() {
        return aro;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }
    
    
    
}

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
public class Combustivel extends Produto {
    
    private String tipo;
    private double quantidade;

    public Combustivel(String nome, double preco, String idUsuario, String tipo, double quantidade) {
        super(nome, preco, idUsuario);
    }

    public String getTipo() {
        return tipo;
    }

    public double getQuantidade() {
        return quantidade;
    }
    
    
    
    
}

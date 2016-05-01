/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Anderson
 */
public class PostoDeGasolina {

    // atributos  
    private String nome;
    private ArrayList<Produto> produtos;

    public PostoDeGasolina() {

        this.produtos = new ArrayList<>();
    }
    
    public PostoDeGasolina(String nome) {
        this.nome = nome;
        this.produtos = new ArrayList<>();
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
    
    public ArrayList<Produto> listaProduto () {
        return produtos;
    }

}

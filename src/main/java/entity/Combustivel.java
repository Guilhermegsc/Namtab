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
public class Combustivel extends Produto {

    private double quantidade;

    public Combustivel(String nome, double preco, String idUsuario, double quantidade, int idFilial) {
        super(nome, preco, idUsuario, idFilial);
    }

    public Combustivel(String nome, double preco, String idUsuario, double quantidade, int idFilial, Date dtOcorrencia) {
        super(nome, preco, idUsuario, idFilial, dtOcorrencia);
    }

    public double getQuantidade() {
        return quantidade;
    }

}

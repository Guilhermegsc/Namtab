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
    
    private String classe;
    private Date validade;

    public OleoLubrificante(String classe, Date validade, String nome, double preco, String idUsuario, int idFilial) {
        super(nome, preco, idUsuario, idFilial);
        this.classe = classe;
        this.validade = validade;
    }
    
    public OleoLubrificante(String classe, Date validade, String nome, double preco, String idUsuario, int idFilial, Date dtOcorrencia) {
        super(nome, preco, idUsuario, idFilial, dtOcorrencia);
        this.classe = classe;
        this.validade = validade;
    }

    public String getClasse() {
        return classe;
    }

    public Date getValidade() {
        return validade;
    }
   
}

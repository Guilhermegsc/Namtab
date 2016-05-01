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
public class Usuario {
    
    private int idUsuario;
    private String nome;
    private String email;
    private int idFilial;
    private int tipoPerfil;
    private Date dataNasc;
    private String funcao;

    public Usuario(int idUsuario, String nome, String email, int idFilial, int tipoPerfil, Date dataNasc, String funcao) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.idFilial = idFilial;
        this.tipoPerfil = tipoPerfil;
        this.dataNasc = dataNasc;
        this.funcao = funcao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public int getTipoPerfil() {
        return tipoPerfil;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public String getFuncao() {
        return funcao;
    }
    
    
}

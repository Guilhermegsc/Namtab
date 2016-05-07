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
public class Usuario {

    private String idUsuario;
    private String senha;
    private String nome;
    private int idFilial;
    private int tipoPerfil;
    private Date dataNasc;
    private String funcao;

    public Usuario(String idUsuario, String nome, int idFilial, int tipoPerfil, Date dataNasc, String funcao) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.idFilial = idFilial;
        this.tipoPerfil = tipoPerfil;
        this.dataNasc = dataNasc;
        this.funcao = funcao;
    }

    public Usuario(String idUsuario, String senha, String nome, int idFilial, int tipoPerfil, Date dataNasc, String funcao) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.idFilial = idFilial;
        this.tipoPerfil = tipoPerfil;
        this.dataNasc = dataNasc;
        this.funcao = funcao;
        this.senha = senha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getSenha() {

        return senha;
    }

    public String getNome() {
        return nome;
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

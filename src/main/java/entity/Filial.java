package entity;

/**
 *
 * @author Thiago
 */
public class Filial {

    private int idFilial;
    private String nomeFilial;
    private String UF;
    private String descricao;

    public Filial(int idFilial, String nomeFilial, String UF, String descricao) {
        this.idFilial = idFilial;
        this.nomeFilial = nomeFilial;
        this.UF = UF;
        this.descricao = descricao;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public String getNomeFilial() {
        return nomeFilial;
    }

    public String getUF() {
        return UF;
    }

    public String getDescricao() {
        return descricao;
    }

}

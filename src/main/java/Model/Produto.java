package Model;

import java.time.LocalDateTime;
import java.util.List;

public class Produto extends Estoque{
    private int codigoP;
    private int quantidade;
    private String nomeP;

    public Produto(int codigoP, int quantidade, String nomeP, int codigo, LocalDateTime dataE2, LocalDateTime dataS, String destino, List<Produto> produtos, int cod, LocalDateTime dataE, String nome, int rg, int cargo, String email, String senha) {
        super(codigo, dataE2, dataS, destino, produtos, cod, dataE, nome, rg, cargo, email, senha);
        this.codigoP = codigoP;
        this.quantidade = quantidade;
        this.nomeP = nomeP;
    }



    public int getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(int codigo) {
        this.codigoP = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeP() {
        return nomeP;
    }

    public void setNomeP(String nome) {
        this.nomeP = nome;
    }
}

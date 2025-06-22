package Model;

import java.time.LocalDateTime;

public class Estoque extends Voluntario{
    
    private int codigo;
    private LocalDateTime dataEE;
    private String est_nome;
    private int quantidade;

    public Estoque(LocalDateTime dataEE, String est_nome, int quantidade, LocalDateTime dataE, String nome, int RG, int cargo, String email, String senha) {
        super(dataE, nome, RG, cargo, email, senha);
        this.dataEE = dataEE;
        this.est_nome = est_nome;
        this.quantidade = quantidade;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getDataEE() {
        return dataEE;
    }

    public void setDataEE(LocalDateTime dataEE) {
        this.dataEE = dataEE;
    }

    public String getEst_nome() {
        return est_nome;
    }

    public void setEst_nome(String est_nome) {
        this.est_nome = est_nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

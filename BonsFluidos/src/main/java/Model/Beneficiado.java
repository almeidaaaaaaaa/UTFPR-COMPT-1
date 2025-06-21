package Model;

import java.time.LocalDateTime;

public class Beneficiado extends Usuario{
    private int cod;
    private LocalDateTime dataE;
    private String endereco;

    public Beneficiado(LocalDateTime dataE, String endereco, String nome, int RG, int cargo, String email, String senha) {
        super(nome, RG, cargo, email, senha);
        this.dataE = dataE;
        this.endereco = endereco;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public LocalDateTime getDataE() {
        return dataE;
    }

    public void setDataE(LocalDateTime dataE) {
        this.dataE = dataE;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}

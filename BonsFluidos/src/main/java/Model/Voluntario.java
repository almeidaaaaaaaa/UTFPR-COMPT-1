package Model;

import java.time.LocalDateTime;

public class Voluntario extends Usuario{
    private int cod;
    private LocalDateTime dataE;

    public Voluntario(int cod, LocalDateTime dataE, String nome, int RG, int cargo, String email, String senha) {
        super(nome, RG, cargo, email, senha);
        this.cod = cod;
        this.dataE = dataE;
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
        
}

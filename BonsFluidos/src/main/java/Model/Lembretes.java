package Model;

import java.time.LocalDateTime;

public class Lembretes {

    private int cod;
    private String recado;
    private LocalDateTime data;

    public Lembretes(String recado, LocalDateTime data, int cod) {
        this.recado = recado;
        this.data = data;
        this.cod = cod;
    }

    public String getRecado() {
        return recado;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setRecado(String recado) {
        this.recado = recado;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}

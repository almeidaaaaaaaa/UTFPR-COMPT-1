package Model;

public class Lembretes {
    private String recado;
    private int data;
    private String destinatario;

    public Lembretes(String recado, int data, String destinatario) {
        this.recado = recado;
        this.data = data;
        this.destinatario = destinatario;
    }

    public String getRecado() {
        return recado;
    }

    public void setRecado(String recado) {
        this.recado = recado;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    
}

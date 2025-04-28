package Model;

public class Evento {
    
    private int idEvento;
    private String orador;
    private int data;
    private int horario;
    private String local;
    private int vagas;
    private int tipo;

    public Evento(int idEvento, String orador, int data, int horario, String local, int vagas, int tipo) {
        this.idEvento = idEvento;
        this.orador = orador;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.vagas = vagas;
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getOrador() {
        return orador;
    }

    public void setOrador(String orador) {
        this.orador = orador;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
    
}

package Model;

public class Voluntario extends Usuario{
    private int cod;
    private int dataE;

    public Voluntario(int cod, int dataE, String nome, int RG, int cargo, String email) {
        super(nome, RG, cargo, email);
        this.cod = cod;
        this.dataE = dataE;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getDataE() {
        return dataE;
    }

    public void setDataE(int dataE) {
        this.dataE = dataE;
    }
        
}

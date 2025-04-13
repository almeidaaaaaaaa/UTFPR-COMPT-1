package Model;

public class Beneficiado extends Pessoa{
    private int cod;
    private int dataE;
    private String entedeco;

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

    public String getEntedeco() {
        return entedeco;
    }

    public void setEntedeco(String entedeco) {
        this.entedeco = entedeco;
    }
    
}

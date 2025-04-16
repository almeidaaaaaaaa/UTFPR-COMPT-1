package Model;

public class Beneficiado extends Usuario{
    private int cod;
    private int dataE;
    private String endereco;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}

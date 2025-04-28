package Model;

public class Doacao {
    private String doador;
    private String produto;
    private int data;

    public Doacao(String doador, String produto, int data) {
        this.doador = doador;
        this.produto = produto;
        this.data = data;
    }

    public String getDoador() {
        return doador;
    }

    public void setDoador(String doador) {
        this.doador = doador;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
    
}

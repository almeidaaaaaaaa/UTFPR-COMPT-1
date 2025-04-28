package Model;

public class Produto {
    private int codigo;
    private int quantidade;
    private String nome;

    public Produto(int codigo, int quantidade, String nome) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

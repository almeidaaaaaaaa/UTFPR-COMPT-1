package Model;

import java.util.List;

public class Estoque {
    
    private int codigo;
    private int dataE;
    private int dataS;
    private String doador;
    private String destino;
    private final List<Produto> produtos;

    public Estoque(int codigo, int dataE, int dataS, String doador, String destino, List<Produto> produtos) {
        this.codigo = codigo;
        this.dataE = dataE;
        this.dataS = dataS;
        this.doador = doador;
        this.destino = destino;
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
    
    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }
    
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDataE() {
        return dataE;
    }

    public void setDataE(int dataE) {
        this.dataE = dataE;
    }

    public int getDataS() {
        return dataS;
    }

    public void setDataS(int dataS) {
        this.dataS = dataS;
    }

    public String getDoador() {
        return doador;
    }

    public void setDoador(String doador) {
        this.doador = doador;
    }
}

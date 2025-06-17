package Model;

import java.util.List;
import java.time.LocalDateTime;

public class Estoque extends Voluntario{
    
    private int codigo;
    private LocalDateTime dataE2;
    private LocalDateTime dataS;
    private String destino;
    private final List<Produto> produtos;

    public Estoque(int codigo, LocalDateTime dataE2, LocalDateTime dataS, String destino, List<Produto> produtos, int cod, LocalDateTime dataE, String nome, int RG, int cargo, String email, String senha) {
        super(cod,  dataE, nome, RG, cargo, email, senha);
        this.codigo = codigo;
        this.dataE2 = dataE2;
        this.dataS = dataS;
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


    /**
     * @return the dataE
     */
    public LocalDateTime getDataE2() {
        return dataE2;
    }

  
    public void setDataE2(LocalDateTime dataE2) {
        this.dataE2 = dataE2;
    }

    /**
     * @return the dataS
     */
    public LocalDateTime getDataS() {
        return dataS;
    }

    /**
     * @param dataS the dataS to set
     */
    public void setDataS(LocalDateTime dataS) {
        this.dataS = dataS;
    }
}

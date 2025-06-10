package Model;

public class Admin extends Usuario{
    private int idMestre;
    private int cod;

    public Admin(int idMestre, int cod, String nome, int RG, int cargo, String email, String senha) {
        super(nome, RG, cargo, email, senha);
        this.idMestre = idMestre;
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public int getIdMestre() {
        return idMestre;
    }

    public void setIdMestre(int idMestre) {
        this.idMestre = idMestre;
    }
   
    public void setCod(int cod) {
        this.cod = cod;
    }
    
}

package Model;

public class Admin extends Usuario{
    private int idMestre;

    public Admin(int idMestre, String nome, int RG, int cargo, String email) {
        super(nome, RG, cargo, email);
        this.idMestre = idMestre;
    }

    public int getIdMestre() {
        return idMestre;
    }

    public void setIdMestre(int idMestre) {
        this.idMestre = idMestre;
    }
    
}

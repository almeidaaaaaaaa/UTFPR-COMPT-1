
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;


public class UsuarioDAO {
    
    public static void main(String[] args) {
        ConexaoBD bd = new ConexaoBD();
        Connection conn = bd.ConexaoBD();
    }
}



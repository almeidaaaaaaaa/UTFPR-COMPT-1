
package Controller;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UsuarioDAO {
    
    public static void inserir(Usuario u) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO usuario(Usu_rg, Usu_nome, Usu_cargo, Usu_email) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, 2);
                    stmt.setString(2, "Gabriel");
                    stmt.setString(3, "1");
                    stmt.setString(4, "Gabriel@email");
                    stmt.executeUpdate();
                }catch(SQLException e) {
                    throw new RuntimeException("Não foi possivel inserir novo usuário", e);
                }
            } else {
                System.out.println("Nao foi possivel conectar ao banco.");
            }
        }catch(SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }
}



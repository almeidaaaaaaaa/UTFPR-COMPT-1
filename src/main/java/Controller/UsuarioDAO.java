package Controller;

import Model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO 
{
    public static boolean verificarRG(String RG) 
        {
            ConexaoBD bd = new ConexaoBD();
            Connection conn = bd.getConnection();
            String sql = "SELECT * FROM usuario WHERE Usu_rg = ? ";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) 
        
            {
                stmt.setString(1, RG);
                

                ResultSet rs = stmt.executeQuery();

                Boolean achou = rs.next(); // Se encontrou um resultado, o usuário existe
            
                return achou;

            } 
        
            catch (SQLException e) 
            {
                e.printStackTrace();//aqui se o usuario nao existe
                return false;
            }
        
        
        }
    
    public static boolean verificarLogin(String usuario, String senha) 
        {
            ConexaoBD bd = new ConexaoBD();
            Connection conn = bd.getConnection();
            String sql = "SELECT * FROM usuario WHERE Usu_rg = ? AND Usu_senha = ?";
            try(PreparedStatement stmt = conn.prepareStatement(sql))
        
            {
                stmt.setString(1, usuario);
                stmt.setString(2, senha);

                ResultSet rs = stmt.executeQuery();

                Boolean achou = rs.next(); // Se encontrou um resultado, o usuário existe
            
                return achou;

            } 
        
            catch (SQLException e) 
            {
                e.printStackTrace();//aqui se o usuario nao existe
                return false;
            }
        
        
        }

    public static void inserir(Usuario u) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO usuario(Usu_rg, Usu_nome, Usu_cargo, Usu_email) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, u.getRG());
                    stmt.setString(2, u.getNome());
                    stmt.setInt(3, u.getCargo());
                    stmt.setString(4, u.getEmail());
                    stmt.setString(5,u.getSenha());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel inserir novo usuário", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }
}

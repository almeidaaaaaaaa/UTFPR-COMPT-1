package Controller;

import Model.Usuario;
import Model.Voluntario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class VoluntarioDAO {

    public static void inserir(Voluntario v) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO voluntario(Vol_data, Usuario_Usu_rg) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setTimestamp(1, Timestamp.valueOf(v.getDataE()));
                    stmt.setInt(2, v.getRG());
                    stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        v.setCod(rs.getInt(1)); 
                    }
                }
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel inserir novo Voluntário", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }
    
    public static void excluir(int cod) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "DELETE FROM voluntario WHERE Vol_cod = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, cod);
                    
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel excluir Voluntário existente", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }
    
    public static void atualizar(Voluntario v) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {

                String sql = "UPDATE voluntario SET Vol_data = ? WHERE Vol_cod = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    
                    stmt.setTimestamp(1, Timestamp.valueOf(v.getDataE()));
                    stmt.setInt(2, v.getCod());


                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel atualizar Voluntário existente", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }
    
    public static Voluntario buscarPorRG(Usuario usu) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        Voluntario voluntario = null;

        try (Connection conn = bd.getConnection()) {
            String sql = "SELECT * FROM voluntario WHERE Usuario_Usu_rg = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, usu.getRG());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        voluntario = new Voluntario(rs.getTimestamp("Vol_data").toLocalDateTime(), usu.getNome(), usu.getRG(), usu.getCargo(), usu.getEmail(), usu.getSenha());
                        voluntario.setCod(rs.getInt("Vol_cod"));
                    }
                }
            }
        }

        return voluntario;
    }
}

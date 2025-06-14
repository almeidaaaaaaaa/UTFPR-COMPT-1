package Controller;

import Model.Voluntario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class VoluntarioDAO {

    public static void inserir(Voluntario v) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO voluntario(Vol_cod, Vol_data, Usuario_Usu_rg) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, v.getCod());
                    stmt.setTimestamp(2, Timestamp.valueOf(v.getDataE()));
                    stmt.setInt(3, v.getRG());
                    stmt.executeUpdate();
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
}

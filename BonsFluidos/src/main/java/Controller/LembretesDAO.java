
package Controller;

import Model.Lembretes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LembretesDAO {
    public static void inserir(Lembretes l) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO lembrete(Lem_cod, Lem_recado, Lem_data) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, l.getCod());
                    stmt.setString(2, l.getRecado());
                    stmt.setTimestamp(3, Timestamp.valueOf(l.getData()));
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException("Não foi possível inserir novo lembrete", ex);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco", ex);
        }
    }

   public static void atualizar(Lembretes l) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "UPDATE lembrete SET Lem_recado=?, Lem_data=? WHERE Lem_cod=?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, l.getRecado());
                    stmt.setTimestamp(2, Timestamp.valueOf(l.getData()));
                    stmt.setInt(3, l.getCod());
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException("Não foi possível inserir novo lembrete", ex);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco", ex);
        }
    }
   
   public static void excluir(int cod) throws SQLException{
       ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
       String sql = "DELETE FROM lembrete WHERE Lem_cod=?";
       try (PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setInt(1, cod);
           stmt.executeUpdate();
       }catch (SQLException ex) {
                    throw new RuntimeException("Não foi possível inserir novo lembrete", ex);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco", ex);
        }
   }
}

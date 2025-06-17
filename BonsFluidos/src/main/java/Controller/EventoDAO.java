package Controller;

import Model.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EventoDAO{

   public static void inserir(Evento e) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO evento(Eve_id, Eve_orador, Eve_data, Eve_local, Eve_vagas, Eve_tipo) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, e.getIdEvento());
                    stmt.setString(2, e.getOrador());
                    stmt.setTimestamp(3, Timestamp.valueOf(e.getData()));
                    stmt.setString(4, e.getLocal());
                    stmt.setInt(5, e.getVagas());
                    stmt.setInt(6, e.getTipo());
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException("Não foi possível inserir novo evento", ex);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco", ex);
        }
    }
   public static void atualizar(Evento e) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "UPDATE evento SET Eve_orador=?, Eve_data=?, Eve_local=?, Eve_vagas=?, Eve_tipo=? WHERE Eve_id=?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, e.getOrador());
                    stmt.setTimestamp(2, Timestamp.valueOf(e.getData()));
                    stmt.setString(3, e.getLocal());
                    stmt.setInt(4, e.getVagas());
                    stmt.setInt(5, e.getTipo());
                    stmt.setInt(6, e.getIdEvento());
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException("Não foi possível inserir novo evento", ex);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco", ex);
        }
    }
   
   public static void excluir(int idEvento) throws SQLException{
       ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
       String sql = "DELETE FROM evento WHERE Eve_id=?";
       try (PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setInt(1, idEvento);
           stmt.executeUpdate();
       }catch (SQLException ex) {
                    throw new RuntimeException("Não foi possível inserir novo evento", ex);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco", ex);
        }
   }
}

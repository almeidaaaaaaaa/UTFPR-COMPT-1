
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Estoque;
import java.sql.Timestamp;

public class EstoqueDAO{
    
        public static void inserir(Estoque s) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO estoque(Est_cod, Est_dataE, Est_dataS, Est_destino, Voluntario_Vol_cod, Voluntario_Usuario_Usu_rg) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, s.getCodigo());
                    stmt.setTimestamp(2, Timestamp.valueOf(s.getDataE2()));
                    stmt.setTimestamp(3, Timestamp.valueOf(s.getDataS()));
                    stmt.setString(4, s.getDestino());
                    stmt.setInt(5, s.getCod());
                    stmt.setInt(6, s.getRG());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Não foi possivel inserir novo estoque", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }
        
        public static void Excluir(int cod) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "DELETE FROM estoque WHERE Est_cod = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, cod);
                    
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel excluir estoque", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }
        
        public static void Atualizar(Estoque s) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "UPDATE estoque SET Est_dataE = ?, Est_dataS = ?, Est_destino = ? WHERE Est_cod = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setTimestamp(1, Timestamp.valueOf(s.getDataE2()));
                    stmt.setTimestamp(2, Timestamp.valueOf(s.getDataS()));
                    stmt.setString(3, s.getDestino());
                    stmt.setInt(4, s.getCodigo());
             
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel atualizar estoque", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }
}

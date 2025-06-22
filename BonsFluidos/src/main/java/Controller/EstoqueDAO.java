package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Estoque;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {

    public static void inserir(Estoque s) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO estoque(Est_dataE, Voluntario_Vol_cod, Voluntario_Usuario_Usu_rg, Est_nome, Est_quantidade) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setTimestamp(1, Timestamp.valueOf(s.getDataEE()));
                    stmt.setInt(2, s.getCod());
                    stmt.setInt(3, s.getRG());
                    stmt.setString(4, s.getEst_nome());
                    stmt.setInt(5, s.getQuantidade());
                    stmt.executeUpdate();
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            s.setCodigo(rs.getInt(1));
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel inserir novo estoque", e);
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

    public static void atualizar(Estoque s) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "UPDATE estoque SET Est_dataE = ?, Est_nome = ?, Est_quantidade = ? WHERE Est_cod = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setTimestamp(1, Timestamp.valueOf(s.getDataEE()));
                    stmt.setString(2, s.getEst_nome());
                    stmt.setInt(3, s.getQuantidade());
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

    public static List<Estoque> buscarTodos() throws SQLException {
        List<Estoque> lista = new ArrayList<>();
        ConexaoBD bd = new ConexaoBD();

        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM estoque";
                try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        Estoque estoque = new Estoque(
                                rs.getTimestamp("Est_dataE").toLocalDateTime(),
                                rs.getString("Est_nome"),
                                rs.getInt("Est_quantidade"),
                                null, // dataE do voluntário (não retornada aqui)
                                null, // nome voluntário
                                rs.getInt("Voluntario_Usuario_Usu_rg"),
                                0, // cargo
                                null, // email
                                null // senha
                        );
                        estoque.setCodigo(rs.getInt("Est_cod"));
                        estoque.setCod(rs.getInt("Voluntario_Vol_cod"));
                        lista.add(estoque);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao buscar todos os produtos do estoque", e);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }

        return lista;
    }
}

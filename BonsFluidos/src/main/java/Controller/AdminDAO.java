/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joaom
 */
public class AdminDAO {

    public static void inserir(Admin a) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO administrador(Adm_idMestre, Usuario_Usu_rg) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setInt(1, a.getIdMestre());
                    stmt.setInt(2, a.getRG());
                    stmt.executeUpdate();
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            a.setCod(rs.getInt(1));
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel inserir novo Administrador", e);
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
                String sql = "DELETE FROM administrador WHERE Adm_cod = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, cod);

                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel excluir Administrador existente", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }

    public static void atualizar(Admin a) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {

                String sql = "UPDATE administrador SET Adm_idMestre = ? WHERE Adm_cod = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, a.getIdMestre());
                    stmt.setInt(2, a.getCod());

                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel atualizar Administrador existente", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }

    public static Admin buscarPorRG(int rg) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "SELECT Adm_idMestre, Adm_cod, Usu_nome, Usuario_Usu_rg, Usu_cargo, Usu_email, Usu_senha "
                        + "FROM administrador "
                        + "JOIN usuario ON administrador.Usuario_Usu_rg = usuario.Usu_rg "
                        + "WHERE Usuario_Usu_rg = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, rg);
                    try (java.sql.ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            int idMestre = rs.getInt("Adm_idMestre");
                            String nome = rs.getString("Usu_nome");
                            int RG = rs.getInt("Usuario_Usu_rg");
                            int cargo = rs.getInt("Usu_cargo");
                            String email = rs.getString("Usu_email");
                            String senha = rs.getString("Usu_senha");

                            return new Admin(idMestre, nome, RG, cargo, email, senha);
                        } else {
                            return null;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possível buscar o Administrador pelo RG", e);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível conectar ao banco", e);
        }
    }

}

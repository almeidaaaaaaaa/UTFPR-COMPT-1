package Controller;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public static boolean verificarRG(int rg) {
        ConexaoBD bd = new ConexaoBD();
        Connection conn = bd.getConnection();
        String sql = "SELECT * FROM usuario WHERE Usu_rg = ? ";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rg);

            ResultSet rs = stmt.executeQuery();

            Boolean achou = rs.next(); // Se encontrou um resultado, o usuário existe

            return achou;

        } catch (SQLException e) { //aqui se o usuario nao existe
            //aqui se o usuario nao existe
            return false;
        }

    }

    public static boolean verificarLogin(int rg, String senha) {
        ConexaoBD bd = new ConexaoBD();
        Connection conn = bd.getConnection();
        String sql = "SELECT * FROM usuario WHERE Usu_rg = ? AND Usu_senha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rg);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            Boolean achou = rs.next(); // Se encontrou um resultado, o usuário existe

            return achou;

        } catch (SQLException e) { //aqui se o usuario nao existe
            //aqui se o usuario nao existe
            return false;
        }
    }

    public static void inserir(Usuario u) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO usuario(Usu_rg, Usu_nome, Usu_cargo, Usu_email, Usu_senha) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, u.getRG());
                    stmt.setString(2, u.getNome());
                    stmt.setInt(3, u.getCargo());
                    stmt.setString(4, u.getEmail());
                    stmt.setString(5, u.getSenha());
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

    public static void excluir(int rg) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "DELETE FROM usuario WHERE Usu_rg = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, rg);

                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel excluir Usuario existente", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }

    public static void atualizar(Usuario u) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {

                String sql = "UPDATE usuario SET Usu_nome = ?, Usu_cargo = ?, Usu_email = ?, Usu_senha = ? WHERE Usu_rg = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setString(1, u.getNome());
                    stmt.setInt(2, u.getCargo());
                    stmt.setString(3, u.getEmail());
                    stmt.setString(4, u.getSenha());
                    stmt.setInt(5, u.getRG());

                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel atualizar Usuario existente", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
    }

    public static List<Object[]> buscarU() throws SQLException {
        List<Object[]> usu = new ArrayList<>();
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {

                String sql = "SELECT Usu_rg, Usu_nome, Usu_cargo, Usu_email FROM usuario";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        String nome = rs.getString("Usu_nome");
                        int cg = rs.getInt("Usu_cargo");
                        String em = rs.getString("Usu_email");

                        usu.add(new Object[]{nome, cg, em});
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel listar usuarios", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
        return usu;
    }

    public static List<Object[]> buscarPorCargo(int cargo) throws SQLException {
        List<Object[]> usu = new ArrayList<>();
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            String sql = "SELECT Usu_rg, Usu_nome, Usu_cargo, Usu_email FROM usuario WHERE Usu_cargo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, cargo);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    usu.add(new Object[]{
                        rs.getString("Usu_nome"),
                        rs.getInt("Usu_cargo"),
                        rs.getString("Usu_email")
                    });
                }
            }
        }
        return usu;
    }
   public static Usuario buscarPorRG(int rg) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        Usuario usuario = null;

        try (Connection conn = bd.getConnection()) {
            String sql = "SELECT * FROM usuario WHERE Usu_rg = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, rg);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario(rs.getString("Usu_nome"), rg,rs.getInt("Usu_cargo"), rs.getString("Usu_email"), rs.getString("Usu_senha"));
                    }
                }
            }
        }

        return usuario;
    }
}

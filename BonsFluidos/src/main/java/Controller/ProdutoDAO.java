package Controller;

import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO{

   public static void inserir(Produto p) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO produto(Pro_id, Pro_quantidade, Pro_nome, Estoque_Est_cod) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, p.getCodigoP());
                    stmt.setInt(2, p.getQuantidade());
                    stmt.setString(3, p.getNomeP());
                    stmt.setInt(4, p.getCodigo());
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException("Não foi possível inserir novo produto", ex);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco", ex);
        }
    }
   public static void atualizar(Produto p) throws SQLException {
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
                String sql = "UPDATE produto SET Pro_quantidade=?, Pro_nome=?, Estoque_Est_cod=? WHERE Pro_id=?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, p.getQuantidade());
                    stmt.setString(2, p.getNomeP());
                    stmt.setInt(3, p.getCodigo());
                    stmt.setInt(4, p.getCodigoP());
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                        throw new RuntimeException("Não foi possível atualizar produto", ex);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco", ex);
        }
    }
   
   public static void excluir(int codigo) throws SQLException{
       ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {
       String sql = "DELETE FROM produto WHERE Pro_id=?";
       try (PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setInt(1, codigo);
           stmt.executeUpdate();
       }catch (SQLException ex) {
                    throw new RuntimeException("Não foi possível excluir produto", ex);
                }
            } else {
                throw new RuntimeException("Não foi possível conectar ao banco.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível conectar ao banco", ex);
        }
   }
   
       
    public List<Object[]>buscarP() throws SQLException {
        List<Object[]> pro = new ArrayList<>();
        ConexaoBD bd = new ConexaoBD();
        try (Connection conn = bd.getConnection()) {
            if (conn != null) {

                String sql = "SELECT Pro_id, Pro_quantidade, Pro_nome, Estoque_Est_cod FROM produto";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    while(rs.next()){
                        int id = rs.getInt("Pro_id");
                        int qt = rs.getInt("Pro_quantidade");
                        String nome = rs.getString("Pro_nome"); 
                        
                        int cod = rs.getInt("Estoque_Est_cod");
                        
                        pro.add(new Object[]{id, qt, nome, cod});
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Não foi possivel listar produtos", e);
                }
            } else {
                throw new RuntimeException("Nao foi possivel conectar ao banco.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nao foi possivel conectar ao banco", e);
        }
        return pro;
    }    
    
}

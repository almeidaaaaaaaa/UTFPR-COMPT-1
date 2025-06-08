/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


//imports
import java.sql.*;

/**
 *
 * @author scard
 */

//

    public class Login 
    {
        public static boolean verificarLogin(String usuario, String senha) 
        {
            String url = "jdbc:mysql://localhost:3306/competente"/*aqui fica o local de conexao */;
            String user = "root"; // aqui fica o usuário do MySQL
            String pass = ""; // aqui fica a senha do MySQL

            String sql = "SELECT * FROM dados_login WHERE dad_login = ? AND dad_senha = ?";

            try (
                Connection conn = DriverManager.getConnection(url, user, pass);
                PreparedStatement stmt = conn.prepareStatement(sql)
            ) 
        
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

    public static void main(String[] args) 
    {
        if (verificarLogin(""/*output da box de login no campo usuario*/, ""/*output da box de login no campo senha*/)) 
        {
            System.out.println("Login bem-sucedido!");//quando a parte visual existir pop-up com login bem sucedido
        } 
        
        else 
        {
            System.out.println("Usuário ou senha inválidos.");//quando a parte visual existir pop-up com usuario ou senha invalidos
        }
    }
    }
 

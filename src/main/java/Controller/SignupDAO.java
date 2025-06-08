/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.*;

/**
 *
 * @author scard
 */

//como nao tem requerimento de senha nao existe mais de uma excessao entao o unico modo de falha e se o usuario ja existir
    

public class SignupDAO 
{

    public static boolean registrar(String usuario, String senha) 
    {
        ConexaoBD bd = new ConexaoBD();
        Connection conn = bd.getConnection();
        String inserirSql = "INSERT INTO dados_login (Usu_rg, Usu_senha) VALUES (?, ?)";

        //verificar conexao com o bd
        try(PreparedStatement stmt = conn.prepareStatement(inserirSql))
            
        {
            // Verifica se o usuário já existe
            if (UsuarioDAO.verificarRG(usuario)) 
            {
                System.out.println("");//quando o componente visual existir colocar um pop-up com usuario ja existe
                return false;
            }

            // Insere novo usuário
            try (PreparedStatement inserirStmt = conn.prepareStatement(inserirSql)) 
            {
                //pra essa parte provavelmente vai precisar da logica de cadostro de admins?
                inserirStmt.setString(1, usuario);
                inserirStmt.setString(2, senha);
                inserirStmt.executeUpdate();
                System.out.println("Usuário registrado com sucesso!");//quando o componente visual existir colocar um pop-up com usuario regitrado com sucesso
                return true;
            }

        } catch (SQLException e) 
        {
            e.printStackTrace();//colocar codigo de erro
            return false;
        }
    }

    
}

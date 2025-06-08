/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;

/**
 *
 * @author scard
 */

//como nao tem requerimento de senha nao existe mais de uma excessao entao o unico modo de falha e se o usuario ja existir
    

public class Signup 
{

    public static boolean registrar(String usuario, String senha) 
    {
        String url = "jdbc:mysql://localhost:3306/competente";
        String user = "root"; // seu usuário do MySQL
        String pass = ""; // sua senha do MySQL

        String verificarSql = "SELECT * FROM dados_login WHERE dad_login = ?";
        String inserirSql = "INSERT INTO dados_login (dad_login, dad_senha) VALUES (?, ?)";

        //verificar conexao com o bd
        try 
            (
            Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement verificarStmt = conn.prepareStatement(verificarSql)
        )
            
        {
            // Verifica se o usuário já existe
            verificarStmt.setString(1, usuario);
            ResultSet rs = verificarStmt.executeQuery();

            if (rs.next()) 
            {
                System.out.println("");//quando o componente visual existir colocar um pop-up com usuario ja existe
                return false;
            }

            // Insere novo usuário
            try (PreparedStatement inserirStmt = conn.prepareStatement(inserirSql)) 
            {
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

    public static void main(String[] args) {
        if (registrar(""/*output da box de registro campo de usuario*/, ""/*output da box de registro campo senha */)) 
        {
            System.out.println("Registro realizado.");
        } 
        else 
        {
            System.out.println("Falha no registro.");
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usuario;
import java.sql.SQLException;

/**
 *
 * @author scard
 */
//como nao tem requerimento de senha nao existe mais de uma excessao entao o unico modo de falha e se o usuario ja existir
public class Signup {

    public static boolean registrar(Usuario u) {

        if (UsuarioDAO.verificarRG(u.getRG())) {
            System.out.println("");//quando o componente visual existir colocar um pop-up com usuario ja existe
            return false;
        }

        try {
            UsuarioDAO.inserir(u);
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usuario;

/**
 *
 * @author scard
 */

//como nao tem requerimento de senha nao existe mais de uma excessao entao o unico modo de falha e se o usuario ja existir
    

public class Signup
{
    static UsuarioDAO user = new UsuarioDAO();

    public static boolean registrar(Usuario u) 
    {
        
        if (UsuarioDAO.verificarRG(u.getNome())) 
        {
            System.out.println("");//quando o componente visual existir colocar um pop-up com usuario ja existe
            return false;
        }
        
        

        try
        {
           user.inserir(u);
           return true;
            
        }
        catch(Exception e)
                {
                    e.printStackTrace();
                    return false;
                }
    }

    
}

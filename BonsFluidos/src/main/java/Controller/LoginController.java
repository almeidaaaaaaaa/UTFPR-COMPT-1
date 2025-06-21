/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Admin;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Pega os dados do formulário
        String rgStr = request.getParameter("login-rg");
        String senha = request.getParameter("login-password");

        try {
            int rg = Integer.parseInt(rgStr);

            Boolean achouUsu = UsuarioDAO.verificarLogin(rg, senha);
            if (achouUsu) {
                Admin adm = AdminDAO.buscarPorRG(rg);
                if (adm != null) {
                    response.sendRedirect(request.getContextPath() + "/admin.html");
                } else {
                    response.sendRedirect(request.getContextPath() + "/index.html");
                }
                return;
            }
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Usuario existe.");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("RG inválido.");
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

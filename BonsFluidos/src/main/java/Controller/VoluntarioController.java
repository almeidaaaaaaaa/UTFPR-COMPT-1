/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Voluntario;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/api/voluntario")
public class VoluntarioController extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Pega os dados do formulário
        String nome = request.getParameter("vol-nome");
        String rgStr = request.getParameter("vol-rg");
        String email = request.getParameter("vol-email");
        String senha = request.getParameter("vol-password");
        String cargoStr = request.getParameter("vol-cargo");

        try {
            int rg = Integer.parseInt(rgStr);
            int cargo = Integer.parseInt(cargoStr);

            Voluntario v = new Voluntario(LocalDateTime.now(), nome, rg, cargo, email, senha);

            UsuarioDAO.inserir(v);
            VoluntarioDAO.inserir(v);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.sendRedirect(request.getContextPath() + "/index.html");


        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("RG inválido.");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao inserir voluntário.");
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Beneficiado;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/beneficiado")
public class BeneficiadoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Pega os dados do formulário
        String nome = request.getParameter("ben-nome");
        String rgStr = request.getParameter("ben-rg");
        String email = request.getParameter("ben-email");
        String senha = request.getParameter("ben-password");
        String cargoStr = request.getParameter("ben-cargo");
        String endereco = request.getParameter("ben-endereco");

        try {
            int rg = Integer.parseInt(rgStr);
            int cargo = Integer.parseInt(cargoStr);

            Beneficiado b = new Beneficiado(LocalDateTime.now(), endereco, nome, rg, cargo, email, senha);

            UsuarioDAO.inserir(b);
            BeneficiadoDAO.inserir(b);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.sendRedirect(request.getContextPath() + "/index.html");

        } catch (NumberFormatException e) {
            System.out.println(e);

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("RG inválido.");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao inserir voluntário.");
        }
    }
}

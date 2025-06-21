package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/usuarios")
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cargoStr = request.getParameter("cargo");
        List<Object[]> usuarios;

        try {
            if (cargoStr != null) {
                int cargo = Integer.parseInt(cargoStr);
                usuarios = UsuarioDAO.buscarPorCargo(cargo);
            } else {
                usuarios = UsuarioDAO.buscarU();
            }

            // converter para JSON e enviar (simplificado)
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("[");
            for (int i = 0; i < usuarios.size(); i++) {
                Object[] u = usuarios.get(i);

                out.print("{");
                out.print("\"nome\":\"" + u[0] + "\",");
                out.print("\"cargo\":" + u[1] + ",");
                out.print("\"email\":\"" + u[2] + "\"");
                out.print("}");
                if (i < usuarios.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]");
            out.flush();
        } catch (SQLException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao buscar usuários.");
        }
    }
}

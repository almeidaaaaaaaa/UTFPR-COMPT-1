package Controller;

import Model.Estoque;
import Model.Usuario;
import Model.Voluntario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/produto")
public class EstoqueController extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            List<Estoque> lista = EstoqueDAO.buscarTodos();

            out.print("[");
            for (int i = 0; i < lista.size(); i++) {
                Estoque e = lista.get(i);

                out.print("{");
                out.print("\"codigo\":" + e.getCodigo() + ",");
                out.print("\"est_nome\":\"" + escapeJson(e.getEst_nome()) + "\",");
                out.print("\"quantidade\":" + e.getQuantidade() + ",");
                out.print("\"rg\":\"" + e.getRG() + "\"");
                out.print("}");

                if (i < lista.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]");
            out.flush();

        } catch (SQLException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"error\":\"Erro ao buscar estoques.\"}");
        }
    }

    /**
     * Método simples para escapar aspas e barras em strings JSON.
     */
    private String escapeJson(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String rgStr = request.getParameter("rg");
        try {
            int rg = Integer.parseInt(rgStr);
            Usuario usu = UsuarioDAO.buscarPorRG(rg);
            Voluntario vol = VoluntarioDAO.buscarPorRG(usu);

            Estoque estoque = new Estoque(LocalDateTime.now(), nome, quantidade, vol.getDataE(), vol.getNome(), vol.getRG(), vol.getCargo(), vol.getEmail(), vol.getSenha());
            estoque.setCod(vol.getCod());
            EstoqueDAO.inserir(estoque);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        try {
            int id = Integer.parseInt(idStr);
            EstoqueDAO.excluir(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

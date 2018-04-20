package tacos;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tacos/info")
public class TacoDetailsServlet extends HttpServlet {
    @Inject
    TaqueriaManager manager;

    protected int idParameter(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }

    protected Taqueria findTaqueria(HttpServletRequest request) {
        try {
            return manager.getTaqueriaById(idParameter(request));
        } catch (Exception e) {
            // log?
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Taqueria taqueria = findTaqueria(request);
        if (taqueria == null) {
            response.sendError(404, "Not Found");
        } else {
            request.setAttribute("taqueria", taqueria);
            request.getRequestDispatcher("/WEB-INF/taco_details.jsp").forward(request, response);
        }
    }
}
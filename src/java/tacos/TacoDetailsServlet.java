package tacos;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/tacos/info")
public class TacoDetailsServlet extends HttpServlet {

    @Resource(lookup = "java:app/jdbc/nachoDB")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TaqueriaManager manager = (TaqueriaManager) getServletContext().getAttribute("taqueriaManager");

            int id = Integer.parseInt(req.getParameter("id"));
            Taqueria taqueria = manager.getTaqueriaById(dataSource, id);

            if (taqueria == null) {
                req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
            } else {
                req.setAttribute("taqueria", taqueria);
                req.getRequestDispatcher("/WEB-INF/taco_details.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }
    }

}

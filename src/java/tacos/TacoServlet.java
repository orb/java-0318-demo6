package tacos;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tacos")
public class TacoServlet extends HttpServlet {
    @Inject
    TaqueriaManager manager;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {         
        req.setAttribute("taquerias", manager.getTaqueriasRatingOrder());
        
        req.getRequestDispatcher("/WEB-INF/tacos.jsp").forward(req, resp);
    }
}

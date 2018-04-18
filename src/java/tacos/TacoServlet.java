
package tacos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/tacos")
public class TacoServlet extends HttpServlet {
    @Resource(lookup = "java:app/jdbc/nachoDB")
    DataSource dataSource;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
        TaqueriaManager manager = (TaqueriaManager) getServletContext().getAttribute("taqueriaManager");
        req.setAttribute("taquerias", manager.getTaqueriasRatingOrder(dataSource));
        
        req.getRequestDispatcher("/WEB-INF/tacos.jsp").forward(req, resp);
    }

}

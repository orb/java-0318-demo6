
package tacos;

import customers.Customer;
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
        ArrayList<Taqueria> taquerias = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM taquerias");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Taqueria newTaqueria = new Taqueria();

                newTaqueria.setId(resultSet.getInt("id"));
                newTaqueria.setName(resultSet.getString("name"));
                newTaqueria.setRating(resultSet.getInt("rating"));
                
                taquerias.add(newTaqueria);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        req.setAttribute("taquerias", taquerias);
        
        req.getRequestDispatcher("/WEB-INF/tacos.jsp").forward(req, resp);
    }

}

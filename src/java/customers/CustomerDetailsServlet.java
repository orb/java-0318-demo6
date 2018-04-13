package customers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/customer")
public class CustomerDetailsServlet extends HttpServlet {
    @Resource(lookup = "java:app/jdbc/nachoDB")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // we should validate here
        int id = Integer.parseInt((String) req.getParameter("id"));
        req.setAttribute("id", id);
        
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT CUSTOMER_ID,NAME,EMAIL FROM CUSTOMER WHERE CUSTOMER_ID=?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Customer newCustomer = new Customer();
                newCustomer.setId(resultSet.getInt("CUSTOMER_ID"));
                newCustomer.setName(resultSet.getString("NAME"));
                newCustomer.setEmail(resultSet.getString("EMAIL"));
                
                req.setAttribute("customer", newCustomer);
            }

        } catch (SQLException ex) {
            // bad error handling
            ex.printStackTrace();
        }
        
        // what if customer is not found?
        req.getRequestDispatcher("/WEB-INF/details.jsp").forward(req, resp);    
    }
    
}
